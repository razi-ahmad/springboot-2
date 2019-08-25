/**
 * 
 */
package pk.edu.pucit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pk.edu.pucit.entities.Order;

/**
 * @author Razi Ahmad
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
