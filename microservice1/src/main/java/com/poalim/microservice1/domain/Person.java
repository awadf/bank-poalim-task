package com.poalim.microservice1.domain;

import com.poalim.microservice1.enums.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author - FAwad
 */
@Data
@Document("person")
public class Person {

	@Id
	private String id;

	private String name;

	private int age;

	private Gender gender;

	private double height;

	private double weight;

	private Address address;
}
