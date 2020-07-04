package com.crs.services;

import com.crs.models.Customer;
import com.crs.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerRepo customerRepo;

    @Override
    public boolean createCustomer(Customer customer) throws Exception {
        if (customer == null) {
            throw new Exception("Invalid creation of Customer");
        }
        customer.setActive(true);
        customerRepo.save(customer);
        return true;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws Exception {
        if (customer == null || !customerRepo.existsById(customer.getId())) {
            throw new Exception("Invalid updating for Customer");
        }
        customerRepo.saveAndFlush(customer);
        return true;
    }

    @Override
    public boolean setCustomerStatus(Long customerId) throws Exception {
        Customer customer = getById(customerId);
        if (customer == null) {
            throw new Exception("Customer does not exist");
        }
        if(customer.isActive()) {
            customer.setActive(false);
        }
        customer.setActive(true);
        return updateCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getById(Long customerId) {
        return customerRepo.getOne(customerId);
    }
}