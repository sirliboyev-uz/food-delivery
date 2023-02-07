package com.example.fooddeliverybackend.service;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.RoleRegisterDTO;
import com.example.fooddeliverybackend.entity.Role;
import com.example.fooddeliverybackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    ApiResponse roleRegister(RoleRegisterDTO roleRegisterDTO);

    ApiResponse roleUpdate(Long id, RoleRegisterDTO dto);

    ApiResponse roleDelete(Long id);
    ApiResponse role(Long id);
    ApiResponse roles();
}
