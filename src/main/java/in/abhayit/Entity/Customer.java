package in.abhayit.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		private Long id ;
		
		private String email;
		
		private String name;
		
		
		@CreationTimestamp
		private  LocalDateTime createdDate;
		
		
		@UpdateTimestamp
		private LocalDateTime updatedDate;
}
