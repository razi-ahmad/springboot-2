/**
 * 
 */
package pk.edu.pucit.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pk.edu.pucit.entities.Order;
import pk.edu.pucit.exceptions.UserNotFoundException;
import pk.edu.pucit.services.IOrderService;

/**
 * @author Razi Ahmad
 *
 */
@Validated
@RestController
@RequestMapping(value = "/v1/api/users")
public class OrderController extends AbstractController {

	private IOrderService service;

	/**
	 * @param service
	 */
	@Autowired
	public OrderController(IOrderService service) {
		this.service = service;
	}

	@GetMapping(value = "/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable @Min(1) Long userid) throws UserNotFoundException {
		return service.getAllOrders(userid);
	}

	@PostMapping(value = "/{userid}/orders")
	public Order createOrder(@PathVariable("userid") @Min(1) Long userid, @RequestBody Order order)
			throws UserNotFoundException {
		return service.createOrder(userid, order);
	}

	@GetMapping(value = "/{userid}/orders/{orderid}")
	public Optional<Order> getOrderByOrderId(@PathVariable("userid") @Min(1) Long userid,
			@PathVariable("orderid") @Min(1) Long orderid) throws UserNotFoundException {
		return service.getOrderByOrderId(userid, orderid);
	}
}
