package com.mmu.product_app.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Represents a food product item.
 * This class is an entity mapped to the "foodProductItem" table in the database.
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foodProductItem", uniqueConstraints={
    @UniqueConstraint(columnNames = {"product_id"})
})
public class FoodProductItem {

    /**
     * The unique identifier of the food product item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * The serial number of the food product item.
     */
    @Column
    @NonNull
    private String serialNumber;

    /**
     * The expiry date of the food product item.
     */
    @Column
    private LocalDate expiryDate;
    
    /**
     * The food product associated with the food product item.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private FoodProduct foodProduct; // Reference to FoodProduct

    /**
     * Returns a string representation of the food product item.
     *
     * @return a string representation of the food product item.
     */
    @Override
    public String toString() {
        return "FoodProductItem{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", foodProduct=" + foodProduct +
                '}';
    }
}