package br.com.brunocoan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedException extends RuntimeException {

	private static final long serialVersionUID = -7633109099821122347L;

	public DuplicatedException(String message) {
		super(message);
	}
	
	
}
