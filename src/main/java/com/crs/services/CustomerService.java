package com.crs.services;

import com.crs.dto.CustomerRegistrationDto;
import com.crs.models.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(CustomerRegistrationDto customerRegistrationDto) throws Exception;

    boolean updateCustomer(Customer customer) throws Exception;

    List<Customer> getAllCustomers();
  
    Customer getById(Long customerId);
}
