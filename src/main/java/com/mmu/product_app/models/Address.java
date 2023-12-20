package com.mmu.product_app.models;

import jakarta.persistence.*;
import lombok.*;

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

    // generate a toString() method for the class taking into account all fields
    public String toString() {
        return "{ id = " + id + ", addressLine1='" + addressLine1 + "', addressLine2 = " + addressLine2 + ", addressLine3 = " + addressLine3 + ", country = " + country + ", postCode= " + postCode + '}';
    }
}
