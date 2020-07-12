package com.crs.services;

import com.crs.models.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    boolean createEmployee(Employee employee) throws Exception;

    boolean updateEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getById(UUID employeeId);

}
