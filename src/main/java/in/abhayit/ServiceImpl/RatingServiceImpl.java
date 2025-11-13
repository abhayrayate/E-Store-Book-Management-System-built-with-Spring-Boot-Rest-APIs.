package in.abhayit.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.abhayit.Entity.BooksModule;
import in.abhayit.Entity.Customer;
import in.abhayit.Entity.Rating;
import in.abhayit.Exception.BookIdNotFoundException;
import in.abhayit.Exception.CustomIDNotfoundException;
import in.abhayit.Model.RatingDto;
import in.abhayit.Repository.BooksModuleRepo;
import in.abhayit.Repository.CustomerRepository;
import in.abhayit.Repository.RatingRepo;
import in.abhayit.Service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepo ratingRepo;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BooksModuleRepo booksModuleRepo;

	@Override
	public Rating createRatingReview(RatingDto ratingDto) {
		Customer customer = customerRepository.findById(ratingDto.getCustomerId())
				.orElseThrow(() -> new CustomIDNotfoundException("Customer Id Not Found.."));

		BooksModule BooksModule = booksModuleRepo.findById(ratingDto.getBookId())
				.orElseThrow(() -> new BookIdNotFoundException("Book Not Found.."));

		 Rating rating = new Rating();
		    rating.setBooksModule(BooksModule);
		    rating.setCustomer(customer);
		    rating.setRate(ratingDto.getRate());              
		    rating.setReviewtext(ratingDto.getReviewtext()); 
		    
		    return ratingRepo.save(rating);   
	}

	@Override
	public List<Rating> getbyAllReviews() {
		
		List<Rating> all = ratingRepo.findAll();
		return all;
		
	}

	@Override
	public String deleteReview(Long id) {
		
		Rating rating = ratingRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Review not found for id: " + id));

	    ratingRepo.delete(rating);

	    return "Review deleted successfully.";
	}

}
