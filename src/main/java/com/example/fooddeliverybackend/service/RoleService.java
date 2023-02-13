package com.example.fooddeliverybackend.service;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.RoleRegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    ApiResponse register(RoleRegisterDto roleRegisterDTO);

    ApiResponse update(Long id, RoleRegisterDto dto);

    ApiResponse delete(Long id);
    ApiResponse role(Long id);
    ApiResponse roles();
}
