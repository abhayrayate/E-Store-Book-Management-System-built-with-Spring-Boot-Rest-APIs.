package in.abhayit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.abhayit.Entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Long> {

}
