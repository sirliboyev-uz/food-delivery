package com.example.fooddeliverybackend.dto;

import com.example.fooddeliverybackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer ";
    private Long id;
    private String email;
    private String roles;

    public JwtResponse(String token, Long id, String email, String roles) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }
}