package com.example.fooddeliverybackend.service;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.EmployeeDto;
public interface EmployeeService{
    ApiResponse empAdd(EmployeeDto dto);
    boolean sendEmail(String email,String code);
    ApiResponse confirm(String email, String code);
    ApiResponse empUpdate(Long id, EmployeeDto dto);
    ApiResponse empDelete(Long id);
    ApiResponse empSelect();
}
