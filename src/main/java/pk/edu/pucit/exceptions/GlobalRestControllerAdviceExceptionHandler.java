/**
 * 
 */
package pk.edu.pucit.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Razi Ahmad
 *
 */
@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public BaseErrorDetails usernameNotFound(UserNameNotFoundException ex, WebRequest request) {
		return new BaseErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public BaseErrorDetails userNotFound(UserNotFoundException ex, WebRequest request) {
		return new BaseErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	}

	@ExceptionHandler(SecurityException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseErrorDetails security(SecurityException ex, WebRequest request) {
		return new BaseErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	}

}
