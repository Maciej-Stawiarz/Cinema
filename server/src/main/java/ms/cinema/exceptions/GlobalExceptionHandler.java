package ms.cinema.exceptions;

import ms.cinema.exceptions.models.exceptions.DataMismatchException;
import ms.cinema.exceptions.models.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
		return new ResponseEntity<>(
				exception.getMessage(),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
		return new ResponseEntity<>(
				exception.getMessage(),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException exception) {
		return new ResponseEntity<>(
			exception.getMessage(),
			HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataMismatchException.class)
	public ResponseEntity<String> handleDataMismatchException(DataMismatchException exception) {
		return new ResponseEntity<>(
				exception.getMessage(),
				HttpStatus.BAD_REQUEST);
	}
}
