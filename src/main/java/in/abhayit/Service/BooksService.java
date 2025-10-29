package in.abhayit.Service;

import java.util.List;

import in.abhayit.Entity.BooksModule;

public interface BooksService {

	BooksModule custmerSaveBooks(BooksModule booksModule);

	List<BooksModule> custmergetAllBooks();

	BooksModule getByCustmerBookid(Long id);

}

