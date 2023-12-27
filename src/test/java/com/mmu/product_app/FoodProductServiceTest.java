package com.mmu.product_app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mmu.product_app.models.FoodProduct;
import com.mmu.product_app.repository.FoodProductRepository;
import com.mmu.product_app.services.FoodProductService;

@RunWith(MockitoJUnitRunner.class)
public class FoodProductServiceTest {
    @Mock
    private FoodProductRepository foodProductRepository;

    @InjectMocks // auto inject foodProductRepository to foodProductService
    private FoodProductService foodProductService;

    @Test
    public void testCreateFoodProduct() {
        // Create a new FoodProduct object
        FoodProduct foodProduct = new FoodProduct();
        foodProduct.setSku("SKU-001");  
        foodProduct.setDescription("Test Food Product");
        foodProduct.setCategory("Test Category");
        foodProduct.setPrice(10.00);
        
        // Mock the behavior of the foodProductRepository
        when(foodProductRepository.save(any(FoodProduct.class))).thenReturn(foodProduct);
        
        // Call the createFoodProduct method
        FoodProduct createdProduct = foodProductService.createFoodProduct(foodProduct);
        
        // Verify that the product is created successfully
        assertNotNull(createdProduct);
        assertEquals("SKU-001", createdProduct.getSku());
        assertEquals("Test Food Product", createdProduct.getDescription());
        assertEquals("Test Category", createdProduct.getCategory());
        assertEquals(10.00, createdProduct.getPrice(), 0.01);
    }
    @Test
    public void testGetFoodProduct() {
        // Create a mock food product
        FoodProduct foodProduct = new FoodProduct();
        foodProduct.setId(1L);
        foodProduct.setSku("SKU-001");
        foodProduct.setDescription("Test Food Product");
        foodProduct.setCategory("Test Category");
        foodProduct.setPrice(10.00);

        // Mock the behavior of the foodProductRepository
        when(foodProductRepository.findById(1L)).thenReturn(Optional.of(foodProduct));

        // Call the getFoodProduct method
        FoodProduct retrievedProduct = foodProductService.getFoodProduct(1L);

        // Verify that the correct product is retrieved
        assertNotNull(retrievedProduct);
        assertEquals("SKU-001", retrievedProduct.getSku());
        assertEquals("Test Food Product", retrievedProduct.getDescription());
        assertEquals("Test Category", retrievedProduct.getCategory());
        assertEquals(10.00, retrievedProduct.getPrice(), 0.01);
    }

    @Test
    public void testGetAllFoodProducts() {
        // Create a list of mock food products
        List<FoodProduct> foodProducts = new ArrayList();
        FoodProduct foodProduct1 = new FoodProduct();
        foodProduct1.setId(1L);
        foodProduct1.setSku("SKU-001");
        foodProduct1.setDescription("Test Food Product 1");
        foodProduct1.setCategory("Test Category 1");
        foodProduct1.setPrice(10.00);
        foodProducts.add(foodProduct1);

        FoodProduct foodProduct2 = new FoodProduct();
        foodProduct2.setId(2L);
        foodProduct2.setSku("SKU-002");
        foodProduct2.setDescription("Test Food Product 2");
        foodProduct2.setCategory("Test Category 2");
        foodProduct2.setPrice(20.00);
        foodProducts.add(foodProduct2);

        // Mock the behavior of the foodProductRepository
        when(foodProductRepository.findAll()).thenReturn(foodProducts);

        // Call the getAllFoodProducts method
        List<FoodProduct> retrievedProducts = foodProductService.getAllFoodProducts();

        // Verify that all products are retrieved
        assertNotNull(retrievedProducts);
        assertEquals(2, retrievedProducts.size());
        assertEquals("SKU-001", retrievedProducts.get(0).getSku());
        assertEquals("Test Food Product 1", retrievedProducts.get(0).getDescription());
        assertEquals("Test Category 1", retrievedProducts.get(0).getCategory());
        assertEquals(10.00, retrievedProducts.get(0).getPrice(), 0.01);
        assertEquals("SKU-002", retrievedProducts.get(1).getSku());
        assertEquals("Test Food Product 2", retrievedProducts.get(1).getDescription());
        assertEquals("Test Category 2", retrievedProducts.get(1).getCategory());
        assertEquals(20.00, retrievedProducts.get(1).getPrice(), 0.01);
    }

@Test
public void testUpdateFoodProduct() {
    // Create a mock food product
    FoodProduct foodProduct = new FoodProduct();
    foodProduct.setId(1L);
    foodProduct.setSku("SKU-001");
    foodProduct.setDescription("Test Food Product");
    foodProduct.setCategory("Test Category");
    foodProduct.setPrice(10.00);

    // Mock the behavior of the foodProductRepository
    when(foodProductRepository.findById(1L)).thenReturn(Optional.of(foodProduct));
    when(foodProductRepository.save(any(FoodProduct.class))).thenReturn(foodProduct);

    // Call the updateFoodProduct method
    FoodProduct updatedProduct = foodProductService.updateFoodProduct(1L, foodProduct);

    // Verify that the product is updated
    assertNotNull(updatedProduct);
    assertEquals("SKU-001", updatedProduct.getSku());
    assertEquals("Test Food Product", updatedProduct.getDescription());
    assertEquals("Test Category", updatedProduct.getCategory());
    assertEquals(10.00, updatedProduct.getPrice(), 0.01);
}

@Test
public void testDeleteFoodProduct() {
    // Create a mock food product
    FoodProduct foodProduct = new FoodProduct();
    foodProduct.setId(1L);
    foodProduct.setSku("SKU-001");
    foodProduct.setDescription("Test Food Product");
    foodProduct.setCategory("Test Category");
    foodProduct.setPrice(10.00);

    // Call the deleteProduct method
    foodProductService.deleteProduct(1L);

    // Verify that the product is deleted
    verify(foodProductRepository, times(1)).deleteById(1L);
}
}
