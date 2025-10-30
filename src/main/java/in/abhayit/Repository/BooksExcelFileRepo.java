package in.abhayit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.abhayit.Entity.BooksExcelFile;

public interface BooksExcelFileRepo  extends JpaRepository<BooksExcelFile, Long>{

}
