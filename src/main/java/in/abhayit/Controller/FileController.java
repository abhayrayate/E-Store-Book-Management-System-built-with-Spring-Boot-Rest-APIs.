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
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "FileController", description = "Endpoints for uploading images")
@RestController
public class FileController {

	@Autowired
	private FileService fileService;

	@Operation(summary = "Upload Single Image", description = "Upload a single image file to the database")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Image saved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid image data"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/uploadimage")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) {
		try {
			String response = fileService.saveFile(file);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("File upload failed: " + e.getMessage());
		}
	}

	@Operation(summary = "Upload Multiple Images", description = "Upload multiple image files to the database")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Files uploaded successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid file data"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/uploadmultiimages")
	public ResponseEntity<List<Object>> uploadMultipleFiles(@RequestParam MultipartFile[] files) {
		List<Object> response = Arrays.stream(files).map(file -> {
			try {
				return fileService.saveFile(file);
			} catch (Exception e) {
				return "File upload failed: " + e.getLocalizedMessage();
			}
		}).collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}
}
