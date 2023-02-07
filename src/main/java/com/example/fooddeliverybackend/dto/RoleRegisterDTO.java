package com.example.fooddeliverybackend.dto;
import com.example.fooddeliverybackend.entity.permission.Permission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleRegisterDTO {
    private String name;
    private List<Permission> permissionList;
}