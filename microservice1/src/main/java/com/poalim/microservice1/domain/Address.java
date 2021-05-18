package com.poalim.microservice1.domain;

import com.poalim.microservice1.enums.State;
import lombok.Data;

/**
 * @author - FAwad
 */
@Data
public class Address {

	private State state;

	private String city;

	private String street;

	private String zipcode;

	private boolean containsAnimals;
}
