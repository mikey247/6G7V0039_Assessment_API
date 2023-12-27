package com.mmu.product_app.services;

import com.mmu.product_app.models.FoodProductItem;
import com.mmu.product_app.repository.FoodItemRepository;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;

    public FoodProductItem createFoodProductItem(FoodProductItem foodProductItem){
        return foodItemRepository.save(foodProductItem);
    }

    public FoodProductItem getFoodProductItem(Long foodProductItemId){
        return foodItemRepository.findById(foodProductItemId).orElse(null);
    }

    public List<FoodProductItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public void deleteFoodProductItem(Long foodProductItemId){
        foodItemRepository.deleteById(foodProductItemId);
    }

}
