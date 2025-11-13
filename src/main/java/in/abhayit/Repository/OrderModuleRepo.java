package in.abhayit.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.abhayit.Entity.BooksModule;
import in.abhayit.Entity.OrderModule;

public interface OrderModuleRepo extends JpaRepository<OrderModule, Long> {

   
    @Query(value = "SELECT * FROM orders o WHERE o.customer_id = :customerId AND o.created_date > CURDATE() - INTERVAL 7 DAY", 
           nativeQuery = true)
    public List<OrderModule> findAnyLastweekPlaced(@Param("customerId") Long customerId);

   
    @Query("SELECT b FROM BooksModule b WHERE b.title = :title")
    public BooksModule findByBookName(@Param("title") String title);
}
