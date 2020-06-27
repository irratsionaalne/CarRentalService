package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Employee;

import java.util.List;

public interface EmployeeService {

    boolean createEmployee(Employee employee) throws Exception;

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(Long employeeId) throws Exception;

    boolean restoreEmployee(Long employeeId) throws Exception;

    List<Employee> getAllEmployees();

    Employee getById(Long employeeId);
}
