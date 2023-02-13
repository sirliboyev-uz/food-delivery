package com.example.fooddeliverybackend.dto;

import lombok.Data;

@Data
public class FoodDto {
    private String foodName;
    private String foodCategory;
    private float foodPrice;
    private float foodQuantity;
}
