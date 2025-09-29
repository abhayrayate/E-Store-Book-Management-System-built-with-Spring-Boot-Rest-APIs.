package in.abhayit.Model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseMessage {

    private Integer statuscode;
    private String status;
    private String message;
    private Object data ;
    private List<?> list;
    
    
	public ResponseMessage(Integer statuscode, String status, String message, Object data) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.message = message;
		this.data = data;	
	}
	
	public ResponseMessage(Integer statuscode, String status, String message) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.message = message;
	}

	public ResponseMessage(Integer statuscode, String status, String message, List<?> list) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.message = message;
		this.list = list;
	}
    
    
    
}
