/**
 * 
 */
package pk.edu.pucit.exceptions;

import java.util.Date;

/**
 * @author Razi Ahmad
 *
 */
public class BaseErrorDetails {

	private Date timestamp;
	private String message;
	private String errorDetails;

	/**
	 * @param timestamp
	 * @param message
	 * @param errorDetails
	 */
	public BaseErrorDetails(Date timestamp, String message, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorDetails = errorDetails;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the errorDetails
	 */
	public String getErrorDetails() {
		return errorDetails;
	}
}
