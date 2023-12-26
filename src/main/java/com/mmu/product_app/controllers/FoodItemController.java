package com.mmu.product_app.controllers;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.models.FoodProductItem;
import com.mmu.product_app.services.FoodItemService;
import com.mmu.product_app.services.ProductService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/food-item")
@CrossOrigin({"http://localhost:5173/", "https://6g7v0039-assessment-frontend.vercel.app/"})
public class FoodItemController {
    final private ProductService productService;
    final private FoodItemService foodItemService;

    @PostMapping("/create")
    public ResponseEntity<String> createFoodItem (){
       return new ResponseEntity<>("Right here", HttpStatus.CREATED);
    }

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
