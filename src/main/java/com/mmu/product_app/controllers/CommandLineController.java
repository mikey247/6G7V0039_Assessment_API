package com.mmu.product_app.controllers;

import org.springframework.stereotype.Component;

import com.mmu.product_app.services.CustomerService;
import com.mmu.product_app.services.FoodProductService;
import com.mmu.product_app.models.*;
import com.mmu.product_app.repository.CustomerRepository;
import com.mmu.product_app.repository.FoodProductRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.mmu.product_app.models.Address;

/**
 * The CommandLineController class represents a command-line interface controller for the product application.
 * It provides methods to interact with the user and perform various operations on products and customers.
 */
@Component
public class CommandLineController {
    FoodProductService productService;
    FoodProductRepository productRepository;
    CustomerService customerService;
    CustomerRepository customerRepository;

    /**
     * Constructs a CommandLineController object with the specified dependencies.
     * 
     * @param productRepository the repository for food products
     * @param foodProductService the service for food products
     * @param customerService the service for customers
     * @param customerRepository the repository for customers
     */
    public CommandLineController(FoodProductRepository productRepository, FoodProductService foodProductService, CustomerService customerService, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.productService = foodProductService;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    /**
     * Displays the main menu and prompts the user to choose an option.
     * 
     * @return the selected option
     */
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
        System.out.println("[6] List all customers");
        System.out.println("[7] Search for customer by ID");
        System.out.println("[8] Add a new customer");
        System.out.println("[9] Update customer by ID");
        System.out.println("[10] Delete customer by ID");
        System.out.println("[11] Exit");
        int option = scanner.nextInt(); 
        return option;
    }

    /**
     * Retrieves and displays all food products.
     */
    public void getAllProducts(){
        System.out.println("Get all");
        List<FoodProduct> products = productService.getAllFoodProducts();
        for (int index = 0; index < products.size(); index++) {
            System.out.println(products.get(index).toString());
        }
    }
    
    /**
     * Retrieves and displays a specific food product based on the provided ID.
     */
    public void getOneProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id");
        try {
            Long id = scanner.nextLong();
            FoodProduct product = productService.getFoodProduct(id);
    
            if (product != null) {
                System.out.println(product);
            } else {
                System.out.println("Product not found");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid product ID.");
            scanner.nextLine(); // Consume the invalid input
        }catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Print the detailed error message
        }
        scanner.nextLine();
    }

    /**
     * Creates a new food product based on user input.
     */
    public void createProduct(){
        System.out.println("create product");
        Scanner scanner = new Scanner(System.in);
        FoodProduct product = new FoodProduct();
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
     
    /**
     * Updates an existing food product based on user input.
     */
    public void updateProduct(){
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("Let's update the product!");
            System.out.println("Id of product: ");
            Long id = scanner.nextLong();
            scanner.nextLine(); //consume \n
            FoodProduct product = productService.getFoodProduct(id);
            if(product != null) {
                System.out.println("Old Description: "+product.getDescription());
                System.out.print("New Description: ");
                String Description = scanner.nextLine();

                System.out.println("Old SKU: "+product.getSku());
                System.out.print("New SKU: ");
                String SKU = scanner.nextLine();

                System.out.println("Old Price: "+ product.getPrice());
                System.out.print("New Price (required due to scanner.nextDouble behaviour): ");
                double price = scanner.nextDouble();
                scanner.nextLine(); //consume \n

                if(!Description.isEmpty()){
                    product.setDescription(Description);
                }
                if(!SKU.isEmpty()){
                    product.setSku(SKU);
                }
                if((price > 0)){
                    product.setPrice(price);
                }
                productRepository.save(product);
                System.out.println("Your product has been updated!");

            }else {
                System.out.println("Product NOT FOUND!!!");
            }
            scanner.nextLine();   
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Print the detailed error message
        }
    }

    /**
     * Deletes a food product based on the provided ID.
     */
    public void deleteProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id");
        Long id = scanner.nextLong();
        productService.deleteProduct(id);
        System.out.println("Product Deleted");    
    }

    /**
     * Retrieves and displays all customers.
     */
    public void getAllCustomers(){
        List<Customer> customers = customerService.getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    /**
     * Retrieves and displays a specific customer based on the provided ID.
     */
    public void getCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer ID: ");
        Long customerId = scanner.nextLong();
        Customer customer = customerService.getCustomer(customerId);
        if (customer != null) {
            System.out.println(customer.toString());
        } else {
            System.out.println("Customer not found.");
        }
    }

    /**
     * Creates a new customer based on user input.
     */
    public void createCustomer(){
        System.out.println("create customer");
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer(); 
        System.out.println("Let's create a customer!");
        System.out.println("First of all, I'd like to know your business name.");
        System.out.println("Business name: ");
        String businessName = scanner.nextLine();
        customer.setBusinessName(businessName);

        System.out.println("Telephone Number: ");
        String telephoneNumber = scanner.nextLine();
        customer.setTelephoneNumber(telephoneNumber);

        Address address = new Address();
        System.out.println("Address Line 1: ");
        String addressLine1 = scanner.nextLine();
        address.setAddressLine1(addressLine1);

        System.out.println("Address Line 2: ");
        String addressLine2 = scanner.nextLine();
        address.setAddressLine2(addressLine2);

        System.out.println("Address Line 3: ");
        String addressLine3 = scanner.nextLine();
        address.setAddressLine3(addressLine3);

        System.out.println("Post Code ");
        String postCode = scanner.nextLine();
        address.setPostCode(postCode); 

        System.out.println("Country: ");
        String country = scanner.nextLine();
        address.setCountry(country);

        customer.setAddress(address);

        Customer newCustomer = customerService.createCustomer(customer);
        System.out.println(newCustomer.toString());
    }

    /**
     * Updates an existing customer based on user input.
     */
    public void updateCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer ID: ");
        Long customerId = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character

        Customer customer = customerService.getCustomer(customerId);
        if (customer != null) {
            System.out.println("Enter new business name: ");
            String businessName = scanner.nextLine();
            if (!businessName.trim().isEmpty()) {
                customer.setBusinessName(businessName);
            }

            System.out.println("Enter new telephone number: ");
            String telephoneNumber = scanner.nextLine();
            if (!telephoneNumber.trim().isEmpty()) {
                customer.setTelephoneNumber(telephoneNumber);
            }

            Address address = customer.getAddress();
            System.out.println("Enter new address line 1: ");
            String addressLine1 = scanner.nextLine();
            if (!addressLine1.trim().isEmpty()) {
                address.setAddressLine1(addressLine1);
            }

            System.out.println("Enter new address line 2: ");
            String addressLine2 = scanner.nextLine();
            if (!addressLine2.trim().isEmpty()) {
                address.setAddressLine2(addressLine2);
            }

            System.out.println("Enter new address line 3: ");
            String addressLine3 = scanner.nextLine();
            if (!addressLine3.trim().isEmpty()) {
                address.setAddressLine3(addressLine3);
            }

            System.out.println("Enter new post code: ");
            String postCode = scanner.nextLine();
            if (!postCode.trim().isEmpty()) {
                address.setPostCode(postCode);
            }

            System.out.println("Enter new country: ");
            String country = scanner.nextLine();
            if (!country.trim().isEmpty()) {
                address.setCountry(country);
            }
            
            customerService.updateCustomer(customerId, customer);
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    /**
     * Deletes a customer based on the provided ID.
     */
    public void deleteCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer ID: ");
        Long customerId = scanner.nextLong();
        customerService.deleteCustomer(customerId);
        System.out.println("Customer deleted successfully.");
    }
}
