/**
 * 
 */
package pk.edu.pucit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pk.edu.pucit.entities.Order;
import pk.edu.pucit.entities.User;
import pk.edu.pucit.exceptions.UserNotFoundException;
import pk.edu.pucit.repositories.OrderRepository;
import pk.edu.pucit.repositories.UserRepository;

/**
 * @author Razi Ahmad
 *
 */
@Service
public class OrderService implements IOrderService {

	private OrderRepository repository;

	private UserRepository userRepository;

	/**
	 * @param userRepository
	 */
	public OrderService(OrderRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	@Override
	public List<Order> getAllOrders(Long userid) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userid);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		return user.get().getOrders();
	}

	@Override
	public Order createOrder(Long userid, Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = userOptional.get();
		order.setUser(user);
		return repository.save(order);
	}

	@Override
	public Optional<Order> getOrderByOrderId(Long userid, Long orderid) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		Optional<Order> orderOptional = repository.findById(orderid);
		if (orderOptional.isPresent() && !orderOptional.get().getUser().getId().equals(userOptional.get().getId())) {
			throw new SecurityException("You are not authroized to get this order");
		}
		return orderOptional;
	}

}
