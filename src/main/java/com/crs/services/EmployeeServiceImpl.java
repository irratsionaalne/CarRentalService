package com.crs.services;

import com.crs.controllers.dto.EmployeeRegistrationDto;
import com.crs.models.*;
import com.crs.repositories.EmployeeRepo;
import com.crs.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.EnumMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    
    private final UserRepo userRepo;
    
    private final EmployeeRepo employeeRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    EmployeeRole employeeRole;

    @Override
    public Employee createEmployee(EmployeeRegistrationDto employeeRegistrationDto) throws Exception {
        User user = new User();
        user.setFirstName(employeeRegistrationDto.getFirstName());
        user.setLastName(employeeRegistrationDto.getLastName());
        user.setEmail(employeeRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(employeeRegistrationDto.getPassword()));
        user.setRole(Role.EMPLOYEE);
        user.setActive(true);
        user = userRepo.save(user);

        Employee employee = new Employee();
        employee.setId(user.getId());
        employee.setBranch(employeeRegistrationDto.getBranch());
        employee.setRole(employeeRole);
        return employeeRepo.save(employee);
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
    public Employee getById(Long employeeId) {
        return employeeRepo.getOne(employeeId);
    }

}
