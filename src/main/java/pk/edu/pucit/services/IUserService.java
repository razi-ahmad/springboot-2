/**
 * 
 */
package pk.edu.pucit.services;

import java.util.List;
import java.util.Optional;

import pk.edu.pucit.entities.User;

/**
 * @author Razi Ahmad
 *
 */
public interface IUserService {

	List<User> getAllUsers();

	User createUser(User user);

	Optional<User> getUserById(Long id);

	User updateUserById(Long id, User user);
	
	void deleteUserById(Long id);
	
	Optional<User> getUserByUsername(String username); 

}
