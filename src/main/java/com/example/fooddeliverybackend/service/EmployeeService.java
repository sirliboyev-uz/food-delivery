package com.example.fooddeliverybackend.service;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.EmployeeDTO;
import com.example.fooddeliverybackend.entity.Users;
import com.example.fooddeliverybackend.entity.utils.Constanta;
import com.example.fooddeliverybackend.repository.RoleRepository;
import com.example.fooddeliverybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface EmployeeService{
    public ApiResponse empAdd(EmployeeDTO dto);
    boolean sendEmail(String email,String code);

    public ApiResponse confirm(String email, String code);

    public ApiResponse empUpdate(Long id, EmployeeDTO dto);

    public ApiResponse empDelete(Long id);

    public ApiResponse empSelect();
}
