package com.example.fooddeliverybackend.service;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.FoodDto;
import com.example.fooddeliverybackend.entity.Foods;

import java.util.List;

public interface FoodService {
    ApiResponse add(FoodDto foodDTO);
    ApiResponse update(FoodDto foodDTO);
    ApiResponse delete(Long id);
    ApiResponse getFood(Long id);
    List<Foods> getFoods();
}
