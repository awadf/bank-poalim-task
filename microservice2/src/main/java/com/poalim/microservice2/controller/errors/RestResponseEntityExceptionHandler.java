package com.poalim.microservice2.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author - FAwad
 */
@RestControllerAdvice
class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler(InterServiceCommunicationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String interServiceCommunicationExceptionHandler(InterServiceCommunicationException ex) {
		return ex.getMessage();
	}
}