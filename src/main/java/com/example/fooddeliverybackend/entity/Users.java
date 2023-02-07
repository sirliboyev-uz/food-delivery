package com.example.fooddeliverybackend.entity;

import com.example.fooddeliverybackend.entity.permission.Permission;
import com.example.fooddeliverybackend.template.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Users extends AbstractEntity implements UserDetails {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private Role role;

    private boolean enabled;

    private String emailCode;

    private boolean isAccountNonExpired=true;
    private boolean isAccountNonLocked=true;
    private boolean isCredentialsNonExpired=true;

    public Users(String firstName, String lastName, String email, String password, String phone, Role role, boolean enabled, String emailCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
        this.phone=phone;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.emailCode=emailCode;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permission> roleTypes = role.getPermissions();
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for (Permission i: roleTypes){
            grantedAuthorities.add(new SimpleGrantedAuthority(i.name()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
}
