package com.example.fooddeliverybackend.service;
import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.LoginDTO;
import com.example.fooddeliverybackend.dto.RegisterDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService{
    ApiResponse registerUser(RegisterDTO registerDTO);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    ApiResponse loginUser(LoginDTO loginDTO);
    boolean sendEmail(String email,String code);
    ApiResponse confirm(String email, String code);
}
