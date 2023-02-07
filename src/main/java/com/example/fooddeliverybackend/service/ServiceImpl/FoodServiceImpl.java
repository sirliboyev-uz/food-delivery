package com.example.fooddeliverybackend.service.ServiceImpl;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.FoodDTO;
import com.example.fooddeliverybackend.service.FoodService;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {
    @Override
    public ApiResponse addFood(FoodDTO foodDTO) {
        return null;
    }

    @Override
    public ApiResponse deleteProduct(Long id) {
        return null;
    }
}
