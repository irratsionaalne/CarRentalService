package com.crs.services;

import com.crs.controllers.dto.UserRegistrationDto;
import com.crs.models.Employee;
import com.crs.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, BCryptPasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createEmployee(Employee employee) throws Exception {
        if (employee == null) {
            throw new Exception("Invalid employee");
        }

        employee.setActive(true);
        employeeRepo.save(employee);
        return true;
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
    public boolean setEmployeeStatus(Long employeeId) throws Exception {
        Employee employee = getById(employeeId);
        if (employee == null) {
            throw new Exception("Employee does not exist");
        }
        if(employee.isActive()) {
            employee.setActive(false);
        }
        employee.setActive(true);
        return updateEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getById(Long employeeId) {
        return employeeRepo.getOne(employeeId);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeRepo.findByEmail(login);
        if (employee == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("employee"));
        return new User(employee.getEmail(),employee.getPassword(), roles);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }

    @Override
    public Employee save(UserRegistrationDto userRegistrationDto) {
        Employee employee = new Employee();
        employee.setFirstName(userRegistrationDto.getFirstName());
        employee.setLastName(userRegistrationDto.getLastName());
        employee.setEmail(userRegistrationDto.getEmail());
        employee.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        return employeeRepo.save(employee);
    }

}
