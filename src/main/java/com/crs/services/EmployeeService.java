package com.crs.services;

import com.crs.dto.EmployeeRegistrationDto;
import com.crs.models.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee createEmployee(EmployeeRegistrationDto employeeRegistrationDto) throws Exception;

    boolean updateEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getById(UUID employeeId);

}
