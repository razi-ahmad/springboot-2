/**
 * 
 */
package pk.edu.pucit.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Razi Ahmad
 *
 */
@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public BaseErrorDetails usernameNotFound(UserNameNotFoundException ex) {
		return new BaseErrorDetails(new Date(), "From @RestControllerAdvie NOT FOUND", ex.getMessage());
	}

}
