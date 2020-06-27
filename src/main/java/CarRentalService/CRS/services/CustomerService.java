package CarRentalService.CRS.services;

import CarRentalService.CRS.models.Customer;

import java.util.List;

public interface CustomerService {

    boolean createCustomer(Customer customer) throws Exception;

    boolean updateCustomer(Customer customer) throws Exception;

    boolean deleteCustomer(Long customerId) throws Exception;

    boolean restoreCustomer(Long customerId) throws Exception;

    List<Customer> getAllCustomers();

    Customer getById(Long customerId);
}
