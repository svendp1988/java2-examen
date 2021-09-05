package be.pxl.paj.museum.api.handler;

import be.pxl.paj.museum.exception.BusinessException;
import be.pxl.paj.museum.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(value = {
			BusinessException.class
	})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage businessException(RuntimeException ex) {
		return new ErrorMessage(ex.getMessage());
	}

	@ExceptionHandler(value = {NotFoundException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage resourceNotFoundException(RuntimeException ex) {
		return new ErrorMessage(ex.getMessage());
	}

}

