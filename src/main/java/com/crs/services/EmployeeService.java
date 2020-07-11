package com.crs.services;

import com.crs.dto.EmployeeRegistrationDto;
import com.crs.models.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(EmployeeRegistrationDto employeeRegistrationDto) throws Exception;

    boolean updateEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getById(Long employeeId);

}
