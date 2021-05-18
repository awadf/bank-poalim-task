package com.poalim.microservice1.service;

import com.poalim.microservice1.controller.dtos.AddressDTO;
import com.poalim.microservice1.controller.dtos.PersonDTO;
import com.poalim.microservice1.controller.errors.PersonNotFoundException;
import com.poalim.microservice1.domain.Person;
import com.poalim.microservice1.enums.State;
import com.poalim.microservice1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author - FAwad
 */
@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public List<PersonDTO> findPersonsAboveAvgWeightByState(State state) {
		final Double avgWeightByState = personRepository.getAvgWeightByState(state);
		final List<Person> personListAboveAvgWeightByState = personRepository.findByWeightGreaterThan(avgWeightByState);
		List<PersonDTO> personDTOListAboveAvgWeightByState = personListAboveAvgWeightByState.stream().map(PersonDTO::convertToDto).collect(Collectors.toList());

		return personDTOListAboveAvgWeightByState;
	}

	@Override
	public List<PersonDTO> findPersonsByNamesStartingWith(List<String> nameSearchTerms) {
		final Query query = new Query();
		final List<Criteria> criteria = new ArrayList<>();
		nameSearchTerms.forEach(searchTerm -> {
			criteria.add(Criteria.where("name").regex("^" + searchTerm));
		});

		if (criteria.size() > 0) {
			query.addCriteria(new Criteria().orOperator(criteria.toArray(new Criteria[criteria.size()])));
		}

		List<Person> personListStartingWithName = mongoTemplate.find(query, Person.class);
		List<PersonDTO> personDtoListStartingWithName = personListStartingWithName.stream().map(PersonDTO::convertToDto).collect(Collectors.toList());
		return personDtoListStartingWithName;
	}

	@Override
	public List<PersonDTO> findAll() {
		List<Person> personList = personRepository.findAll();
		List<PersonDTO> personDtoList = personList.stream().map(PersonDTO::convertToDto).collect(Collectors.toList());
		return personDtoList;
	}

	@Override
	public PersonDTO getPersonById(String id) {
		return personRepository.findById(id).map(PersonDTO::convertToDto).orElseThrow(() -> new PersonNotFoundException(id));
	}

	@Override
	public PersonDTO createPerson(PersonDTO personDTO) {
		Person person = new Person();
		person.setId(personDTO.getId());
		person.setName(personDTO.getName());
		person.setAge(personDTO.getAge());
		person.setGender(personDTO.getGender());
		person.setHeight(personDTO.getHeight());
		person.setWeight(personDTO.getWeight());
		person.setAddress(AddressDTO.convertToEntity(personDTO.getAddressDTO()));
		Person personCreated = personRepository.save(person);
		return PersonDTO.convertToDto(personCreated);
	}

	@Override
	public PersonDTO updatePerson(String id, PersonDTO personDTO) {
		return personRepository.findById(id).map(person -> {
			person.setId(personDTO.getId());
			person.setName(personDTO.getName());
			person.setAge(personDTO.getAge());
			person.setGender(personDTO.getGender());
			person.setHeight(personDTO.getHeight());
			person.setWeight(personDTO.getWeight());
			person.setAddress(AddressDTO.convertToEntity(personDTO.getAddressDTO()));
			Person personUpdated = personRepository.save(person);
			return PersonDTO.convertToDto(personUpdated);
		}).orElseThrow(() -> new PersonNotFoundException(id));
	}

	@Override
	public void deletePerson(String id) {
		personRepository.deleteById(id);
	}
}
