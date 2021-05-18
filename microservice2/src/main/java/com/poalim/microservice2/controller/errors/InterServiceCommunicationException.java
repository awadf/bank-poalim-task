package com.poalim.microservice2.controller.errors;

import org.springframework.http.HttpStatus;

/**
 * @author - FAwad
 */
public class InterServiceCommunicationException extends RuntimeException {

	public InterServiceCommunicationException(String message) {
		super("Fetching data from other microservice failed with message: " + message);
	}
}