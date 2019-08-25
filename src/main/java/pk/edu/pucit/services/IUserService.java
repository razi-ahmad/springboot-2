/**
 * 
 */
package pk.edu.pucit.services;

import java.util.List;
import java.util.Optional;

import pk.edu.pucit.entities.User;
import pk.edu.pucit.exceptions.UserExistsException;
import pk.edu.pucit.exceptions.UserNameNotFoundException;
import pk.edu.pucit.exceptions.UserNotFoundException;

/**
 * @author Razi Ahmad
 *
 */
public interface IUserService {

	List<User> getAllUsers();

	User createUser(User user) throws UserExistsException;

	Optional<User> getUserById(Long id) throws UserNotFoundException;

	User updateUserById(Long id, User user) throws UserNotFoundException;

	void deleteUserById(Long id);

	Optional<User> getUserByUsername(String username) throws UserNameNotFoundException;

}
