package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Car;
import CarRentalService.CRS.Models.Customer;
import CarRentalService.CRS.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerRepo customerRepo;

    @Override
    public boolean createCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }
        customer.setActive(true);
        customerRepo.save(customer);
        return true;
    }

    @Override
    public boolean updateCustomer(Customer customer) {

        if (customer == null || !customerRepo.existsById(customer.getId())) {
            return false;
        }
        customerRepo.saveAndFlush(customer);
        return true;
    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        Customer customer = getById(customerId);
        if (customer == null) {
            return false;
        }
        customer.setActive(false);
        deleteCustomer(customerId);
        return true;
    }

    @Override
    public boolean restoreCustomer(Long customerId) {
        Customer customer = getById(customerId);
        if (customer == null) {
            return false;
        }
        customer.setActive(true);
        restoreCustomer(customerId);
        return true;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getById(Long customerId) {
        return customerRepo.getOne(customerId);
    }
}

