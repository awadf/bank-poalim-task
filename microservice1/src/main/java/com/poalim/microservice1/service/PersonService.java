package com.poalim.microservice1.service;

import com.poalim.microservice1.controller.dtos.PersonDTO;
import com.poalim.microservice1.domain.Person;
import com.poalim.microservice1.enums.State;

import java.util.List;

/**
 * @author - FAwad
 */
public interface PersonService {

	List<PersonDTO> findAll();

	PersonDTO getPersonById(String id);

	PersonDTO createPerson(PersonDTO personDTO);

	PersonDTO updatePerson(String id, PersonDTO personDTO);

	void deletePerson(String id);

	List<PersonDTO> findPersonsByNamesStartingWith(List<String> names);

	List<PersonDTO> findPersonsAboveAvgWeightByState(State state);

}
