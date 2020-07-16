package com.crs.services;

import com.crs.models.Branch;
import com.crs.models.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    void createEmployee(Employee employee) throws Exception;

    boolean updateEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getById(UUID employeeId);

    boolean deleteEmployeeById(UUID employeeId);

    boolean restoreEmployeeById(UUID employeeId);

}
