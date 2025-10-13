package in.abhayit.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	
	private String email;
	
	private String name;
	
	
//	@CreationTimestamp
//	private  LocalDateTime createdDate;
//	
//	
//	@UpdateTimestamp
//	private LocalDateTime updatedDate;
}
