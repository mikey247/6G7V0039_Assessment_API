/**
 * The CustomerRepository interface is responsible for providing CRUD operations
 * for the Customer entity using Spring Data JPA.
 * 
 * <p>
 * This interface extends the JpaRepository interface, which provides generic
 * CRUD operations and advanced querying capabilities for the Customer entity.
 * It is annotated with the {@link org.springframework.data.jpa.repository.JpaRepository}
 * annotation to indicate that it is a repository interface.
 * </p>
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.mmu.product_app.models.Customer
 */
package com.mmu.product_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;

import com.mmu.product_app.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
