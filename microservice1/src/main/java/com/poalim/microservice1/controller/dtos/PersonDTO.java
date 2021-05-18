package com.poalim.microservice1.controller.dtos;

import com.poalim.microservice1.domain.Person;
import com.poalim.microservice1.enums.Gender;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author - FAwad
 */
@Data
public class PersonDTO {

	@NotNull(message = "Id must not be null")
	@Size(min = 3, max = 40, message = "Id size must be between 2 and 40")
	private String id;

	@NotNull(message = "Name must not be null")
	@Size(min = 3, max = 20, message = "Name size must be between 3 and 20")
	private String name;

	@NotNull(message = "Age must not be null")
	@Min(value = 0, message = "Age must be higher or equal to 0")
	@Max(value = 200, message = "Age must be lower or equal to 200")
	private int age;

	@NotNull(message = "Gender must not be null")
	private Gender gender;

	@NotNull(message = "Height must not be null")
	@Min(value = 0, message = "Height must be higher or equal to 0")
	private double height;

	@Min(value = 0, message = "Weight must be higher or equal to 0")
	private double weight;

	@NotNull(message = "Address must not be null")
	@Valid
	private AddressDTO addressDTO;

	public static PersonDTO convertToDto(Person person) {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(person.getId());
		personDTO.setName(person.getName());
		personDTO.setAge(person.getAge());
		personDTO.setGender(person.getGender());
		personDTO.setHeight(person.getHeight());
		personDTO.setWeight(person.getWeight());
		personDTO.setAddressDTO(AddressDTO.convertToDto(person.getAddress()));
		return personDTO;
	}
}
