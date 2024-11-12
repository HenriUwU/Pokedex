package io.tutorial.spring.Pokedex.service;

import java.util.Map;
import java.util.Optional;

import io.tutorial.spring.Pokedex.dto.EvolutionDTO;
import io.tutorial.spring.Pokedex.mapper.EvolutionMapper;
import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.tutorial.spring.Pokedex.model.Evolution;
import io.tutorial.spring.Pokedex.repository.EvolutionRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class EvolutionService {

	private final EvolutionRepository evolutionRepository;
	private final RestTemplate restTemplate;
	private final EvolutionMapper evolutionMapper;

	public EvolutionService(EvolutionRepository evolutionRepository, RestTemplate restTemplate, EvolutionMapper evolutionMapper) {
		this.evolutionRepository = evolutionRepository;
		this.restTemplate = restTemplate;
		this.evolutionMapper = evolutionMapper;
	}

	public Iterable<Evolution> getEvolutions(String username) {
		return evolutionRepository.findByUser_UsernameAndDeletedFalse(username);
	}

	public void	addEvolution(final Evolution evolution) {
		evolutionRepository.save(evolution);
	}

	public void	addEvolutionFromAPI(final String evolutionName, User user, Pokemon pokemon) {
		String url = "https://pokeapi.co/api/v2/pokemon/" + evolutionName;

		try {
			ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<Map<String, Object>>() {}
			);

			EvolutionDTO evolutionDTO = getEvolutionDTO(response);
			Evolution evolution = evolutionMapper.toEntity(evolutionDTO);

			evolution.setUser(user);
			evolution.setPokemon(pokemon);

			evolutionRepository.save(evolution);
		} catch (Exception e) {
			throw new RuntimeException("Error retrieving pokemon from API, at : " + url  + " | ", e);
		}
	}

	private static EvolutionDTO getEvolutionDTO(ResponseEntity<Map<String, Object>> response) {
		Map<String, Object> evolutionData = response.getBody();

		assert evolutionData != null;
		Integer pokedexNumber = (Integer) evolutionData.get("id");
		String name = (String) evolutionData.get("name");
		String description = "A wild " + name + " appeared!";

		@SuppressWarnings("unchecked")
		Map<String, Object> sprites = (Map<String, Object>) evolutionData.get("sprites");
		String imageUrl = (String) sprites.get("front_default");

		if (imageUrl == null || imageUrl.isEmpty())
			imageUrl = "https://img.freepik.com/premium-vector/default-image-icon-vector-missing-picture-page-website-design-mobile-app-no-photo-available_87543-11093.jpg";

        return new EvolutionDTO(pokedexNumber, name, description, imageUrl);
	}

	public void updateEvolution(final Evolution evolution) {
		if (evolutionRepository.existsById(evolution.getPokedexNumber())) {
			evolutionRepository.save(evolution);
		}
	}

	public void	removeEvolution(final int pokedexNumber) {
		Optional<Evolution> optionalEvolution = evolutionRepository.findById(pokedexNumber);
		if (optionalEvolution.isPresent()) {
			Evolution evolution = optionalEvolution.get();
			evolution.setDeleted(true);
			updateEvolution(evolution);
		}
	}

}
