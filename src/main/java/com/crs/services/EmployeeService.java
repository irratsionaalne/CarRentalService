package com.crs.services;

import com.crs.controllers.dto.EmployeeRegistrationDto;
import com.crs.models.Employee;
import com.crs.models.EmployeeRole;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(EmployeeRegistrationDto employeeRegistrationDto) throws Exception;

    boolean updateEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getById(Long employeeId);

}
