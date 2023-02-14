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
public class FoodPicSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    private String contentType;

    private float fileSize;
    @Column(nullable = false)
    private byte[] bytes;
    @OneToOne
    Foods foods;
}
