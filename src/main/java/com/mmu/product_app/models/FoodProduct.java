package com.mmu.product_app.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
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
    @NonNull
    private String description;
    
    @Column
    @NonNull
    private String category;
    
    @Column
    @Nonnull
    private double price;
    
    public String toString() {
        return "{ id= " + id + ", sku = '" + sku + "', price = " + price + ", description = " + description + '}';
    }
}
