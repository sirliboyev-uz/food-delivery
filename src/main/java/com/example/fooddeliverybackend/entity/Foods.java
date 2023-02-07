package com.example.fooddeliverybackend.entity;

import com.example.fooddeliverybackend.template.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Foods extends AbstractEntity {
    @Column(nullable = false)
    private String foodName;
    @Column(nullable = false)
    private String quantity;
    @Column(nullable = false)
    private String price;
    @ManyToOne
    private Category category;
}