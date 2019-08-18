/**
 * 
 */
package pk.edu.pucit.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pk.edu.pucit.entities.User;
import pk.edu.pucit.services.IUserService;

/**
 * @author Razi Ahmad
 *
 */
@RestController
public class UserController extends AbstractController {

	private IUserService service;

	@Autowired
	public UserController(IUserService service) {
		this.service = service;
	}

	@GetMapping(value = "v1/api/users")
	public List<User> getAllUsers() {

		return service.getAllUsers();
	}

	@PostMapping(value = "v1/api/users")
	public User createUser(@RequestBody User user) {
		return service.createUser(user);
	}

	@PutMapping(value = "v1/api/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		return service.updateUserById(id, user);
	}

	@GetMapping(value = "v1/api/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		return service.getUserById(id);
	}

	@DeleteMapping(value = "v1/api/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		service.deleteUserById(id);
	}

	@GetMapping(value = "v1/api/users/byusername/{username}")
	public Optional<User> getUserByUsername(@PathVariable("username") String username) {
		return service.getUserByUsername(username);
	}
}
