package in.abhayit.Controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.abhayit.Model.OrderModuleDto;
import in.abhayit.Model.ResponseMessage;
import in.abhayit.Service.OrderService;
import in.abhayit.Utility.Constants;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/orderplaced")
	public ResponseEntity<ResponseMessage> createOrder(@RequestBody OrderModuleDto orderModuleDto) {

		try {
			// service method
			String saveOrders = orderService.saveOrders(orderModuleDto);

			if (saveOrders.toLowerCase().contains("success")) {

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
						"Order placed successfully", saveOrders));

			} else {

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Order placement failed", saveOrders));
			}

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error"));
		}
	}
}
