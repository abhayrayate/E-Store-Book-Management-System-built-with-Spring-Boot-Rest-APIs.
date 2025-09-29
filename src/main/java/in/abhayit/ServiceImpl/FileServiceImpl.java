package in.abhayit.ServiceImpl;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import in.abhayit.Entity.FilesEntity;
import in.abhayit.Repository.FileRepo;
import in.abhayit.Service.FileService;

@Service
public class FileServiceImpl implements FileService {

	 @Autowired
	    private FileRepo fileRepo;

	     @Override
	    public String saveFile(MultipartFile file) throws IOException {
	        FilesEntity filesEntity = new FilesEntity();
	        filesEntity.setFileName(file.getOriginalFilename());
	        filesEntity.setFileType(file.getContentType());
	        filesEntity.setData(file.getBytes());

	        fileRepo.save(filesEntity);

	        return "Image Insert Successfully.. " + file.getOriginalFilename();
	    }

}
