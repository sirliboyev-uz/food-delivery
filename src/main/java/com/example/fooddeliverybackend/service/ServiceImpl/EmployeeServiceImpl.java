package com.example.fooddeliverybackend.service.ServiceImpl;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.EmployeeDto;
import com.example.fooddeliverybackend.entity.Users;
import com.example.fooddeliverybackend.repository.RoleRepository;
import com.example.fooddeliverybackend.repository.UserRepository;
import com.example.fooddeliverybackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
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
    public ApiResponse empAdd(EmployeeDto dto) {
        if (!roleRepository.existsByRoleName(String.valueOf(dto.getRoleName()))) return new ApiResponse("Not found role",false);
        if(userRepository.existsByEmail(dto.getEmail())) return new ApiResponse("Already registered user",false);
        Users users=new Users(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getPhone(),
                dto.getRoleName(),
                false,
                UUID.randomUUID().toString()
        );

        boolean d=sendEmail(users.getUsername(),users.getEmailCode());
        userRepository.save(users);
        return new ApiResponse("Successfully",true,d);
    }
    @Override
    public boolean sendEmail(String email, String code){
        try {
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom("test@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject("Confirm Email code");
            mailMessage.setText("<a href='http://localhost:8080/auth/emailConfirm?emailCode="+code+"&email="+email+"'>Confirmation email</a>");
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
    @Override
    public ApiResponse empUpdate(Long id, EmployeeDto dto) {
        Optional<Users> optionalUsers=userRepository.findById(id);
        if (optionalUsers.isPresent()){
            if(userRepository.existsByEmail(dto.getEmail())) return new ApiResponse("Username already taken",false);
            Users users=optionalUsers.get();
            users.setFirstName(dto.getFirstName());
            users.setLastName(dto.getLastName());
            users.setPassword(passwordEncoder.encode(dto.getPassword()));
            users.setEmail(dto.getEmail());
            users.setRole(roleRepository.findByRoleName(String.valueOf(dto.getRoleName())).get());
            userRepository.save(users);
            return new ApiResponse("Update",true);
        }
        return new ApiResponse("Not found user",false);
    }
    @Override
    public ApiResponse empDelete(Long id) {
        Optional<Users> optionalUsers=userRepository.findById(id);
        if (optionalUsers.isPresent()){
            userRepository.deleteById(optionalUsers.get().getId());
            return new ApiResponse("Delete employee",true);
        }
        return new ApiResponse("Not found employee",false);
    }
    @Override
    public ApiResponse empSelect() {
        List<Users> employees=userRepository.findAll();
        return new ApiResponse("Employees list",true,employees);
    }
}
