package com.mmu.product_app.repository;

import org.springframework.data.repository.CrudRepository;

import com.mmu.product_app.models.FoodProduct;


public interface ProductRepository extends CrudRepository<FoodProduct,Long> {

    
} 
