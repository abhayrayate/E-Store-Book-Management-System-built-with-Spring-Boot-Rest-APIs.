package in.abhayit.Controller;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.abhayit.Service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "FileController ",description = "UploadImage")
@RestController
public class FileController {
	
	@Autowired
	private FileService fileService;

    @Operation(
    		summary="Add New Image",
    		description ="Insert a new Image into the database",
    		responses= {
	     @ApiResponse(responseCode = "201",description = "Image Saved  successfully"),
	     @ApiResponse(responseCode = "400",description = "Invalid Image data"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     }
    	)
	@PostMapping("/uploadimage")
	public ResponseEntity<String> uploadFiles(@RequestParam MultipartFile file) throws IOException{

	  String response =fileService.saveFile(file);
		
	  return ResponseEntity.status(HttpStatus.CREATED).body(response);

		}
	
	
	@PostMapping("/uploadmultiimages")
	public ResponseEntity<List<Object>>  uploadMultiFile(@RequestParam MultipartFile[] file) throws IOException{
		
		List<Object> response = Arrays.stream(file).map(s->{
			try {
				return fileService.saveFile(s);
				
				
			}catch (Exception e) {
				return "files upload failed" +e.getLocalizedMessage();
			}
		}).collect(Collectors.toList());
		return ResponseEntity.ok(response);		
	}
}
