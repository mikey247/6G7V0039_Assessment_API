package com.mmu.product_app.services;

import com.mmu.product_app.models.FoodProductItem;
import com.mmu.product_app.repository.FoodItemRepository;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * The FoodItemService class is responsible for handling operations related to food product items.
 * It provides methods for creating, retrieving, updating, and deleting food product items.
 */
@Service
@AllArgsConstructor
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;

    /**
     * Creates a new food product item.
     *
     * @param foodProductItem The food product item to be created.
     * @return The created food product item.
     */
    public FoodProductItem createFoodProductItem(FoodProductItem foodProductItem){
        return foodItemRepository.save(foodProductItem);
    }

    /**
     * Retrieves a food product item by its ID.
     *
     * @param foodProductItemId The ID of the food product item to retrieve.
     * @return The retrieved food product item, or null if not found.
     */
    public FoodProductItem getFoodProductItem(Long foodProductItemId){
        return foodItemRepository.findById(foodProductItemId).orElse(null);
    }

    /**
     * Retrieves all food product items.
     *
     * @return A list of all food product items.
     */
    public List<FoodProductItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    /**
     * Deletes a food product item by its ID.
     *
     * @param foodProductItemId The ID of the food product item to delete.
     */
    public void deleteFoodProductItem(Long foodProductItemId){
        foodItemRepository.deleteById(foodProductItemId);
    }

}
