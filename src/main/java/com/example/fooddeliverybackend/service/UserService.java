package com.example.fooddeliverybackend.service;
import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.JwtResponse;
import com.example.fooddeliverybackend.dto.LoginDto;
import com.example.fooddeliverybackend.dto.RegisterDto;
import com.example.fooddeliverybackend.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService{
    ApiResponse registerUser(RegisterDto registerDTO);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    JwtResponse loginUser(LoginDto loginDTO);
    boolean sendEmail(String email,String code);
    ApiResponse confirm(String email, String code);
    Optional<Users> getUser(String email);
    List<Users> getUsers();
}
