package com.crs.services;

import com.crs.controllers.dto.UserRegistrationDto;
import com.crs.models.Employee;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService extends UserDetailsService {

    boolean createEmployee(Employee employee) throws Exception;

    boolean updateEmployee(Employee employee);

    boolean setEmployeeStatus(Long employeeId) throws Exception;

    List<Employee> getAllEmployees();

    Employee getById(Long employeeId);

    UserDetails loadUserByUsername(String email);

    Employee findByEmail(String email);

    Employee save(UserRegistrationDto registration);

}