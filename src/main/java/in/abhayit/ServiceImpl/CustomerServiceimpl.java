package in.abhayit.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.abhayit.Entity.Customer;
import in.abhayit.Exception.CustomIDNotfoundException;
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
	    return customerRepo.findById(id)
	            .orElseThrow(() -> new CustomIDNotfoundException("Customer Id Not Found: " + id));
	}


	@Override
	public List<Customer> getByAllCustomers() {
		List<Customer> list = customerRepo.findAll();
		return list;
	}

	@Override
	public Page<Customer> fetchAllCustomPages(int pageNo, int pageSize, String sortField, String sortOrder) {

		Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

		PageRequest request = PageRequest.of(pageNo, pageSize, sort);
		
		return customerRepo.findAll(request);
	}

}
