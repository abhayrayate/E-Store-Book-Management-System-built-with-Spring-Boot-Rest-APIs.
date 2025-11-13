package in.abhayit.Model;

import java.util.List;

import lombok.Data;

@Data
public class OrderModuleDto {

	private Long customerId;
	private List<String> title;
}
