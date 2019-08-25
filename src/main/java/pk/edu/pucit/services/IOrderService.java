/**
 * 
 */
package pk.edu.pucit.services;

import java.util.List;
import java.util.Optional;

import pk.edu.pucit.entities.Order;
import pk.edu.pucit.exceptions.UserNotFoundException;

/**
 * @author Razi Ahmad
 *
 */
public interface IOrderService {

	List<Order> getAllOrders(Long userid) throws UserNotFoundException;

	Order createOrder(Long userid, Order order) throws UserNotFoundException;

	Optional<Order> getOrderByOrderId(Long userid, Long orderid) throws UserNotFoundException;
}
