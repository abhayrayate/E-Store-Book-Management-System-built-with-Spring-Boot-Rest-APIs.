package in.abhayit.Service;

import java.util.List;

import in.abhayit.Entity.Customer;
import in.abhayit.Model.CustomerDto;

public interface CustomerService {

	public Customer insertCustomer(CustomerDto customerDto);

	public Customer updateCustomer(Customer customer);

	public Customer createOrUpdateCustomer(Customer customer);

	public Customer getByCustomersId(Long id);

	public List<Customer> getByAllCustomers();

}
