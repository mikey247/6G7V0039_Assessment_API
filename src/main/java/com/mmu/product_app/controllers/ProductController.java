package com.mmu.product_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.services.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/product")
public class ProductController {
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<FoodProduct> createProduct( @RequestBody FoodProduct product){
        FoodProduct newProduct = productService.createFoodProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }  

    @PutMapping("/update/{productId}")
    public ResponseEntity<FoodProduct> updateProduct(@PathVariable Long productId, @RequestBody FoodProduct product){
        FoodProduct updateFoodProduct = productService.updateFoodProduct(productId, product);
        return new ResponseEntity<>(updateFoodProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<HttpStatus> deleteStudent (@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<FoodProduct>> getAllProducts(){
        productService.getAllFoodProducts();
        return new ResponseEntity<>(productService.getAllFoodProducts(), HttpStatus.OK);
    }
    @GetMapping("/get/{productId}")
    public ResponseEntity<FoodProduct> getOneProduct(@PathVariable Long productId){
       FoodProduct product = productService.getFoodProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
