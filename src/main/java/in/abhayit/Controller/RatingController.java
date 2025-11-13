package in.abhayit.Controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.abhayit.Entity.Rating;
import in.abhayit.Model.RatingDto;
import in.abhayit.Model.ResponseMessage;
import in.abhayit.Service.RatingService;
import in.abhayit.Utility.Constants;

@RestController
public class RatingController {

	@Autowired
	RatingService ratingService;

	@PostMapping("/ratingbooks")
	public ResponseEntity<ResponseMessage> rateBooks(@RequestBody RatingDto ratingDto) {
		try {

			Rating savedRating = ratingService.createRatingReview(ratingDto);

			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
					"Rating & Review submitted successfully.", savedRating));

		} catch (IllegalArgumentException e) {

			return ResponseEntity
					.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, e.getMessage()));

		} catch (RuntimeException e) {

			return ResponseEntity
					.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, e.getMessage()));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Something went wrong while submitting your review."));
		}
	}

	@GetMapping("/getAllreviews")
	public ResponseEntity<ResponseMessage> getAllReview() {
		try {
			List<Rating> allReviews = ratingService.getbyAllReviews();

			if (allReviews.isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"No reviews found.", allReviews));
			}

			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
					"All reviews fetched successfully.", allReviews));

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Something went wrong while fetching reviews."));
		}
	}

	@DeleteMapping("/deletereview/{id}")
	public ResponseEntity<ResponseMessage> deleteReview(@PathVariable Long id) {
		try {
			String result = ratingService.deleteReview(id);

			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, result));

		} catch (RuntimeException e) {
			return ResponseEntity
					.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Something went wrong while deleting the review."));
		}
	}

}
