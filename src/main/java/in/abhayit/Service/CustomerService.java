package in.abhayit.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import in.abhayit.Entity.Customer;
import in.abhayit.Model.CustomerDto;

public interface CustomerService {

	public Customer insertCustomer(CustomerDto customerDto);

	public Customer updateCustomer(Customer customer);

	public Customer createOrUpdateCustomer(Customer customer);

	public Customer getByCustomersId(Long id);

	public List<Customer> getByAllCustomers();

	public Page<Customer> fetchAllCustomPages(int pageNo, int pageSize, String sortField, String sortOrder);

}
