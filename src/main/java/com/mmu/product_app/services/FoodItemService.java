package com.mmu.product_app.services;

import com.mmu.product_app.models.FoodProductItem;
import com.mmu.product_app.repository.FoodItemRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;

    public FoodProductItem createFoodProductItem(FoodProductItem foodProductItem){
        return foodItemRepository.save(foodProductItem);
    }
}
