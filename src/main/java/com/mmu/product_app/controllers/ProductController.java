package com.mmu.product_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.services.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<FoodProduct> createCourse( @RequestBody FoodProduct product){
        FoodProduct newProduct = productService.createFoodProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }  
}
