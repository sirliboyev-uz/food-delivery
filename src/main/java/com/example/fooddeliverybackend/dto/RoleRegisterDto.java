package com.example.fooddeliverybackend.dto;
import com.example.fooddeliverybackend.entity.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleRegisterDto {
    private String name;
    private List<Permission> permissionList;
}