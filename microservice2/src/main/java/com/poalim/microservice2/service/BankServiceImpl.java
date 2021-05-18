package com.poalim.microservice2.service;

import com.poalim.microservice2.controller.dto.PersonDTO;
import com.poalim.microservice2.controller.errors.InterServiceCommunicationException;
import com.poalim.microservice2.enums.State;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.poalim.microservice2.config.Constants.MICROSERVICE_RESOURCE_URL;

/**
 * @author - FAwad
 */
@Service
public class BankServiceImpl implements BankService {

	private final RestTemplate restTemplate;
	private final UriComponentsBuilder uriComponentsBuilder;

	public BankServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(MICROSERVICE_RESOURCE_URL).path("/api");
	}

	@Override
	public List<PersonDTO> getPersons() {
		try {
			final UriComponentsBuilder uriBuilder = this.uriComponentsBuilder.cloneBuilder().path("/persons");
			ResponseEntity<List<PersonDTO>> responseEntity = this.restTemplate.exchange(
					uriBuilder.toUriString(),
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<>() {
					});

			List<PersonDTO> personDTOList = responseEntity.getBody();
			return personDTOList;
		} catch (RestClientException e) {
			throw new InterServiceCommunicationException(e.getMessage());
		}
	}

	@Override
	public List<PersonDTO> getPersonsByNameStartingWithAOrK() {
		UriComponentsBuilder uriBuilder = this.uriComponentsBuilder.cloneBuilder().path("/persons/name-startwith");
		ResponseEntity<List<PersonDTO>> responseEntity = this.restTemplate.exchange(
				uriBuilder.queryParam("name", "A", "K").toUriString(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<>() {
				});

		List<PersonDTO> allPersonsList = responseEntity.getBody();
		return allPersonsList;
	}

	@Override
	public List<PersonDTO> findPersonsAboveAvgWeightInIsraelState() {
		UriComponentsBuilder uriBuilder = this.uriComponentsBuilder.cloneBuilder().path("/persons/avg-weight");
		ResponseEntity<List<PersonDTO>> responseEntity = this.restTemplate.exchange(
				uriBuilder.queryParam("state", State.Israel).toUriString(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<>() {
				});

		List<PersonDTO> allPersonsList = responseEntity.getBody();
		return allPersonsList;
	}
}
