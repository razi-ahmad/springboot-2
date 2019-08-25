/**
 * 
 */
package pk.edu.pucit.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Razi Ahmad
 *
 */
@ControllerAdvice
public class GlobalControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BaseErrorDetails errorDetails = new BaseErrorDetails(new Date(),
				"From MethodArgumentNotValidException in GEH", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BaseErrorDetails errorDetails = new BaseErrorDetails(new Date(),
				"From HttpRequestMethodNotSupportedException in GEH", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/*
	 * @ExceptionHandler(UserNameNotFoundException.class) public final
	 * ResponseEntity<Object> handleUserNameNotFound(UserNameNotFoundException ex,
	 * WebRequest request) {
	 * 
	 * CustomErrorDetails errorDetails = new CustomErrorDetails(new Date(),
	 * ex.getMessage(), request.getDescription(false)); return new
	 * ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); }
	 */

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {

		BaseErrorDetails errorDetails = new BaseErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
