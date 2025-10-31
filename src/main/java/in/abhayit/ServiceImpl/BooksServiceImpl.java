package in.abhayit.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import in.abhayit.Entity.BooksModule;
import in.abhayit.Entity.mongo.BooksModuleMongo;
import in.abhayit.Exception.BookIdNotFoundException;
import in.abhayit.Repository.BooksModuleRepo;
import in.abhayit.Repository.mongo.BooksModuleMongoRepo;
import in.abhayit.Service.BooksService;

@Service
public class BooksServiceImpl implements BooksService{
	
	@Autowired BooksModuleRepo booksModuleRepo;
	
	@Autowired BooksModuleMongoRepo booksModuleMongoRepo;

	 @Override
	    public BooksModule custmerSaveBooks(BooksModule booksModule) {
		 
	        //  Save in MySQL
	        BooksModule savedBook = booksModuleRepo.save(booksModule);

	        //  Save in MongoDB
	        BooksModuleMongo mongoBook = new BooksModuleMongo();
	        mongoBook.setName(savedBook.getName());   
	        mongoBook.setTitle(savedBook.getTitle());
	        mongoBook.setAuthor(savedBook.getAuthor());

	        booksModuleMongoRepo.save(mongoBook);

	        return savedBook;
	    }


	@Override
	@Cacheable(value = "getAllBooks")
	public List<BooksModule> custmergetAllBooks() {
		
		List<BooksModule> list = booksModuleRepo.findAll();
		
		System.out.println("check the database how many Times get the Records");
		
		return list;
	}

	@Override
	@Cacheable(cacheNames = "booksmodule" ,key = "#id")
	public BooksModule getByCustmerBookid(Long id)  {
		
		Optional<BooksModule> bookId = booksModuleRepo.findById(id);
		
		if(!bookId.isPresent()) {
			
			throw new BookIdNotFoundException("Book Id Not Found");
	    }
		return bookId.get();
}
}