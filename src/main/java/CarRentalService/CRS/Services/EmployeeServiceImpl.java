package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Employee;
import CarRentalService.CRS.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

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
    public boolean deleteEmployee(Long employeeId) throws Exception {
        Employee employee = getById(employeeId);
        if (employee == null) {
            throw new Exception("Employee does not exist");
        }

        employee.setActive(false);
        return updateEmployee(employee);
    }

    @Override
    public boolean restoreEmployee(Long employeeId) throws Exception {
        Employee employee = getById(employeeId);
        if (employee == null) {
            throw new Exception("Employee does not exist");
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

}
