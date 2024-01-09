package com.mmu.product_app.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.NonNull;


/**
 * Represents a customer in the product application.
 * This class contains information about the customer's business name, telephone number, and address.
 *
 * <p>
 * The {@code Customer} class provides methods to access and modify the customer's information.
 * It also includes annotations for database mapping and table configuration.
 * </p>
 *
 * <p>
 * The customer's information includes:
 * <ul>
 *     <li>Business name</li>
 *     <li>Telephone number</li>
 *     <li>Address</li>
 * </ul>
 * </p>
 *
 * <p>
 * The {@code Customer} class is annotated with {@code @Entity} to indicate that it is a JPA entity.
 * It is also annotated with {@code @Table} to specify the name of the database table for this entity.
 * </p>
 *
 * <p>
 * The class includes getters and setters for all the properties, as well as constructors for creating instances of the class.
 * </p>
 */
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    
    /**
     * The unique identifier of the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * The business name of the customer.
     */
    @Column
    @NonNull
    private String businessName;

    /**
     * The telephone number of the customer.
     */
    @Column
    private String telephoneNumber;

    /**
     * The address of the customer.
     */
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    /**
     * Returns a string representation of the Customer object.
     * The string includes the values of all fields, including the Address object.
     *
     * @return a string representation of the Customer object
     */
    public String toString() {
        return "{id=  " + id + ", businessName='" + businessName + "', telephoneNumber=" + telephoneNumber + ", address=" + address + '}';
    }
}


