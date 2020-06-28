package com.crs.services;

import com.crs.controllers.dto.UserRegistrationDto;
import com.crs.models.Customer;
import com.crs.models.Employee;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService extends UserDetailsService {

    boolean createCustomer(Customer customer) throws Exception;

    boolean updateCustomer(Customer customer) throws Exception;

    boolean setCustomerStatus(Long customerId) throws Exception;

    List<Customer> getAllCustomers();
  
    Customer getById(Long customerId);

    UserDetails loadUserByUsername(String email);

    Customer findByEmail(String email);

    Customer save(UserRegistrationDto registration);
}
