package in.abhayit.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartModule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
	private Long id;

	@ManyToOne
	@JoinColumn(name = "custmer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private BooksModule booksModule;

	private int quantity;
	private double totalPrice;

	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	public CartModule(int quantity, BooksModule booksModule, Customer customer) {
		super();
		this.quantity = quantity;
		this.booksModule = booksModule;
		this.customer = customer;
	}
}
