package com.mmu.product_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mmu.product_app.models.Address;
import com.mmu.product_app.models.Customer;
import com.mmu.product_app.repository.CustomerRepository;

import lombok.AllArgsConstructor;

/**
 * This class represents the service layer for managing customers.
 * It provides methods for creating, retrieving, updating, and deleting customers.
 */
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Creates a new customer.
     *
     * @param customer The customer object to be created.
     * @return The newly created customer.
     */
    public Customer createCustomer(Customer customer){
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;
    }
    
    /**
     * Retrieves a customer by ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     */
    public Customer getCustomer(Long customerId){
        Optional<Customer> product =  customerRepository.findById(customerId);
        return product.get();
    }
    
    /**
     * Retrieves all customers.
     *
     * @return A list of all customers.
     */
    public List<Customer> getCustomers(){
        return (List<Customer>)customerRepository.findAll();
    }

    /**
     * Deletes a customer by ID.
     *
     * @param customerId The ID of the customer to delete.
     */
    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }
    
    /**
     * Updates a customer by ID.
     *
     * @param customerId The ID of the customer to update.
     * @param customer The updated customer object.
     * @return The updated customer.
     */
    public Optional<Customer> updateCustomer(Long customerId, Customer customer){

        return customerRepository.findById(customerId).map(existingCustomer -> {
            if (customer.getBusinessName() != null) {
                existingCustomer.setBusinessName(customer.getBusinessName());
            }
            if (customer.getTelephoneNumber() != null) {
                existingCustomer.setTelephoneNumber(customer.getTelephoneNumber());
            }
            if (customer.getAddress() != null) {
                Address existingAddress = existingCustomer.getAddress();
                Address updatedAddress = customer.getAddress();
                if (updatedAddress.getAddressLine1() != null) {
                    existingAddress.setAddressLine1(customer.getAddress().getAddressLine1());
                }
                if (updatedAddress.getAddressLine2() != null) {
                    existingAddress.setAddressLine2(customer.getAddress().getAddressLine2());
                }
                if (updatedAddress.getAddressLine3() != null) {
                    existingAddress.setAddressLine3(customer.getAddress().getAddressLine3());
                }
                if (updatedAddress.getPostCode() != null) {
                    existingAddress.setPostCode(customer.getAddress().getPostCode());
                }
                existingCustomer.setAddress(existingAddress);
            }
            return customerRepository.save(existingCustomer);
        });
    }
}
