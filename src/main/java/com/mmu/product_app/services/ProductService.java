package com.mmu.product_app.services;

import org.springframework.stereotype.Service;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.repository.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;

    public FoodProduct createFoodProduct(FoodProduct foodProduct){
        FoodProduct product = productRepository.save(foodProduct);
        return product;
    }
    
}
