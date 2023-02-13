package com.example.fooddeliverybackend.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String orderId;
    private float cost;
    private String[] items;
    private String userId;
    private boolean status;
}
