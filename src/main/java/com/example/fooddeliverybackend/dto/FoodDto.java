package com.example.fooddeliverybackend.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Data
public class FoodDto {
    private String foodName;
    private String foodCategory;
    private float foodPrice;
    MultipartFile file;
}
