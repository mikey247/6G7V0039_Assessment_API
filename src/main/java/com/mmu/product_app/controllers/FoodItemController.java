package com.mmu.product_app.controllers;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.models.FoodProductItem;
import com.mmu.product_app.services.FoodItemService;
import com.mmu.product_app.services.FoodProductService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller class for managing food item-related operations.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/food-item")
@CrossOrigin({"http://localhost:5173/", "https://6g7v0039-assessment-frontend.vercel.app/"})
public class FoodItemController {
    final private FoodProductService productService;
    final private FoodItemService foodItemService;

    /**
     * Create a new food item for a specific food product.
     *
     * @param foodProductItem The food product item to be created
     * @param foodProductId The ID of the food product
     * @return ResponseEntity with the created food product item and HTTP status code 201 (Created),
     *         or an error message and HTTP status code 500 (Internal Server Error) if an exception occurs
     */
    @PostMapping("/create/{foodProductId}")
    public ResponseEntity<FoodProductItem> createFoodItem (@RequestBody FoodProductItem foodProductItem, @PathVariable Long foodProductId){
        try{
            FoodProduct foodProduct = productService.getFoodProduct(foodProductId);

            foodProductItem.setFoodProduct(foodProduct);

            FoodProductItem newFoodProductItem = foodItemService.createFoodProductItem(foodProductItem);
            System.out.println(newFoodProductItem);
            System.out.println("Food Product Item Created");
            return new ResponseEntity<>(newFoodProductItem, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a specific food item by its ID.
     *
     * @param foodItemId The ID of the food item
     * @return ResponseEntity with the requested food item and HTTP status code 200 (OK),
     *         or an error message and HTTP status code 500 (Internal Server Error) if an exception occurs
     */
    @GetMapping("/{foodItemId}")
    public ResponseEntity<FoodProductItem> getFoodItem(@PathVariable Long foodItemId) {
        try {
            FoodProductItem foodItem = foodItemService.getFoodProductItem(foodItemId);
            return new ResponseEntity<>(foodItem, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all food items.
     *
     * @return ResponseEntity with the list of all food items and HTTP status code 200 (OK),
     *         or an error message and HTTP status code 500 (Internal Server Error) if an exception occurs
     */
    @GetMapping("/all")
    public ResponseEntity<List<FoodProductItem>> getAllFoodItems() {
        try {
            List<FoodProductItem> foodItems = foodItemService.getAllFoodItems();
            return new ResponseEntity<>(foodItems, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update a specific food item.
     *
     * @param foodItemId The ID of the food item to be updated
     * @param updatedFoodItem The updated food item
     * @return ResponseEntity with the updated food item and HTTP status code 200 (OK),
     *         or an error message and HTTP status code 500 (Internal Server Error) if an exception occurs
     */
    @PutMapping("/update/{foodItemId}")
    public ResponseEntity<FoodProductItem> updateFoodItem(@PathVariable Long foodItemId, @RequestBody FoodProductItem updatedFoodItem) {
        try {
            FoodProductItem foodItem = foodItemService.getFoodProductItem(foodItemId);
            if (foodItem == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            foodItem.setSerialNumber(updatedFoodItem.getSerialNumber());
            foodItem.setExpiryDate(updatedFoodItem.getExpiryDate());

            FoodProductItem updatedItem = foodItemService.updateFoodProductItem(foodItem);
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete a specific food item.
     *
     * @param foodItemId The ID of the food item to be deleted
     * @return ResponseEntity with the deleted food item and HTTP status code 200 (OK),
     *         or an error message and HTTP status code 500 (Internal Server Error) if an exception occurs
     */
    @DeleteMapping("/delete/{foodItemId}")
    public ResponseEntity<FoodProductItem> deleteFoodItem(@PathVariable Long foodItemId) {
        try {
            FoodProductItem foodItem = foodItemService.getFoodProductItem(foodItemId);
            if (foodItem == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            foodItemService.deleteFoodProductItem(foodItemId);
            return new ResponseEntity<>(foodItem, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
