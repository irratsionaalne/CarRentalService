package com.crs.services;

import com.crs.models.Customer;

import java.util.List;

public interface CustomerService {

    boolean createCustomer(Customer customer) throws Exception;

    boolean updateCustomer(Customer customer) throws Exception;

    boolean setCustomerStatus(Long customerId) throws Exception;

    List<Customer> getAllCustomers();
  
    Customer getById(Long customerId);
}
