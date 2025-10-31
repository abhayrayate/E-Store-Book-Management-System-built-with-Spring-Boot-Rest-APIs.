package in.abhayit.Entity.mongo;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="Books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksModuleMongo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	private String title;
	private String author;
	
	
	
	
	

}
