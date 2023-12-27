package com.mmu.product_app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.models.FoodProductItem;
import com.mmu.product_app.repository.FoodItemRepository;
import com.mmu.product_app.services.FoodItemService;
import com.mmu.product_app.services.FoodProductService;

@RunWith(MockitoJUnitRunner.class)
public class FoodProductItemServiceTest {
    @Mock
    private FoodProductService productService;
    @Mock
    private FoodItemRepository foodItemRepository;

    @InjectMocks
    private FoodItemService foodItemService;

    @Test
    public void testCreateFoodItem() {
        // Create a mock foodProductItem
        FoodProductItem foodProductItem = new FoodProductItem();
        foodProductItem.setId(1L);
        foodProductItem.setExpiryDate(LocalDate.of(2021, 12, 31));
        // Create a mock foodProduct
        FoodProduct foodProduct = new FoodProduct();
        foodProduct.setId(1L);
        foodProduct.setSku("ABC123");
        foodProduct.setDescription("Test Product");
        foodProduct.setCategory("Food");
        foodProduct.setPrice(9.99);
        // Set the foodProduct for the foodProductItem
        foodProductItem.setFoodProduct(foodProduct);
        // Mock the behavior of the productService
        when(foodItemRepository.save(any(FoodProductItem.class))).thenReturn(foodProductItem);
        // Call the method to create the food item
        FoodProductItem response = foodItemService.createFoodProductItem(foodProductItem);
        // Verify the response
        assertNotNull(response);
        assertEquals(foodProductItem.getId(), response.getId());
        assertEquals(foodProductItem.getExpiryDate(), response.getExpiryDate());
        assertEquals(foodProductItem.getFoodProduct(), response.getFoodProduct());
    }

    @Test
    public void testGetAllFoodProductItems() {
        // Create a list of mock food product items
        List<FoodProductItem> foodProductItems = new ArrayList();
        FoodProductItem item1 = new FoodProductItem();
        item1.setId(1L);
        item1.setExpiryDate(LocalDate.of(2021, 12, 31));
        foodProductItems.add(item1);

        FoodProductItem item2 = new FoodProductItem();
        item2.setId(2L);
        item2.setExpiryDate(LocalDate.of(2022, 1, 15));
        foodProductItems.add(item2);
        
        // Mock the behavior of the foodItemRepository
        when(foodItemRepository.findAll()).thenReturn(foodProductItems);
        // Call the method to get all food product items
        List<FoodProductItem> response = foodItemService.getAllFoodItems();
        // Verify the response
        assertNotNull(response);
        assertEquals(foodProductItems.size(), response.size());
        assertEquals(foodProductItems.get(0).getId(), response.get(0).getId());
        assertEquals(foodProductItems.get(0).getExpiryDate(), response.get(0).getExpiryDate());
        assertEquals(foodProductItems.get(1).getId(), response.get(1).getId());
        assertEquals(foodProductItems.get(1).getExpiryDate(), response.get(1).getExpiryDate());
    }

    @Test
    public void testGetFoodProductItem() {
        // Create a mock food product item
        FoodProductItem item = new FoodProductItem();
        item.setId(1L);
        item.setExpiryDate(LocalDate.of(2021, 12, 31));

        // Mock the behavior of the foodItemRepository
        when(foodItemRepository.findById(any(Long.class))).thenReturn(Optional.of(item));

        // Call the method to get the food product item
        FoodProductItem response = foodItemService.getFoodProductItem(1L);

        // Verify the response
        assertNotNull(response);
        assertEquals(item.getId(), response.getId());
        assertEquals(item.getExpiryDate(), response.getExpiryDate());
    }

    @Test
    public void testDeleteFoodProductItem() {
        // Create a mock food product item
        FoodProductItem item = new FoodProductItem();
        item.setId(1L);
        item.setExpiryDate(LocalDate.of(2021, 12, 31));
        // Call the method to delete the food product item
        foodItemService.deleteFoodProductItem(1L);
        verify(foodItemRepository, times(1)).deleteById(1L);

    }
}

