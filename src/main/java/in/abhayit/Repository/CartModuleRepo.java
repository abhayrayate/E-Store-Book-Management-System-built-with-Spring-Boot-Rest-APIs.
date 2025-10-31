package in.abhayit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.abhayit.Entity.BooksModule;
import in.abhayit.Entity.CartModule;
import in.abhayit.Entity.Customer;

public interface CartModuleRepo extends JpaRepository<CartModule, Long> {
	
    CartModule findByCustomerAndBooksModule(Customer customer, BooksModule booksModule);
}

