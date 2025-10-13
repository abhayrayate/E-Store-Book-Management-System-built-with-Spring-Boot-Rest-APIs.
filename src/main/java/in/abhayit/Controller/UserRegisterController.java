package in.abhayit.Controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.abhayit.Entity.UserRegister;
import in.abhayit.Model.LoginRequestDto;
import in.abhayit.Model.ResponseMessage;
import in.abhayit.Model.UserRequestDto;
import in.abhayit.Service.UserRegisterService;
import in.abhayit.Utitity.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserRegisterController ", description = "UserRegister Register and Login")
@RestController
public class UserRegisterController {

	@Autowired
	private UserRegisterService userRegisterService;

	@Operation(summary = "Create User Register", description = "e commerece online books store  register the users")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "user register successfully"),
			@ApiResponse(responseCode = "400", description = "user register failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/userregisters")
	public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody UserRequestDto userRequestDto) {

		try {
			if (userRequestDto.getEmail() == null || userRequestDto.getEmail().isEmpty()
					|| userRequestDto.getPassword() == null || userRequestDto.getPassword().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and passowrd cannot be empty"));
			}
			UserRegister userRegister = userRegisterService.insertUserRegister(userRequestDto);
			if (userRegister != null) {
//			       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "online bookstore save successfully", userRegister));
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"online bookstore save successfully", userRegister));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Register Failed", userRegister));

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

//	 -----------------Login user-----------------
	@Operation(summary = "Login User", description = "Validate user credentials and login")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "User logged in successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request data or credentials"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/userLogin")
	public ResponseEntity<ResponseMessage> checkLogin(@RequestBody LoginRequestDto loginRequestDto) {

		try {
			if (loginRequestDto.getEmail() == null || loginRequestDto.getEmail().isEmpty()
					|| loginRequestDto.getPassword() == null || loginRequestDto.getPassword().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and passowrd cannot be empty"));
			}
			UserRegister userRegister = userRegisterService.checkUserDetails(loginRequestDto);
			if (userRegister != null) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,
						Constants.SUCCESS, "User Login successfully", userRegister));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Register Failed", userRegister));

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Invalid Credentials"));
		}
	}

//	 ------------Get User Data  By ID -------------------------------

	@Operation(summary = "Get User By ID", description = "Fetch user details using the user ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "User details fetched successfully"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/userDetails/{id}")
	public ResponseEntity<ResponseMessage> getUserById(@PathVariable Long id) {
		try {
			UserRegister userRegister = userRegisterService.getUserById(id);
			if (userRegister != null) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,
						Constants.SUCCESS, "User details fetched successfully", userRegister));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(
						HttpURLConnection.HTTP_NOT_FOUND, Constants.FAILED, "User not found", null));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

//---------------------------------------------------

	@Operation(summary = "Register User with Multiple Files", description = "Register a new user and upload multiple files (images/documents) in a single request")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "User registered successfully with files"),
			@ApiResponse(responseCode = "400", description = "Invalid request data or file upload failed"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/userregistersuploadmulti")
	public ResponseEntity<ResponseMessage> createUserRegisterUploadfiles(@RequestParam String jsonData,
			@RequestParam MultipartFile[] files) {

		try {

			UserRequestDto userRequestDto = new ObjectMapper().readValue(jsonData, UserRequestDto.class);

			UserRegister userRegister = userRegisterService.uploadMultiUserRegister(userRequestDto, files);

			if (userRegister != null) {

				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"online bookstore save successfully", userRegister));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Register Failed", userRegister));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

//---------------------------get all details from db using cacheing

	@Operation(summary = "Get All Users", description = "Fetch all registered users from the database")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Fetched all users successfully"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/getallusers")
	public List<UserRegister> getallUserDetails() {
		List<UserRegister> alluserdetails = userRegisterService.getallUser();
		return alluserdetails;
	}

}
