package in.abhayit.Controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.abhayit.Model.ResponseMessage;
import in.abhayit.Service.BooksExcelUploadService;
import in.abhayit.Utility.Constants;
import in.abhayit.Utility.Helper;

@RestController
public class BooksExcelUploadController {

	@Autowired
	BooksExcelUploadService booksExcelUploadService;

	@PostMapping("/uploadExcelFile")
	public ResponseEntity<ResponseMessage> postMethodName(@RequestParam MultipartFile file) throws IOException {
	
		System.out.println(file);
		if(Helper.checkExcelfile(file)) {
			booksExcelUploadService.uploadExcelintoDB(file);
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Excelfile save successfully"));
		
	}else {
		
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "Excelfile save failed"));

	}
	
}
}
