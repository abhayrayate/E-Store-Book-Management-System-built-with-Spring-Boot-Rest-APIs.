package in.abhayit.ServiceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.abhayit.Entity.BooksExcelFile;
import in.abhayit.Repository.BooksExcelFileRepo;
import in.abhayit.Service.BooksExcelUploadService;
import in.abhayit.Utility.Helper;

@Service
public class BooksExcelUploadServiceImpl implements BooksExcelUploadService {

	@Autowired
	BooksExcelFileRepo booksExcelFileRepo;

	@Override
	public void uploadExcelintoDB(MultipartFile file) throws IOException {

		List<BooksExcelFile> excelFilesSaveDatabase = Helper.excelFilesInsertDatabase(file.getInputStream());
        System.out.println(excelFilesSaveDatabase);
        
		booksExcelFileRepo.saveAll(excelFilesSaveDatabase);

	}

}

