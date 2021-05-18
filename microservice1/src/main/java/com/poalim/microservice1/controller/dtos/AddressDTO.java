package com.poalim.microservice1.controller.dtos;

import com.poalim.microservice1.domain.Address;
import com.poalim.microservice1.domain.Person;
import com.poalim.microservice1.enums.State;
import com.poalim.microservice1.enums.constraint.EnumPattern;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author - FAwad
 */
@Data
public class AddressDTO {

	@NotNull(message = "State must not be null")
	@EnumPattern(regexp = "^Israel$", message = "Allowed input for state: Israel")
	private State state;

	@NotNull(message = "City must not be null")
	@Size(min = 3, max = 20, message = "City size must be between 3 and 20")
	private String city;

	@NotNull(message = "Street must not be null")
	@Size(min = 3, max = 50, message = "Street size must be between 3 and 50")
	private String street;

	@NotNull(message = "Zipcode must not be null")
	@Pattern(regexp = "^\\d+$", message = "Zipcode must contains only numbers")
	private String zipcode;

	@NotNull(message = "ContainAnimals must not be null")
	@Pattern(regexp = "^true$|^false$", message = "Allowed input for containAnimals: true or false")
	private String containsAnimals;

	public static AddressDTO convertToDto(Address address) {
		final AddressDTO addressDTO = new AddressDTO();
		addressDTO.setState(address.getState());
		addressDTO.setCity(address.getCity());
		addressDTO.setStreet(address.getStreet());
		addressDTO.setZipcode(address.getZipcode());
		addressDTO.setContainsAnimals(address.isContainsAnimals() ? "true" : "false");
		return addressDTO;
	}

	public static Address  convertToEntity(AddressDTO addressDTO) {
		final Address address = new Address();
		address.setState(addressDTO.getState());
		address.setCity(addressDTO.getCity());
		address.setStreet(addressDTO.getStreet());
		address.setZipcode(addressDTO.getZipcode());
		address.setContainsAnimals("true".equals(addressDTO.getContainsAnimals()) ? true : false);
		return address;
	}
}


