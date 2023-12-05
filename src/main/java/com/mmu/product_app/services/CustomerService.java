package com.mmu.product_app.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mmu.product_app.models.Customer;
import com.mmu.product_app.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;
    }
    
    public Customer getCustomer(Long customerId){
        Optional<Customer> product =  customerRepository.findById(customerId);
        return product.get();
    }

    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }
    
}
