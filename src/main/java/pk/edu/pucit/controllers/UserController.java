/**
 * 
 */
package pk.edu.pucit.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import pk.edu.pucit.entities.User;
import pk.edu.pucit.exceptions.UserExistsException;
import pk.edu.pucit.exceptions.UserNameNotFoundException;
import pk.edu.pucit.exceptions.UserNotFoundException;
import pk.edu.pucit.services.IUserService;

/**
 * @author Razi Ahmad
 *
 */
@Validated
@RestController
@RequestMapping(value = "v1/api/users")
public class UserController extends AbstractController {

	private IUserService service;

	@Autowired
	public UserController(IUserService service) {
		this.service = service;
	}

	@GetMapping
	public List<User> getAllUsers() {

		return service.getAllUsers();
	}

	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			service.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@PutMapping(value = "/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return service.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@GetMapping(value = "/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1)Long id) {
		try {
			return service.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@DeleteMapping(value = "/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		service.deleteUserById(id);
	}

	@GetMapping(value = "/byusername/{username}")
	public Optional<User> getUserByUsername(@PathVariable("username") String username)
			throws UserNameNotFoundException {
		return service.getUserByUsername(username);
	}
}
