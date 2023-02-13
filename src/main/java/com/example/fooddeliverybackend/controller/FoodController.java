package com.example.fooddeliverybackend.controller;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.FoodDto;
import com.example.fooddeliverybackend.entity.Foods;
import com.example.fooddeliverybackend.entity.utils.Annotation.RoleCheckName;
import com.example.fooddeliverybackend.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodService foodService;
    @RoleCheckName("ADD_FOOD")
    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody FoodDto foodDTO){
        ApiResponse apiResponse=foodService.add(foodDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> add(@PathVariable Long id){
        ApiResponse apiResponse=foodService.delete(id);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        ApiResponse food = foodService.getFood(id);
        return ResponseEntity.ok(food);
    }
    @GetMapping()
    public ResponseEntity<?> getUsers(){
        List<Foods> foods=foodService.getFoods();
        return ResponseEntity.ok(foods);
    }
}
