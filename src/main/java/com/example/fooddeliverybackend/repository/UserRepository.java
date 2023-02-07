package com.example.fooddeliverybackend.repository;

import com.example.fooddeliverybackend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailAndEmailCode(String email, String emailCode);
    boolean existsByEmail(String email);
}
