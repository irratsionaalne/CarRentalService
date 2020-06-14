package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Customer;

import java.util.List;

public interface CustomerService {

    boolean createCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(Long customerId);

    boolean restoreCustomer(Long customerId);

    List<CustomerService> getAllCustomers();

    CustomerService getById(Long customerId);
}
