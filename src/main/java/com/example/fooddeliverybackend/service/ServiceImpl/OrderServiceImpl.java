package com.example.fooddeliverybackend.service.ServiceImpl;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.OrderDto;
import com.example.fooddeliverybackend.entity.Orders;
import com.example.fooddeliverybackend.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public ApiResponse add(OrderDto orderDto) {
        return null;
    }

    @Override
    public ApiResponse update() {
        return null;
    }

    @Override
    public ApiResponse delete() {
        return null;
    }

    @Override
    public Orders getOrder(Long id) {
        return null;
    }

    @Override
    public List<Orders> getOrders() {
        return null;
    }
}
