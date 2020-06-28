package com.crs.services;

import com.crs.controllers.dto.UserRegistrationDto;
import com.crs.models.Customer;
import com.crs.models.Employee;
import com.crs.repositories.CustomerRepo;
import com.crs.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerRepo customerRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepo customerRepo, BCryptPasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
    }

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
        if (customer.isActive()) {
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

    @Override
    public UserDetails loadUserByUsername(String email) {
        Customer customer = customerRepo.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("customer"));
        return new User(customer.getEmail(), customer.getPassword(), roles);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    @Override
    public Customer save(UserRegistrationDto userRegistrationDto) {
        Customer customer = new Customer();
        customer.setFirstName(userRegistrationDto.getFirstName());
        customer.setLastName(userRegistrationDto.getLastName());
        customer.setEmail(userRegistrationDto.getEmail());
        customer.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        return customerRepo.save(customer);
    }
}
