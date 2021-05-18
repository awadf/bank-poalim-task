package com.poalim.microservice1.repository;

import com.poalim.microservice1.domain.Person;
import com.poalim.microservice1.enums.State;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author - FAwad
 */
public interface PersonRepository extends MongoRepository<Person, String> {

	List<Person> findAll();

	@Aggregation(pipeline = {"{$match:{'address.state' : ?0}}", "{$group:{ _id: '$address.state', avgWeightInIsrael: { $avg: '$weight' }}}"})
	Double getAvgWeightByState(State state);

	List<Person> findByWeightGreaterThan(Double weight);
}
