/**
 * 
 */
package pk.edu.pucit.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pk.edu.pucit.entities.Order;
import pk.edu.pucit.entities.User;
import pk.edu.pucit.exceptions.UserNotFoundException;
import pk.edu.pucit.repositories.UserRepository;

/**
 * @author Razi Ahmad
 *
 */
@Validated
@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {


	private UserRepository userRepository;

	/**
	 * @param repository
	 * @param userRepository
	 */
	@Autowired
	public OrderHateoasController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping(value = "/{userid}/orders")
	public Resources<Order> getAllOrders(@PathVariable @Min(1) Long userid) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userid);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		List<Order> orders = user.get().getOrders();
		Resources<Order> finalResources = new Resources<>(orders);
		return finalResources;
	}
}
