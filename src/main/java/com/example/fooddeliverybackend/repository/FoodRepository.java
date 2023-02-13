package com.example.fooddeliverybackend.repository;

import com.example.fooddeliverybackend.entity.Foods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Foods, Long> {
    boolean existsByFoodName(String name);
}
