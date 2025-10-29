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
import org.springframework.web.bind.annotation.RestController;

import in.abhayit.Entity.BooksModule;
import in.abhayit.Model.ResponseMessage;
import in.abhayit.Service.BooksService;
import in.abhayit.Utility.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book Controller", description = "Handles all operations related to adding, retrieving, updating, and deleting books in the online bookstore.")

@RestController
public class BooksController {

	@Autowired
	BooksService booksService;

	@Operation(summary = "Create a New Book Entry", description = "Registers a new book in the online bookstore. The request must include both the book name and title.")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Book saved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input — book name or title is missing"),
			@ApiResponse(responseCode = "500", description = "Internal server error while saving book") })
	@PostMapping("/savebooks")
	public ResponseEntity<ResponseMessage> createBooks(@RequestBody BooksModule booksModule) {
		try {
			// Input validation
			if (booksModule.getName() == null || booksModule.getName().isEmpty() || booksModule.getTitle() == null
					|| booksModule.getTitle().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Book name and title cannot be empty"));
			}

			// Save book
			BooksModule savedBook = booksService.custmerSaveBooks(booksModule);

			if (savedBook != null) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"Book has been successfully saved", savedBook));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Failed to save the book"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
							"An unexpected error occurred while saving the book"));
		}
	}

	@Operation(summary = "Fetch All Books", description = "Retrieves the list of all books available in the online bookstore.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Books retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "No books found in the store"),
			@ApiResponse(responseCode = "500", description = "Internal server error while fetching books") })
	@GetMapping("/getAllBooks")
	public ResponseEntity<ResponseMessage> getAllBook() {
		try {
			List<BooksModule> allBooks = booksService.custmergetAllBooks();

			if (allBooks != null && !allBooks.isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Books retrieved successfully", allBooks));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(
						HttpURLConnection.HTTP_NOT_FOUND, Constants.FAILED, "No books found in the store"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
							"An unexpected error occurred while fetching books"));
		}
	}

	@Operation(summary = "Fetch Book by ID", description = "Retrieves a specific book from the online bookstore using its unique ID.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Book retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Book not found with the given ID"),
			@ApiResponse(responseCode = "500", description = "Internal server error while fetching the book") })
	@GetMapping("/getCustBook/{id}")
	public ResponseEntity<ResponseMessage> getByBook(@PathVariable Long id) {
		try {
			BooksModule book = booksService.getByCustmerBookid(id);

			if (book != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Book details retrieved successfully", book));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(
						HttpURLConnection.HTTP_NOT_FOUND, Constants.FAILED, "No book found with ID: " + id));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
							"An unexpected error occurred while retrieving the book"));
		}
	}

}
