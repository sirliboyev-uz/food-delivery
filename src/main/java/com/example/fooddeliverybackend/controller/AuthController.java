package com.example.fooddeliverybackend.controller;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.JwtResponse;
import com.example.fooddeliverybackend.dto.LoginDto;
import com.example.fooddeliverybackend.dto.RegisterDto;
import com.example.fooddeliverybackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody RegisterDto registerDTO){
        ApiResponse apiResponse = userService.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto loginDTO){
        JwtResponse jwtResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(jwtResponse);
    }
    @GetMapping(value = "/emailConfirm")
    public ResponseEntity<?> confirm(@RequestParam String emailCode, @RequestParam String email){
        ApiResponse apiResponse=userService.confirm(email,emailCode);
        return ResponseEntity.status(apiResponse.getType()?201:409).body(apiResponse.getMessage());
    }
//    @GetMapping(value = "/home")
//    public ResponseEntity<?> home(){
//        return ResponseEntity.ok().body("Salom");
//    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
