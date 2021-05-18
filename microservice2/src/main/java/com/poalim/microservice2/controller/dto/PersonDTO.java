package com.poalim.microservice2.controller.dto;

import com.poalim.microservice2.enums.Gender;
import lombok.Data;

/**
 * @author - FAwad
 */
@Data
public class PersonDTO {

	private String id;

	private String name;

	private int age;

	private Gender gender;

	private double height;

	private double weight;

	private AddressDTO addressDTO;

}
