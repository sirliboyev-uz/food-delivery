package com.example.fooddeliverybackend.service.ServiceImpl;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.LoginDTO;
import com.example.fooddeliverybackend.dto.RegisterDTO;
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
    public ApiResponse registerUser(RegisterDTO registerDTO) {
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
                        true,
                        UUID.randomUUID().toString()
                );
                boolean d=sendEmail(users.getUsername(),users.getEmailCode());
                userRepository.save(users);
                return new ApiResponse("Successfully registered", true);
            }
            return new ApiResponse("Already user registered", false);
        }
        return new ApiResponse("Not equal re password", false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username+" not found username"));
    }
    @Override
    public ApiResponse loginUser(LoginDTO loginDTO) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        Users users= (Users) authenticate.getPrincipal();
        String token1=token.getToken(users.getUsername(), users.getRole());
        System.out.println(token1);
        return new ApiResponse("Welcome to profile", true, token1);
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
            return new ApiResponse("Successfully confirmed email",true);
        }
        return new ApiResponse("Already confirmed",false);
    }
}
