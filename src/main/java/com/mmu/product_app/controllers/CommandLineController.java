package com.mmu.product_app.controllers;

import org.springframework.stereotype.Component;

import com.mmu.product_app.services.ProductService;
import com.mmu.product_app.models.*;
import com.mmu.product_app.repository.ProductRepository;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineController {
    ProductService productService;
    ProductRepository productRepository;

    public CommandLineController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public int showMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("\t\t The Food Store");
        System.out.println("Choose from the following options: \n");
        System.out.println("------------------------------------------------------\n");
        System.out.println("[1] List all products");
        System.out.println("[2] Search for product by ID");
        System.out.println("[3] Add a new product");
        System.out.println("[4] Update product by ID");
        System.out.println("[5] Delete product by ID");
        System.out.println("[6] Exit");
        int option = scanner.nextInt(); 
        System.out.println(option);
        return option;
    }

    public void getAll(){
        System.out.println("Get all");
        List<FoodProduct> products = productService.getAllFoodProducts();
        for (int index = 0; index < products.size(); index++) {
            System.out.println(products.get(index).toString());
        }
    }
     public void getOne(){
        System.out.println("Get one");

    }
     public void createProduct(){
        System.out.println("create product");
        Scanner scanner = new Scanner(System.in);

        FoodProduct product = new FoodProduct();
        // System.out.println("Let's create a product!");
        // System.out.println("First of all, I'd like to know your business name.");
        // System.out.println("Business name: ");
        // String businessName = scanner.nextLine();
        // Customer business = customerRepository.findByBusinessName(businessName).orElseThrow(
        //         () -> new IllegalStateException("Oops, you might have entered a wrong business name. Lets try again.")
        // );
        System.out.println("Description: ");
        String description = scanner.nextLine();
        product.setDescription(description);

        System.out.println("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        product.setPrice(price);

        System.out.println("SKU: ");
        String sku = scanner.nextLine();
        product.setSku(sku);

        System.out.println("Category: ");
        String category = scanner.nextLine();
        product.setCategory(category);

        FoodProduct newFoodProduct = productService.createFoodProduct(product);
        System.out.println(newFoodProduct.toString());
    }
     
     public void updateProduct(){
        System.out.println("update product");
    }
     public void deleteProduct(){
        System.out.println("delete product");
    }
}
