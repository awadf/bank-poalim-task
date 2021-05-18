package com.poalim.microservice2.controller;

import com.poalim.microservice2.controller.dto.PersonDTO;
import com.poalim.microservice2.enums.State;
import com.poalim.microservice2.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.poalim.microservice2.config.Constants.MICROSERVICE_RESOURCE_URL;

/**
 * @author - FAwad
 */
@RestController
@RequestMapping("/api")
public class BankController {

	private final BankService bankService;

	@Autowired
	public BankController(BankService bankService) {
		this.bankService = bankService;
	}

	@RequestMapping(value = "/bank/persons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> getPersons() {
		return bankService.getPersons();
	}

	@RequestMapping(value = "/bank/persons/name-startwith", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> getPersonsByNameStartingWithAOrK() {
		return bankService.getPersonsByNameStartingWithAOrK();
	}

	@RequestMapping(value = "/bank/persons/avg-weight", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> findPersonsAboveAvgWeightInIsraelState() {
		return bankService.findPersonsAboveAvgWeightInIsraelState();
	}

}
