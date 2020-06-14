package CarRentalService.CRS.Services;

import java.util.List;

public interface CustomerService {

    boolean createCustomer();

    boolean updateCustomer(CustomerService customerService);

    boolean deleteCustomer(Long customerId);

    boolean restoreCustomer(Long customerId);

    List<CustomerService> getAllCustomers();

    CustomerService getById(Long customerId);
}
