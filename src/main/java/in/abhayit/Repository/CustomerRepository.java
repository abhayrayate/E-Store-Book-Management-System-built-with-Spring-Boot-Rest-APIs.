package in.abhayit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.abhayit.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

	

}
