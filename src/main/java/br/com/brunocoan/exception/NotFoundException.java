package br.com.brunocoan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8528092047176618723L;

	public NotFoundException(String message) {
		super(message);
	}

	
}
