package com.example.fooddeliverybackend.entity.utils;

import com.example.fooddeliverybackend.entity.Users;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class CreatedByUser implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")){
            Users staff = (Users) authentication.getPrincipal();
            return Optional.of(staff.getId());
        }
        return Optional.empty();
    }
}
