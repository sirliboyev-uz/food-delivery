package com.example.fooddeliverybackend.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Valid
public class RegisterDTO {

//    @NotNull(message = "fullName not null")
    private String firstName;

//    @NotNull(message = "username not null")
    private String lastName;

//    @NotNull(message = "password not null")
    private String email;
    private String password;

    private String phone;

//    @NotNull(message = "")
    private String rePassword;
}