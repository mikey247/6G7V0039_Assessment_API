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
import org.springframework.web.server.ResponseStatusException;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.services.FoodProductService;
import com.mmu.product_app.utils.HttpMessage;

import lombok.AllArgsConstructor;

/**
 * Controller class for managing food products.
 */
@AllArgsConstructor
@RestController
@CrossOrigin({"http://localhost:5173/", "https://6g7v0039-assessment-frontend.vercel.app/"})
@RequestMapping("/product")
public class FoodProductController {
    FoodProductService foodProductService;

    /**
     * Create a new food product.
     *
     * @param product the food product to create
     * @return the created food product
     */
    @PostMapping("/create")
    public ResponseEntity<HttpMessage> createProduct(@RequestBody FoodProduct product) {
        FoodProduct newProduct = foodProductService.createFoodProduct(product);
        System.out.println(newProduct);
        if (newProduct == null) {
            return new ResponseEntity<>(new HttpMessage(HttpStatus.BAD_REQUEST.value(),"Failed to create product"), HttpStatus.BAD_REQUEST);
            // return new ResponseEntity<>("Failed to create product", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new HttpMessage(HttpStatus.CREATED.value(), "Product created successfully"), HttpStatus.CREATED);
    }

    /**
     * Update an existing food product.
     *
     * @param productId the ID of the product to update
     * @param product   the updated food product
     * @return the updated food product
     */
    @PutMapping("/update/{productId}")
    public ResponseEntity<FoodProduct> updateProduct(@PathVariable Long productId, @RequestBody FoodProduct product) {
        FoodProduct updateFoodProduct = foodProductService.updateFoodProduct(productId, product);
        return new ResponseEntity<>(updateFoodProduct, HttpStatus.CREATED);
    }

    /**
     * Delete a food product.
     *
     * @param productId the ID of the product to delete
     * @return HTTP status indicating the success of the deletion
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long productId) {
        foodProductService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get all food products.
     *
     * @return a list of all food products
     */
    @GetMapping("/get/all")
    public ResponseEntity<List<FoodProduct>> getAllProducts() {
        return new ResponseEntity<>(foodProductService.getAllFoodProducts(), HttpStatus.OK);
    }

    /**
     * Get a specific food product by ID.
     *
     * @param productId the ID of the product to retrieve
     * @return the requested food product
     */
    @GetMapping("/get/{productId}")
    public ResponseEntity<FoodProduct> getOneProduct(@PathVariable Long productId) {
        FoodProduct product = foodProductService.getFoodProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
