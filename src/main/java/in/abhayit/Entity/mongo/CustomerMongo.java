package in.abhayit.Entity.mongo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerMongo {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		private String id ;
		
		private String email;
		
		private String name;
		
}

