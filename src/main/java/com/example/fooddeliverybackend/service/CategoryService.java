package com.example.fooddeliverybackend.service;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.entity.Category;
public interface CategoryService {
    ApiResponse add(Category category);
    ApiResponse delete(Long id);
}
