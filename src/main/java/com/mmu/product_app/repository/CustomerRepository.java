package com.mmu.product_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;

import com.mmu.product_app.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
