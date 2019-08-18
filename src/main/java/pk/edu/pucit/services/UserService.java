/**
 * 
 */
package pk.edu.pucit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pk.edu.pucit.entities.User;
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

	public User createUser(User user) {
		return repository.save(user);
	}

	public Optional<User> getUserById(Long id) {
		Optional<User> entity = repository.findById(id);
		return entity;
	}

	public User updateUserById(Long id, User user) {
		user.setId(id);
		return repository.save(user);
	}

	public void deleteUserById(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}
	
	public Optional<User> getUserByUsername(String username){
		return repository.findByUsername(username);
	}
}
