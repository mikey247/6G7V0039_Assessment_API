package com.mmu.product_app.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    
    @Column(unique = true)
    @NonNull
    @NotBlank(message = "SKU is mandatory")
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

    @JsonIgnore
    @OneToMany(mappedBy = "foodProduct", cascade = CascadeType.ALL)
    private List<FoodProductItem> items;
    
    public String toString() {
        return "{ id= " + id + ", sku = '" + sku + "', price = " + price + ", description = " + description + '}';
    }
}
