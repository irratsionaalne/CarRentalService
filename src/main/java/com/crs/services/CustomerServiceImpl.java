package com.crs.services;

import com.crs.dto.CustomerRegistrationDto;
import com.crs.models.Customer;
import com.crs.models.Role;
import com.crs.models.User;
import com.crs.repositories.CustomerRepo;
import com.crs.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Customer createCustomer(CustomerRegistrationDto customerRegistrationDto) throws Exception {
        User user = new User();
        user.setFirstName(customerRegistrationDto.getFirstName());
        user.setLastName(customerRegistrationDto.getLastName());
        user.setEmail(customerRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(customerRegistrationDto.getPassword()));
        user.setRole(Role.CUSTOMER);
        user.setActive(true);
        user = userRepo.save(user);

        Customer customer = new Customer();
        customer.setAddress(customerRegistrationDto.getAddress());
        customer.setDob(LocalDate.parse(customerRegistrationDto.getDob()));
        customer.setUser(user);
        return customerRepo.save(customer);
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
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getById(UUID customerId) {
        return customerRepo.getOne(customerId);
    }


}
