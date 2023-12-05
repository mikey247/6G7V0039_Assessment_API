package com.mmu.product_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.repository.ProductRepository;


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

    public Optional<FoodProduct> getFoodProduct(Long productId){
        Optional<FoodProduct> product =  productRepository.findById(productId);
        return product;
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
            if (newProduct.getPrice() > 0) {
                existingFoodProduct.setPrice(newProduct.getPrice());
            }
            productRepository.save(existingFoodProduct);
        } else {
            return null;
        }
        return !product.isEmpty() ? product.get() : null;
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
    
}
