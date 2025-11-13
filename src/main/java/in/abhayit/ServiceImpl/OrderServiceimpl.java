package in.abhayit.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.abhayit.Entity.BooksModule;
import in.abhayit.Entity.OrderModule;
import in.abhayit.Entity.UserRegister;
import in.abhayit.Model.OrderModuleDto;
import in.abhayit.Repository.OrderModuleRepo;
import in.abhayit.Repository.UserRegisterRepo;
import in.abhayit.Service.OrderService;

@Service
public class OrderServiceimpl implements OrderService {

	@Autowired
	private UserRegisterRepo userRegisterRepo;

	@Autowired
	private OrderModuleRepo orderModuleRepo;

	@Override
	public String saveOrders(OrderModuleDto orderModuleDto) {

		if (orderModuleDto == null || orderModuleDto.getTitle() == null || orderModuleDto.getTitle().isEmpty()) {
			return "No books selected. Please select at least one book to proceed.";
		}

		Long customerId = orderModuleDto.getCustomerId();
		Optional<UserRegister> byId = userRegisterRepo.findById(customerId);
		if(byId.isEmpty())
		{
			return "User is not available for this id ";
		}
		UserRegister userRegister = byId.get();
		
		System.out.println("CustomerId received in saveOrders(): " + customerId);
		List<String> seletedBooks = orderModuleDto.getTitle();

		Boolean ifPrimeUser = userRegister.getPrime();

		if (!ifPrimeUser) {

			if (seletedBooks.size() > 1) {
				return "Non-prime users can select only one book.";
			}

			List<OrderModule> anyLastweekPlaced = orderModuleRepo.findAnyLastweekPlaced(customerId);

			if (!anyLastweekPlaced.isEmpty()) {
				return "Non-prime users can place only one order per week.";
			}
		}

		for (String title : seletedBooks) {

			BooksModule bookName = orderModuleRepo.findByBookName(title);

			if (bookName == null) {
				return "No book found: " + title;
			}

			OrderModule order = new OrderModule();
			order.setBookId(bookName.getId());
			order.setCustomerId(customerId);
			order.setStatus(false); // Default status = pending/unprocessed

			// Save order into database
			orderModuleRepo.save(order);
		}

		
		return "Order Placed successfully. Thank you.!";
	}

//	
//	private Boolean checkPrimeUser(Long customerId) {
//		  System.out.println("Inside checkPrimeUser(): " + customerId);
//		// Fetch user by ID
//		Optional<UserRegister> user = userRegisterRepo.findById(customerId);
//
//		// Return Prime status if available, else default to false
//		return user.map(UserRegister::getPrime).orElse(false);
//	}

}
