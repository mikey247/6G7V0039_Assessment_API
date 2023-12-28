package com.mmu.product_app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

import com.mmu.product_app.models.Address;
import com.mmu.product_app.models.Customer;
import com.mmu.product_app.repository.CustomerRepository;
import com.mmu.product_app.services.CustomerService;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setBusinessName("Test Business");
        customer.setTelephoneNumber("1234567890");
        Address address = new Address();
        address.setAddressLine1("123 Test Street");
        address.setAddressLine2("Apt 4B");
        address.setAddressLine3("Test City");
        address.setPostCode("12345");
        address.setCountry("Test Country");
        customer.setAddress(address);

        // Mock the behavior of the customerRepository
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Call the createCustomer method
        Customer createdCustomer = customerService.createCustomer(customer);

        // Verify the behavior
        assertNotNull(createdCustomer);
        assertEquals("Test Business", createdCustomer.getBusinessName());
        assertEquals("1234567890", createdCustomer.getTelephoneNumber());
        assertEquals("123 Test Street", createdCustomer.getAddress().getAddressLine1());
        assertEquals("Apt 4B", createdCustomer.getAddress().getAddressLine2());
        assertEquals("Test City", createdCustomer.getAddress().getAddressLine3());
        assertEquals("12345", createdCustomer.getAddress().getPostCode());
        assertEquals("Test Country", createdCustomer.getAddress().getCountry());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testGetCustomer() {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setBusinessName("Test Business");
        customer.setTelephoneNumber("1234567890");
        Address address = new Address();
        address.setAddressLine1("123 Test Street");
        address.setAddressLine2("Apt 4B");
        address.setAddressLine3("Test City");
        address.setPostCode("12345");
        address.setCountry("Test Country");
        customer.setAddress(address);

        // Mock the behavior of the customerRepository
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(customer));

        // Call the getCustomer method
        Customer retrievedCustomer = customerService.getCustomer(1L);

        // Verify the behavior
        assertNotNull(retrievedCustomer);
        assertEquals("Test Business", retrievedCustomer.getBusinessName());
        assertEquals("1234567890", retrievedCustomer.getTelephoneNumber());
        assertEquals("123 Test Street", retrievedCustomer.getAddress().getAddressLine1());
        assertEquals("Apt 4B", retrievedCustomer.getAddress().getAddressLine2());
        assertEquals("Test City", retrievedCustomer.getAddress().getAddressLine3());
        assertEquals("12345", retrievedCustomer.getAddress().getPostCode());
        assertEquals("Test Country", retrievedCustomer.getAddress().getCountry());
        verify(customerRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void testGetCustomers() {
        // Create a list of sample customers
        List<Customer> customers = new ArrayList();
        Customer customer1 = new Customer();
        customer1.setBusinessName("Test Business 1");
        customer1.setTelephoneNumber("1234567890");
        Address address1 = new Address();
        address1.setAddressLine1("123 Test Street");
        address1.setAddressLine2("Apt 4B");
        address1.setAddressLine3("Test City");
        address1.setPostCode("12345");
        address1.setCountry("Test Country");
        customer1.setAddress(address1);
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setBusinessName("Test Business 2");
        customer2.setTelephoneNumber("0987654321");
        Address address2 = new Address();
        address2.setAddressLine1("456 Test Street");
        address2.setAddressLine2("Apt 2C");
        address2.setAddressLine3("Test City");
        address2.setPostCode("54321");
        address2.setCountry("Test Country");
        customer2.setAddress(address2);
        customers.add(customer2);

        // Mock the behavior of the customerRepository
        when(customerRepository.findAll()).thenReturn(customers);

        // Call the getCustomers method
        List<Customer> retrievedCustomers = customerService.getCustomers();

        // Verify the behavior
        assertNotNull(retrievedCustomers);
        assertEquals(2, retrievedCustomers.size());
        assertEquals("Test Business 1", retrievedCustomers.get(0).getBusinessName());
        assertEquals("1234567890", retrievedCustomers.get(0).getTelephoneNumber());
        assertEquals("123 Test Street", retrievedCustomers.get(0).getAddress().getAddressLine1());
        assertEquals("Apt 4B", retrievedCustomers.get(0).getAddress().getAddressLine2());
        assertEquals("Test City", retrievedCustomers.get(0).getAddress().getAddressLine3());
        assertEquals("12345", retrievedCustomers.get(0).getAddress().getPostCode());
        assertEquals("Test Country", retrievedCustomers.get(0).getAddress().getCountry());

        assertEquals("Test Business 2", retrievedCustomers.get(1).getBusinessName());
        assertEquals("0987654321", retrievedCustomers.get(1).getTelephoneNumber());
        assertEquals("456 Test Street", retrievedCustomers.get(1).getAddress().getAddressLine1());
        assertEquals("Apt 2C", retrievedCustomers.get(1).getAddress().getAddressLine2());
        assertEquals("Test City", retrievedCustomers.get(1).getAddress().getAddressLine3());
        assertEquals("54321", retrievedCustomers.get(1).getAddress().getPostCode());
        assertEquals("Test Country", retrievedCustomers.get(1).getAddress().getCountry());

        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteCustomer() {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setBusinessName("John Doe Ventures");
        customer.setTelephoneNumber("1234567890");
        // Mock the behavior of the customerRepository

        // Call the deleteCustomer method
        customerService.deleteCustomer(1L);

        // Verify that the delete method of customerRepository is called with the correct customer
        verify(customerRepository, times(1)).deleteById(1L);
    }
    
    @Test
    public void testUpdateCustomer() {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setBusinessName("John Doe Ventures");
        customer.setTelephoneNumber("1234567890");

        // Mock the behavior of the customerRepository
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        // Call the updateCustomer method
       Optional<Customer> updatedCustomer = customerService.updateCustomer(1L, customer);

        // Verify that the customerRepository's findById and save methods are called with the correct parameters
        verify(customerRepository).findById(1L);
        verify(customerRepository).save(customer);

        // Verify that the updated customer is returned correctly
        assertEquals(customer, updatedCustomer.get());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}