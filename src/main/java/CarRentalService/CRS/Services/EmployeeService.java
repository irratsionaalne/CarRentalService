package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Employee;

import java.util.List;

public interface EmployeeService {

    boolean createEmployee();

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(Long employeeId);

    boolean restoreEmployee(Long employeeId);

    List<Employee> getAllEmployees();

    Employee getById(Long employeeId);
}
