package in.abhayit.Service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface BooksExcelUploadService {

	void uploadExcelintoDB(MultipartFile file) throws IOException;

}
