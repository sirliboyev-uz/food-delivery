package com.example.fooddeliverybackend.controller;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.EmployeeDto;
import com.example.fooddeliverybackend.entity.utils.Annotation.RoleCheckName;
import com.example.fooddeliverybackend.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @RoleCheckName(value = "ADD_USER")
    @PostMapping("/add")
    public ResponseEntity<?> insertEmployee(@Valid @RequestBody EmployeeDto dto){
        ApiResponse apiResponse=employeeService.empAdd(dto);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage()+"\n"+apiResponse.getObject());
    }

    @RoleCheckName(value = "EDIT_USER")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDto dto){
        ApiResponse apiResponse=employeeService.empUpdate(id,dto);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }

    @RoleCheckName(value = "DELETE_USER")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        ApiResponse apiResponse=employeeService.empDelete(id);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }

    @RoleCheckName(value = "READ_ROLE")
    @GetMapping("/select")
    public ResponseEntity<?> selectEmployee(){
        ApiResponse apiResponse=employeeService.empSelect();
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage()+"\n"+apiResponse.getObject());
    }
    @GetMapping(value = "/emailConfirm")
    public ResponseEntity<?> confirm(@RequestParam String emailCode, @RequestParam String email){
        ApiResponse apiResponse=employeeService.confirm(email,emailCode);
        return ResponseEntity.status(apiResponse.getType()?201:409).body(apiResponse.getMessage());
    }
}
