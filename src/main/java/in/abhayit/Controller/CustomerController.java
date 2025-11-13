package in.abhayit.Controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.abhayit.Entity.Customer;
import in.abhayit.Model.CustomerDto;
import in.abhayit.Model.ResponseMessage;
import in.abhayit.Service.CustomerService;
import in.abhayit.Utility.Constants;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CustomerSaveController ", description = "Customer Save and Update")
@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Operation(summary = "Save Customer ", description = "e commerece online books store  Save Customer ")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "customer register successfully"),
			@ApiResponse(responseCode = "400", description = "customer register failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/customersave")
	public ResponseEntity<ResponseMessage> createCustomer(@RequestBody CustomerDto customerDto) {

		try {

			if (customerDto.getEmail() == null || customerDto.getEmail().isEmpty() || customerDto.getName() == null
					|| customerDto.getName().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Email and name cannot be empty"));
			}

			Customer savedCustomer = customerService.insertCustomer(customerDto);

			if (savedCustomer != null) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"Customer save successfully", savedCustomer));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Customer save failed"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

	// --------------customer update--------------

	@Operation(summary = "Create or Update Customer", description = "Creates a new customer if ID is null, or updates an existing customer if ID is provided.")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Customer created successfully"),
			@ApiResponse(responseCode = "200", description = "Customer updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid customer data or validation failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PutMapping("/customerupdate")
	public ResponseEntity<ResponseMessage> customerUpdates(@RequestBody Customer customer) {
		try {
			if (customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getName() == null
					|| customer.getName().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Email and name cannot be empty"));
			}

			if (customer.getId() != null) {
				// Update existing customer
				Customer updatedCustomer = customerService.updateCustomer(customer);
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Customer updated successfully", updatedCustomer));
			} else {
				// Insert new customer
				Customer newCustomer = customerService.updateCustomer(customer);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"Customer created successfully", newCustomer));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

	// ----------------------------saveorUpdate----------

	@Operation(summary = "Create or Update Customer", description = "Creates a new customer if the ID is null, or updates an existing customer if the ID is provided.")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Customer created successfully"),
			@ApiResponse(responseCode = "200", description = "Customer updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid customer data or validation failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/customerSaveOrUpdate")
	public ResponseEntity<ResponseMessage> customerSaveOrUpdate(@RequestBody Customer customer) {
		try {
			// Validate fields
			if (customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getName() == null
					|| customer.getName().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Email and name cannot be empty"));
			}

			// If ID is null → create new customer
			if (customer.getId() == null) {
				Customer createdCustomer = customerService.createOrUpdateCustomer(customer);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"Customer saved successfully", createdCustomer));
			}
			// Else → update existing customer
			else {
				Customer updatedCustomer = customerService.createOrUpdateCustomer(customer);
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Customer updated successfully", updatedCustomer));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

	// ------------------------------------get user by id ----------

	@GetMapping("/getByCustmerId/{id}")
	@Operation(summary = "Get Customer by ID", description = "Fetches a customer by their unique ID from the database.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Customer fetched successfully"),
			@ApiResponse(responseCode = "400", description = "Customer not found or invalid ID"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	public ResponseEntity<ResponseMessage> customerORUpdates(@PathVariable Long id) {

		Customer byCustomersId = customerService.getByCustomersId(id);
		if (byCustomersId != null) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
					"customer id getting successfully", byCustomersId));

		} else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
					"customer id getting Failed", byCustomersId));

		}
	}

	// --------------------------GetAllCusotmers-----
	@GetMapping("/getAllCustomers")
	@Operation(summary = "Get All Customers", description = "Fetches all customers from the database.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Customers fetched successfully"),
			@ApiResponse(responseCode = "400", description = "Failed to fetch customers or no customers found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	public ResponseEntity<ResponseMessage> getAllcustomers() {

		try {
			List<Customer> byAllCustomers = customerService.getByAllCustomers();

			if (byAllCustomers != null && !byAllCustomers.isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Customers fetched successfully", byAllCustomers));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "No customers found", byAllCustomers));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}
	
//	----------------------pagination--
	
	@GetMapping("/fetchAllCustomersWithPagination")
	public ResponseEntity<ResponseMessage> fetchAllCustomersWithPagination(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String sortField,@RequestParam String sortOrder) {

		try {
			 Page<Customer> fetchAllCustomPages = customerService.fetchAllCustomPages(pageNo,pageSize,sortField,sortOrder);

			if (fetchAllCustomPages != null && !fetchAllCustomPages.isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Customers Fetch  successfully", fetchAllCustomPages));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "No customers found", fetchAllCustomPages));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}
	
	
	//Circuit Breaker 
	@GetMapping("/getcustomer")
	@CircuitBreaker(name = "showData", fallbackMethod = "fallbackgetData")
	public String showData() throws Exception {
	    throw new Exception();
	}

	public String fallbackgetData(Throwable th) {
	    return "Payment Service is Unavailable......";
	}
	
}
