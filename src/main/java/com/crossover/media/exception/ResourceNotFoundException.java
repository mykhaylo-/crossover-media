package com.crossover.media.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such object")
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -7015094984286520393L;

	public ResourceNotFoundException() {
		super();
	}
}
