package com.mmu.product_app.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * The Address class represents a physical address.
 * It is used as a model for storing address information in the application.
 */
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @NonNull
    @Column
    String addressLine1;

    @Column
    String addressLine2;

    @Column
    String addressLine3;

    @NonNull
    @Column
    String country;

    @NonNull
    @Column
    String postCode;

    /**
     * Returns a string representation of the Address object.
     * The string includes the values of all fields in the object.
     *
     * @return a string representation of the Address object
     */
    public String toString() {
        return "{ id = " + id + ", addressLine1='" + addressLine1 + "', addressLine2 = " + addressLine2 + ", addressLine3 = " + addressLine3 + ", country = " + country + ", postCode= " + postCode + '}';
    }
}
