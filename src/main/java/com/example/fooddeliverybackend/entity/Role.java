package com.example.fooddeliverybackend.entity;

import com.example.fooddeliverybackend.entity.permission.Permission;
import com.example.fooddeliverybackend.template.AbstractEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role extends AbstractEntity {
    private String roleName;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Permission> permissions;

}
