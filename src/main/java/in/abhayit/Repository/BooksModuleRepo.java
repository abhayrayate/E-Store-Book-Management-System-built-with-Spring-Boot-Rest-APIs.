package in.abhayit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.abhayit.Entity.BooksModule;

public interface BooksModuleRepo extends JpaRepository<BooksModule, Long>{

}