package com.example.fooddeliverybackend.entity;

import com.example.fooddeliverybackend.template.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Orders extends AbstractEntity {
    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private float cost;

    @Column(nullable = false)
    private String[] items;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private boolean status;
}
