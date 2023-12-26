package com.mmu.product_app.models;

import java.time.LocalDate;

import jakarta.persistence.*;
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
@Table(name = "foodProductItem", uniqueConstraints={
    @UniqueConstraint(columnNames = {"product_id"})
})
public class FoodProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NonNull
    private String serialNumber;

    @Column
    private LocalDate expiryDate;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private FoodProduct foodProduct; // Reference to FoodProduct

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