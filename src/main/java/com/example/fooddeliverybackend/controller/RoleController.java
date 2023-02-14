package com.example.fooddeliverybackend.controller;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.RoleRegisterDto;
import com.example.fooddeliverybackend.entity.utils.Annotation.RoleCheckName;
import com.example.fooddeliverybackend.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    @Autowired
    RoleService roleService;

    @RoleCheckName(value = "ADD_ROLE")
    @PostMapping("/register")
    public HttpEntity<?> create(@Valid @RequestBody RoleRegisterDto roleRegisterDTO){
        ApiResponse apiResponse = roleService.register(roleRegisterDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
    @RoleCheckName(value = "EDIT_ROLE")
    @PutMapping("/update/{id}")
    public HttpEntity<?> update(@PathVariable Long id, @Valid @RequestBody RoleRegisterDto roleRegisterDTO){
        ApiResponse apiResponse=roleService.update(id, roleRegisterDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
    @RoleCheckName(value = "DELETE_ROLE")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse=roleService.delete(id);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }

    @RoleCheckName(value = "READ_ROLE")
    @GetMapping("/select/{id}")
    public HttpEntity<?> select(@PathVariable Long id){
        ApiResponse apiResponse=roleService.role(id);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getType()?apiResponse.getMessage()+"\n"+apiResponse.getObject():apiResponse.getMessage());
    }

    @RoleCheckName(value = "READ_ROLE")
    @GetMapping("/select")
    public HttpEntity<?> select(){
        ApiResponse apiResponse=roleService.roles();
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage()+"\n"+apiResponse.getObject());
    }
}
