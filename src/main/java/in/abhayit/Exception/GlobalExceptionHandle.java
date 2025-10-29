package in.abhayit.Exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import in.abhayit.Model.ErrorResponseMsg;
import in.abhayit.Utility.Constants;

@ControllerAdvice
public class GlobalExceptionHandle {

	@ExceptionHandler(CustomIDNotfoundException.class)
	public ResponseEntity<ErrorResponseMsg> customerExceptionHandler(CustomIDNotfoundException ex) {
		List<String> details = new ArrayList<>();
		details.add("Error: Customer ID not found");
		details.add("Detailed Message: " + ex.getLocalizedMessage());
		details.add("Timestamp: " + System.currentTimeMillis());

		ErrorResponseMsg error = new ErrorResponseMsg(HttpStatus.BAD_REQUEST.value(), Constants.FAILURE, "NOT-FOUND",
				details);

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	
}
