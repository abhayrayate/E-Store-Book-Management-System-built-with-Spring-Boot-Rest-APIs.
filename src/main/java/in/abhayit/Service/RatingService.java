package in.abhayit.Service;

import java.util.List;

import in.abhayit.Entity.Rating;
import in.abhayit.Model.RatingDto;

public interface RatingService {

	Rating createRatingReview(RatingDto ratingDto);

	List<Rating> getbyAllReviews();

	String deleteReview(Long id);

}
