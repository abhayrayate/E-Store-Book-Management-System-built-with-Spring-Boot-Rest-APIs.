package in.abhayit.ServiceImpl;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.abhayit.Entity.FilesEntity;
import in.abhayit.Entity.mongo.FilesEntityMongo;
import in.abhayit.Repository.FileRepo;
import in.abhayit.Repository.mongo.FileRepoMongo;
import in.abhayit.Service.FileService;

@Service
public class FileServiceImpl implements FileService {

	 @Autowired
	    private FileRepo fileRepo;
	 
	 @Autowired
	 FileRepoMongo fileRepoMongo;

	     @Override
	    public String saveFile(MultipartFile file) throws IOException {
	    	 
	    	 //Sql
	        FilesEntity filesEntity = new FilesEntity();
	        filesEntity.setFileName(file.getOriginalFilename());
	        filesEntity.setFileType(file.getContentType());
	        filesEntity.setData(file.getBytes());
	        fileRepo.save(filesEntity);
	        
	        //mongo
	        FilesEntityMongo filesEntityMongo = new FilesEntityMongo();
	        filesEntityMongo.setFileName(file.getOriginalFilename());
	        filesEntityMongo.setFileType(file.getContentType());
	        filesEntityMongo.setData(file.getBytes());
	        fileRepoMongo.save(filesEntityMongo);

	        return "Image Insert Successfully.. " + file.getOriginalFilename();
	    }

}
