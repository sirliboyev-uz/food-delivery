package com.example.fooddeliverybackend.controller;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.EmployeeDTO;
import com.example.fooddeliverybackend.entity.Category;
import com.example.fooddeliverybackend.repository.CategoryRepository;
import com.example.fooddeliverybackend.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Category category){
        ApiResponse apiResponse=categoryService.add(category);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage()+"\n"+apiResponse.getObject());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> add(@PathVariable Long id){
        ApiResponse apiResponse=categoryService.delete(id);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage()+"\n"+apiResponse.getObject());
    }
}
