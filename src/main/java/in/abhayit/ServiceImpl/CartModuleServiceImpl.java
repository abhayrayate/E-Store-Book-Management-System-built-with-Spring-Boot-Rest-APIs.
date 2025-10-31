package in.abhayit.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.abhayit.Entity.BooksModule;
import in.abhayit.Entity.CartModule;
import in.abhayit.Entity.Customer;
import in.abhayit.Exception.BookIdNotFoundException;
import in.abhayit.Exception.CustomIDNotfoundException;
import in.abhayit.Repository.BooksModuleRepo;
import in.abhayit.Repository.CartModuleRepo;
import in.abhayit.Repository.CustomerRepository;
import in.abhayit.Service.CartModuleService;

@Service
public class CartModuleServiceImpl implements CartModuleService {

	@Autowired
	private CartModuleRepo cartModuleRepository;
	@Autowired
	private CustomerRepository custmerRepo;
	@Autowired
	private BooksModuleRepo booksModuleRepo;

	@Override
	public CartModule addToCart(Long custemerId, Long bookId, int quantity) {

		// Step 1: Check if customer exists in DB using ID
		// If not found, throw custom exception "Customer Id Not Found"
		Customer customer = custmerRepo.findById(custemerId)
				.orElseThrow(() -> new CustomIDNotfoundException("Custmer Id Not found"));

		// Step 2: Check if book exists in DB using ID
		// If not found, throw custom exception "Book Id not Found"
		BooksModule booksModule = booksModuleRepo.findById(bookId)
				.orElseThrow(() -> new BookIdNotFoundException("Book Id not Found"));

		// Step 3: Check whether this customer already added this book in their cart
		// It helps to avoid duplicate entries for the same book
		CartModule cartItem = cartModuleRepository.findByCustomerAndBooksModule(customer, booksModule);

		// Step 4: If cartItem already exists, update quantity
		if (cartItem != null) {
			// Add the new quantity to existing one
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
		}
		// Step 5: If cartItem not found, create new cart record
		else {
			// Create new CartModule object with quantity, book, and customer
			cartItem = new CartModule(quantity, booksModule, customer);
		}

		// Step 6: Calculate total price for that book (quantity × book price)
		cartItem.setTotalPrice(cartItem.getQuantity() * booksModule.getPrice());

		// Step 7: Save updated/created cart record to DB
		// This ensures cart data is persistent in database
		return cartModuleRepository.save(cartItem);
	}

	@Override
	public void deleteToCart(Long id) {

		cartModuleRepository.deleteById(id);

	}

	@Override
	public void updateCart(Long id, CartModule updatedCart) {
	    CartModule existingCart = cartModuleRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));

	    // quantity update कर
	    if (updatedCart.getQuantity() > 0) {
	        existingCart.setQuantity(updatedCart.getQuantity());
	    }

	    // book update कर
	    if (updatedCart.getBooksModule() != null) {
	        existingCart.setBooksModule(updatedCart.getBooksModule());
	    }

	    // customer update कर
	    if (updatedCart.getCustomer() != null) {
	        existingCart.setCustomer(updatedCart.getCustomer());
	    }
	 // totalPrice calculate कर (book price * quantity)
	    if (existingCart.getBooksModule() != null) {
	        double totalPrice = existingCart.getQuantity() * existingCart.getBooksModule().getPrice();
	        existingCart.setTotalPrice(totalPrice);
	    }


	    cartModuleRepository.save(existingCart);
	}


}
