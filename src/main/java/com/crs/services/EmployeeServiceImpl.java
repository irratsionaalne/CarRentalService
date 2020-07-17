package com.crs.services;

import com.crs.models.*;
import com.crs.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public void createEmployee(Employee employee) throws Exception {
        employee.setActive(true);
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

    @Override
    public boolean deleteEmployeeById(UUID employeeId) {
        Employee employee = getById(employeeId);
        if (employeeId == null) {
            return false;
        }
        employee.setActive(false);
        updateEmployee(employee);
        return true;
    }

    @Override
    public boolean restoreEmployeeById(UUID employeeId) {
        Employee employee = getById(employeeId);
        if (employeeId == null) {
            return false;
        }
        employee.setActive(true);
        updateEmployee(employee);
        return true;
    }

}
