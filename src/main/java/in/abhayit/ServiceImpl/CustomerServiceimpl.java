package in.abhayit.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.abhayit.Entity.Customer;
import in.abhayit.Model.CustomerDto;
import in.abhayit.Repository.CustomerRepository;
import in.abhayit.Service.CustomerService;

@Service
public class CustomerServiceimpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepo;

	@Override
	public Customer insertCustomer(CustomerDto customerdto) {
		Customer cust = new Customer();
		cust.setName(customerdto.getName());
		cust.setEmail(customerdto.getEmail());
		customerRepo.save(cust);
		return cust;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer cust = customerRepo.save(customer);
		return cust;
	}

	@Override
	public Customer createOrUpdateCustomer(Customer customer) {
		if (customer.getId() == null) {
			// Create new customer
			return customerRepo.save(customer);
		} else {
			// Update existing customer
			Optional<Customer> byId = customerRepo.findById(customer.getId());
			if (byId.isPresent()) {
				Customer existData = byId.get();
				existData.setName(customer.getName());
				existData.setEmail(customer.getEmail());
				return customerRepo.save(existData); // return updated customer
			} else {
				throw new RuntimeException("Customer Not Found");
			}
		}
	}

	@Override
	public Customer getByCustomersId(Long id) {
		Optional<Customer> byId = customerRepo.findById(id);
		if (!byId.isPresent()) {

			throw new RuntimeException("Customer Id Not Found");
		}
		return byId.get();
	}

	@Override
	public List<Customer> getByAllCustomers() {
		List<Customer> list = customerRepo.findAll();
		return list;
	}

}
