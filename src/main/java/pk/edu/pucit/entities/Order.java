/**
 * 
 */
package pk.edu.pucit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Razi Ahmad
 *
 */
@Entity
@Table(name = "orders")
public class Order extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderid;

	@Column(name = "ORDER_DESCRIPTION", length = 100, nullable = false)
	private String orderDescription;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	/**
	 * 
	 */
	public Order() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getOrderid() {
		return orderid;
	}

	/**
	 * @param id the id to set
	 */
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return the orderDescription
	 */
	public String getOrderDescription() {
		return orderDescription;
	}

	/**
	 * @param orderDescription the orderDescription to set
	 */
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", orderDescription=" + orderDescription + "]";
	}
}
