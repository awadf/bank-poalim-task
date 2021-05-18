package com.poalim.microservice2.controller.dto;

import com.poalim.microservice2.enums.State;
import lombok.Data;

/**
 * @author - FAwad
 */
@Data
public class AddressDTO {

	private State state;

	private String city;

	private String street;

	private String zipcode;

	private String containsAnimals;

}


