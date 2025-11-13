package in.abhayit.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ratings")
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int rate;
	private String reviewtext;

	@ManyToOne
	@JoinColumn(name = "custmer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private BooksModule booksModule;
	
	@CreationTimestamp
	private  LocalDateTime createdDate;
	
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	
}
