package com.example.fooddeliverybackend.controller;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.LoginDTO;
import com.example.fooddeliverybackend.dto.RegisterDTO;
import com.example.fooddeliverybackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO){
        ApiResponse apiResponse = userService.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDTO loginDTO){
        ApiResponse apiResponse = userService.loginUser(loginDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse);
    }

    @GetMapping(value = "/emailConfirm")
    public ResponseEntity<?> confirm(@RequestParam String emailCode, @RequestParam String email){
        ApiResponse apiResponse=userService.confirm(email,emailCode);
        return ResponseEntity.status(apiResponse.getType()?201:409).body(apiResponse.getMessage());
    }
}
