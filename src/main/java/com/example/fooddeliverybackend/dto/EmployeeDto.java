package com.example.fooddeliverybackend.dto;

import com.example.fooddeliverybackend.entity.Role;
import lombok.Data;

@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;

    private String phone;
    private String email;
    private String password;
    private Role roleName;
}
