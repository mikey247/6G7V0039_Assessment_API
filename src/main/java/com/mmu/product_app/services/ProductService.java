package com.mmu.product_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.repository.ProductRepository;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public FoodProduct createFoodProduct(FoodProduct product){
        FoodProduct newProduct = productRepository.save(product);
        return newProduct;
    }

    public FoodProduct getFoodProduct(Long productId){
        return productRepository.findById(productId)
        .orElseThrow(() -> new IllegalStateException("Food Product with id " + productId + " does not exist"));
    }

    public List<FoodProduct> getAllFoodProducts(){
        return (List<FoodProduct>)productRepository.findAll();
    }

    public FoodProduct updateFoodProduct(Long productId, FoodProduct newProduct){
        Optional<FoodProduct>  product = productRepository.findById(productId);
        if (product.isPresent()) {
            FoodProduct existingFoodProduct = product.get();
            if (!newProduct.getDescription().isEmpty()) {
                existingFoodProduct.setDescription(newProduct.getDescription());
            }
            if (!newProduct.getCategory().isEmpty()) {
                existingFoodProduct.setCategory(newProduct.getCategory());
            }
            if (!newProduct.getSku().isEmpty()) {
                existingFoodProduct.setSku(newProduct.getSku());
            }
            if (newProduct.getPrice() > 0) {
                existingFoodProduct.setPrice(newProduct.getPrice());
            }
            return productRepository.save(existingFoodProduct);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found");
        }
//        return product.orElse(null);
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
    
}
