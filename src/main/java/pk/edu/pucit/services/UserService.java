/**
 * 
 */
package pk.edu.pucit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import pk.edu.pucit.entities.User;
import pk.edu.pucit.exceptions.UserExistsException;
import pk.edu.pucit.exceptions.UserNotFoundException;
import pk.edu.pucit.repositories.UserRepository;

/**
 * @author Razi Ahmad
 *
 */
@Service
public class UserService extends AbstractService implements IUserService {

	private UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	/**
	 * Returns all instances of the pk.edu.pucit.entites.User
	 *
	 * @return all entities
	 */
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public User createUser(User user) throws UserExistsException{
		Optional<User> existingEntity = repository.findByUsername(user.getUsername());
		if(existingEntity.isPresent()) {
			throw new UserExistsException("User already exists in User Repository");
		}
		return repository.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> entity = repository.findById(id);
		if (!entity.isPresent()) {
			throw new UserNotFoundException("User Not found in user Repository");
		}
		return entity;
	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {
		if (!repository.existsById(id)) {
			throw new UserNotFoundException("User Not found in user Repository, provide the correct user id");
		}
		user.setId(id);
		return repository.save(user);
	}

	public void deleteUserById(Long id) {
		if (!repository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User Not found in user Repository, provide the correct user id");
		}
		repository.deleteById(id);
	}

	public Optional<User> getUserByUsername(String username) {
		return repository.findByUsername(username);
	}
}
