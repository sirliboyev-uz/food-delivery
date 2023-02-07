package com.example.fooddeliverybackend.service;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.FoodDTO;

public interface FoodService {
    public ApiResponse addFood(FoodDTO foodDTO);
    public ApiResponse deleteProduct(Long id);
}
