package in.abhayit.Controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.abhayit.Entity.CartModule;
import in.abhayit.Model.ResponseMessage;
import in.abhayit.Service.CartModuleService;
import in.abhayit.Utility.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/addcart")
@Tag(name = "Cart APIs", description = "APIs for adding and deleting cart items")
public class CartModuleController {

	@Autowired
	private CartModuleService cartModuleService;

	@Operation(summary = "Add item to cart", description = "Adds a book to customer's cart or updates quantity if already exists", responses = {
			@ApiResponse(responseCode = "201", description = "Cart item added successfully"),
			@ApiResponse(responseCode = "400", description = "Failed to add cart item"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/add")
	public ResponseEntity<ResponseMessage> addToCart(
			@Parameter(description = "Customer ID", required = true) @RequestParam Long customerId,
			@Parameter(description = "Book ID", required = true) @RequestParam Long bookId,
			@Parameter(description = "Quantity to add", required = true) @RequestParam int quantity) {
		try {
			CartModule cart = cartModuleService.addToCart(customerId, bookId, quantity);

			if (cart != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
						"Cart item added successfully", cart));
			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Failed to add cart item", null));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error: " + e.getMessage()));
		}
	}

	@Operation(summary = "Delete item from cart", description = "Deletes a cart item by its ID", responses = {
			@ApiResponse(responseCode = "200", description = "Cart deleted successfully"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseMessage> deleteCart(
			@Parameter(description = "Cart ID to delete", required = true) @PathVariable Long id) {
		cartModuleService.deleteToCart(id);
		return ResponseEntity
				.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Cart deleted successfully"));
	}

	// update

	@Operation(
		    summary = "Update item in cart",
		    description = "Updates an existing cart item by its ID. You can modify the quantity, customer, or book details. "
		                + "The total price is automatically recalculated based on the updated book price and quantity.",
		    responses = {
		        @ApiResponse(responseCode = "200", description = "Cart updated successfully"),
		        @ApiResponse(responseCode = "404", description = "Cart not found"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    }
		)
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseMessage> updateCart(
			@Parameter(description = "Cart ID to update", required = true) @PathVariable Long id,
			@RequestBody CartModule updatedCart) {

		cartModuleService.updateCart(id, updatedCart);

		return ResponseEntity
				.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Cart updated successfully"));
	}

}
