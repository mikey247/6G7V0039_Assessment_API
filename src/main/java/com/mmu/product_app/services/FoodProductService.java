package com.mmu.product_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.repository.FoodProductRepository;
import org.springframework.web.server.ResponseStatusException;

/**
 * The FoodProductService class provides methods to perform CRUD operations on FoodProduct objects.
 * It interacts with the FoodProductRepository to access and manipulate the data.
 */
@Service
public class FoodProductService {

    private final FoodProductRepository productRepository;

    /**
     * Constructs a FoodProductService object with the specified FoodProductRepository.
     *
     * @param productRepository The FoodProductRepository used to access the data.
     */
    @Autowired
    public FoodProductService(FoodProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a new FoodProduct.
     *
     * @param product The FoodProduct object to be created.
     * @return The newly created FoodProduct object.
     */
    public FoodProduct createFoodProduct(FoodProduct product){
        FoodProduct newProduct = productRepository.save(product);
        return newProduct;
    }

    /**
     * Retrieves a FoodProduct by its ID.
     *
     * @param productId The ID of the FoodProduct to retrieve.
     * @return The FoodProduct object with the specified ID.
     * @throws IllegalStateException If the FoodProduct with the specified ID does not exist.
     */
    public FoodProduct getFoodProduct(Long productId){
        return productRepository.findById(productId)
        .orElseThrow(() -> new IllegalStateException("Food Product with id " + productId + " does not exist"));
    }

    /**
     * Retrieves all FoodProducts.
     *
     * @return A list of all FoodProduct objects.
     */
    public List<FoodProduct> getAllFoodProducts(){
        return (List<FoodProduct>)productRepository.findAll();
    }

    /**
     * Updates a FoodProduct with the specified ID.
     *
     * @param productId The ID of the FoodProduct to update.
     * @param newProduct The updated FoodProduct object.
     * @return The updated FoodProduct object.
     * @throws ResponseStatusException If the FoodProduct with the specified ID does not exist.
     */
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
    }

    /**
     * Deletes a FoodProduct with the specified ID.
     *
     * @param productId The ID of the FoodProduct to delete.
     */
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
    
}
