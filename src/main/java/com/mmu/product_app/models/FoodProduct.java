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

/**
 * Represents a food product in the application.
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foodProduct")
public class FoodProduct {

    /**
     * The unique identifier of the food product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * The stock keeping unit (SKU) of the food product.
     * It is a unique code for each product.
     */
    @Column(unique = true)
    @NonNull
    @NotBlank(message = "SKU is mandatory")
    private String sku;

    /**
     * The description of the food product.
     */
    @Column
    @NonNull
    private String description;

    /**
     * The category of the food product.
     */
    @Column
    @NonNull
    private String category;

    /**
     * The price of the food product.
     */
    @Column
    @Nonnull
    private double price;

    /**
     * The list of food product items associated with this food product.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "foodProduct", cascade = CascadeType.ALL)
    private List<FoodProductItem> items;

    /**
     * Returns a string representation of the food product.
     *
     * @return The string representation of the food product.
     */
    public String toString() {
        return "{ id= " + id + ", sku = '" + sku + "', price = " + price + ", description = " + description + '}';
    }
}
