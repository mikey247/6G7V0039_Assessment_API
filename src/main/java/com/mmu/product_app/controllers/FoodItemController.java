package com.mmu.product_app.controllers;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.models.FoodProductItem;
import com.mmu.product_app.services.FoodItemService;
import com.mmu.product_app.services.FoodProductService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/food-item")
@CrossOrigin({"http://localhost:5173/", "https://6g7v0039-assessment-frontend.vercel.app/"})
public class FoodItemController {
    final private FoodProductService productService;
    final private FoodItemService foodItemService;

    /**
     * Create a new food item.
     *
     * @return ResponseEntity with a success message and HTTP status code 201 (Created)
     */
    @PostMapping("/create")
    public ResponseEntity<String> createFoodItem (){
       return new ResponseEntity<>("Right here", HttpStatus.CREATED);
    }

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
}
