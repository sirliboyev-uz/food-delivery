package com.example.fooddeliverybackend.service.ServiceImpl;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.JwtResponse;
import com.example.fooddeliverybackend.dto.LoginDto;
import com.example.fooddeliverybackend.dto.RegisterDto;
import com.example.fooddeliverybackend.entity.Users;
import com.example.fooddeliverybackend.entity.utils.Constanta;
import com.example.fooddeliverybackend.repository.RoleRepository;
import com.example.fooddeliverybackend.repository.UserRepository;
import com.example.fooddeliverybackend.service.UserService;
import com.example.fooddeliverybackend.web.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    Token token;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public ApiResponse registerUser(RegisterDto registerDTO) {
        boolean optionalUsers=userRepository.existsByEmail(registerDTO.getEmail());
        if (registerDTO.getPassword().equals(registerDTO.getRePassword())){
            if (!optionalUsers){
                Users users=new Users(
                        registerDTO.getFirstName(),
                        registerDTO.getLastName(),
                        registerDTO.getEmail(),
                        passwordEncoder.encode(registerDTO.getPassword()),
                        registerDTO.getPhone(),
                        roleRepository.findByRoleName(Constanta.USER).get(),
                        false,
                        UUID.randomUUID().toString()
                );
                boolean d=sendEmail(users.getUsername(),users.getEmailCode());
                userRepository.save(users);
                return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz, emailingizga tasdiqlash xabari yuborildi", true);
            }
            return new ApiResponse("Avval ro'yxatdan o'tgansiz", false);
        }
        return new ApiResponse("Parollar mos emas", false);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username+" not found username"));
    }
    @Override
    public JwtResponse loginUser(LoginDto loginDTO) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        if (!authenticate.isAuthenticated()) return null;
        Users users= (Users) authenticate.getPrincipal();
        String token1=token.generateJwtToken(authenticate);
        System.out.println(token1);
        return new JwtResponse(token1, users.getId(), users.getEmail(), users.getRole().getRoleName());
    }
    @Override
    public boolean sendEmail(String email,String code){
        try {
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom("test@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject("Confirm Email code");
            mailMessage.setText("<a href='http://localhost:8080/api/v1/auth/emailConfirm?emailCode="+code+"&email="+email+"'>Confirmation email</a>");
            javaMailSender.send(mailMessage);
            return true;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return false;
        }
    }

    @Override
    public ApiResponse confirm(String email, String code){
        Optional<Users> byEmailAndCodeEmail =userRepository.findByEmailAndEmailCode(email,code);
        if(byEmailAndCodeEmail.isPresent()){
            Users users=byEmailAndCodeEmail.get();
            users.setEnabled(true);
            users.setEmailCode(null);
            userRepository.save(users);
            return new ApiResponse("Email tasdiqlandi",true);
        }
        return new ApiResponse("Hisob foallashtirilgan",false);
    }

    @Override
    public Optional<Users> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }
}
