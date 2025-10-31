package in.abhayit.Entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "fileimages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilesEntityMongo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id ;
	
	private String fileName;
	private String fileType;
	
	@Column(columnDefinition = "longblob")
	@Lob
	private byte[] data;
	
	
}