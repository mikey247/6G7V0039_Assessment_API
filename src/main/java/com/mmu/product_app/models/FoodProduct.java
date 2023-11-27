package com.mmu.product_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
// spring.datasource.url=jdbc:sqlite:src/main/resources/foodStore.sqlite
// url=jdbc:sqlite:src/main/resources/foodStore.sqlite
// hibernate.connection.url=jdbc:sqlite:src/main/resources/foodStore.sqlite
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foodProduct")
public class FoodProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NonNull
    private String sku; //stock keeping unit (a unique code for each product)
    
    @Column
    private String description;
    
    @Column
    @NonNull
    private String category;
    
    @Column
    private int price;
}
