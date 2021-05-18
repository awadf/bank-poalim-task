package com.poalim.microservice1.controller;

import com.poalim.microservice1.controller.dtos.PersonDTO;
import com.poalim.microservice1.enums.State;
import com.poalim.microservice1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author - FAwad
 */
@RestController
@RequestMapping("/api")
public class PersonController {

	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> getAllPersons() {
		return personService.findAll();
	}

	@RequestMapping(value = "/persons/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO getById(@PathVariable String id) {
		return personService.getPersonById(id);
	}

	@RequestMapping(value = "/persons", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO createPerson(@Valid @RequestBody PersonDTO personDTORequest) {
		return personService.createPerson(personDTORequest);
	}

	@RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO updatePerson(@PathVariable String id, @Valid @RequestBody PersonDTO personDTORequest) {
		return personService.updatePerson(id, personDTORequest);
	}

	@RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletePerson(@PathVariable String id) {
		personService.deletePerson(id);
	}

	@RequestMapping(value = "/persons/name-startwith", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> findPersons(@RequestParam(value = "name", required = false) List<String> nameSearchTerms) {
		return personService.findPersonsByNamesStartingWith(nameSearchTerms);
	}

	@RequestMapping(value = "/persons/avg-weight", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> findPersonsAboveAvgWeightByState(@RequestParam(value = "state") State state) {
		return personService.findPersonsAboveAvgWeightByState(state);
	}
}
