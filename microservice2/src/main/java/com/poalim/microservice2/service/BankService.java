package com.poalim.microservice2.service;

import com.poalim.microservice2.controller.dto.PersonDTO;

import java.util.List;

/**
 * @author - FAwad
 */
public interface BankService {

	List<PersonDTO> getPersons();

	List<PersonDTO> getPersonsByNameStartingWithAOrK();

	List<PersonDTO> findPersonsAboveAvgWeightInIsraelState();
}
