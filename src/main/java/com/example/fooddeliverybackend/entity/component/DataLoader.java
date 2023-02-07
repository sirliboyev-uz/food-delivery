package com.example.fooddeliverybackend.entity.component;
import com.example.fooddeliverybackend.entity.Role;
import com.example.fooddeliverybackend.entity.Users;
import com.example.fooddeliverybackend.entity.permission.Permission;
import com.example.fooddeliverybackend.entity.utils.Constanta;
import com.example.fooddeliverybackend.repository.RoleRepository;
import com.example.fooddeliverybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.example.fooddeliverybackend.entity.permission.Permission.*;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Value(value = "${spring.sql.init.mode}")
    String initMode;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("Successfully runner");
        if (initMode.equals("always")){
            Permission[] permissions=Permission.values();
            Role adminRole = roleRepository.save(new Role(
                    Constanta.ADMIN,
                    Arrays.asList(permissions)
            ));
            Role userRole = roleRepository.save(new Role(
                    Constanta.USER,
                    Arrays.asList(READ_ROLE, READ_USER)
            ));
            userRepository.save(new Users(
                    "Admin", "Admin", "admin@gmail.com", passwordEncoder.encode("admin"),"+998945744373", adminRole, true, null
            ));
            userRepository.save(new Users(
                    "User", "User", "user@gmail.com", passwordEncoder.encode("user"),"+998945744373", userRole, true, null
            ));
        }
    }
}
