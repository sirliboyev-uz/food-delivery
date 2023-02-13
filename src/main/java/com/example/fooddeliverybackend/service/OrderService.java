package com.example.fooddeliverybackend.service;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.OrderDto;
import com.example.fooddeliverybackend.entity.Orders;

import java.util.List;

public interface OrderService {
    ApiResponse add(OrderDto orderDto);
    ApiResponse update();
    ApiResponse delete();
    Orders getOrder(Long id);
    List<Orders> getOrders();
}
