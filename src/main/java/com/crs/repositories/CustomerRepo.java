package com.crs.repositories;

import com.crs.models.Customer;
import com.crs.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}
