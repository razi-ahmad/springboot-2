/**
 * 
 */
package pk.edu.pucit.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pk.edu.pucit.entities.Order;
import pk.edu.pucit.entities.User;
import pk.edu.pucit.exceptions.UserNotFoundException;
import pk.edu.pucit.services.IUserService;

/**
 * @author Razi Ahmad
 *
 */
@Validated
@RestController
@RequestMapping(value = "/hateoas/users")
public class UserHateoasController {

	private IUserService service;

	/**
	 * @param repository
	 */
	@Autowired
	public UserHateoasController(IUserService service) {
		this.service = service;
	}

	@GetMapping(value = "/{id}")
	public Resource<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = service.getUserById(id);
			User user = userOptional.get();
			Long userid = user.getUserid();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
			Resource<User> finalResource = new Resource<User>(user);
			return finalResource;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@GetMapping
	public Resources<User> getAllUsers() throws UserNotFoundException {
		List<User> users = service.getAllUsers();
		for (User user : users) {
			Long userid = user.getUserid();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
			Resources<Order> orders = ControllerLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
			Link ordersLink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		Link selfLink=ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
		Resources<User> finalResources = new Resources<User>(users,selfLink);
		return finalResources;
	}
}
