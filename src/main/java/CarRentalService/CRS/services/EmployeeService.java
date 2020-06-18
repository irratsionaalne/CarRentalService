package CarRentalService.CRS.services;

import CarRentalService.CRS.models.Employee;

import java.util.List;

public interface EmployeeService {

    boolean createEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(Long employeeId);

    boolean restoreEmployee(Long employeeId);

    List<Employee> getAllEmployees();

    Employee getById(Long employeeId);
}
