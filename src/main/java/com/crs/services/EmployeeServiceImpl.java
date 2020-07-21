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
    private EmployeeRepo employeeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createEmployee(Employee employee) {
        User user = new User();
        user.setFirstName(employee.getFirstName());
        user.setLastName(employee.getLastName());
        user.setEmail(employee.getEmail());
        user.setPassword(passwordEncoder.encode(employee.getPassword()));
        user.setRole(employee.getRole());
        user.setActive(true);
        user = userRepo.save(user);

        employee.setId(user.getId());
        employee.setBranch(employee.getBranch());
        employee.setRole(employee.getRole());
        employeeRepo.save(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        if (employee == null || !employeeRepo.existsById(employee.getId())) {
            return false;
        }
        User user = new User();
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(employee.getRole());
        userRepo.save(user);
        employeeRepo.save(employee);
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

    @Override
    public boolean deleteEmployeeById(UUID employeeId) {
        Employee employee = getById(employeeId);
        if (employeeId == null) {
            return false;
        }
        //employee.setActive(false);
        updateEmployee(employee);
        return true;
    }

    @Override
    public boolean restoreEmployeeById(UUID employeeId) {
        Employee employee = getById(employeeId);
        if (employeeId == null) {
            return false;
        }
        //employee.setActive(true);
        updateEmployee(employee);
        return true;
    }

}
