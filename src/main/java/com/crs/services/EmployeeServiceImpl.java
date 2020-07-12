package com.crs.services;

import com.crs.models.*;
import com.crs.repositories.EmployeeRepo;
import com.crs.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private  EmployeeRepo employeeRepo;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createEmployee(Employee employee) throws Exception {
        User user = new User();
        user.setFirstName(employee.getFirstName());
        user.setLastName(employee.getLastName());
        user.setEmail(employee.getEmail());
        user.setPassword(passwordEncoder.encode(employee.getPassword()));
        user.setRole(Role.EMPLOYEE);
        user.setActive(true);
        userRepo.save(user);

        employee.setUser(user);
        employeeRepo.save(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        if (employee == null || !employeeRepo.existsById(employee.getId())) {
            return false;
        }

        employeeRepo.saveAndFlush(employee);
        return true;
    }
    
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getById(UUID employeeId) {
        return employeeRepo.getOne(employeeId);
    }

}
