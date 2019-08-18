/**
 * 
 */
package pk.edu.pucit.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pk.edu.pucit.entities.User;

/**
 * @author Razi Ahmad
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
