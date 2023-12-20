package com.mmu.product_app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmu.product_app.models.Customer;
import com.mmu.product_app.services.CustomerService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/customer") 
@CrossOrigin("http://localhost:5173/")
public class CustomerController {
    CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<Customer> getMethodName(@PathVariable Long customerId ) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 

    @GetMapping("/get/all")
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        if (customers != null && !customers.isEmpty()) {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        Optional<Customer> updatedCustomer = customerService.updateCustomer(customerId, customer);
        return updatedCustomer
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
