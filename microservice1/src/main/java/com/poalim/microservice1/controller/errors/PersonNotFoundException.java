package com.poalim.microservice1.controller.errors;

import com.poalim.microservice1.domain.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author - FAwad
 */
public class PersonNotFoundException extends RuntimeException {

	public PersonNotFoundException(String id) {
		super("Could not find person with id: " + id);
	}
}