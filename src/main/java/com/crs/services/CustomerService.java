package com.crs.services;

import com.crs.controllers.dto.CustomerRegistrationDto;
import com.crs.controllers.dto.UserRegistrationDto;
import com.crs.models.Customer;
import com.crs.models.Employee;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(CustomerRegistrationDto customerRegistrationDto) throws Exception;

    boolean updateCustomer(Customer customer) throws Exception;

    List<Customer> getAllCustomers();
  
    Customer getById(Long customerId);
}
