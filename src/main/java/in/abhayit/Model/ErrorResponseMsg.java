package in.abhayit.Model;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponseMsg {

	   private Integer statuscode;
	   private String status;
	   private String message;
	   private List<?> list;
	   
	    	
	   public ErrorResponseMsg(Integer statuscode, String status, String message, List<?> list) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.message = message;
		this.list = list;
	   }
	   
	   
	    
}
