package io.tutorial.spring.Pokedex.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.tutorial.spring.Pokedex.dto.PokemonDTO;
import io.tutorial.spring.Pokedex.dto.PokemonResponse;
import io.tutorial.spring.Pokedex.mapper.PokemonMapper;
import io.tutorial.spring.Pokedex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.repository.PokedexRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class PokedexService {

	private final PokedexRepository pokedexRepository;
	private final PokemonMapper pokemonMapper;
	private final RestTemplate restTemplate;

	public PokedexService(PokedexRepository pokedexRepository, PokemonMapper pokemonMapper, RestTemplate restTemplate) {
		this.pokedexRepository = pokedexRepository;
		this.pokemonMapper = pokemonMapper;
		this.restTemplate = restTemplate;
	}
	
	public PokemonResponse getPokemons(String username, String name, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Pokemon> pageOfPokemons;
		if (name == null || name.isEmpty())
			pageOfPokemons = pokedexRepository.findByUser_Username(username, pageable);
		else
			pageOfPokemons = pokedexRepository.findByUser_UsernameAndNameContainingIgnoreCase(username, name, pageable);
		List<Pokemon> listOfPokemons = pageOfPokemons.getContent();
		List<PokemonDTO> content = pokemonMapper.toDtoList(listOfPokemons);

		PokemonResponse pokemonResponse = new PokemonResponse();
		pokemonResponse.setContent(content);
		pokemonResponse.setPageNo(pageOfPokemons.getNumber());
		pokemonResponse.setPageSize(pageOfPokemons.getSize());
		pokemonResponse.setTotalElements(pageOfPokemons.getTotalElements());
		pokemonResponse.setTotalPages(pageOfPokemons.getTotalPages());
		pokemonResponse.setLast(pageOfPokemons.isLast());

        return pokemonResponse;
	}

	public void addPokemonFromAPI(String pokemonName, User user) {
		String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonName;

		try {
			ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<Map<String, Object>>() {}
			);

			Map<String, Object> pokemonData = response.getBody();

			assert pokemonData != null;
			Integer pokedexNumber = (Integer) pokemonData.get("id");
			String name = (String) pokemonData.get("name");
			String description = "A wild " + name + " appeared!";

			@SuppressWarnings("unchecked")
			Map<String, Object> sprites = (Map<String, Object>) pokemonData.get("sprites");
			String imageUrl = (String) sprites.get("front_default");

			if (imageUrl == null || imageUrl.isEmpty())
				imageUrl = "https://img.freepik.com/premium-vector/default-image-icon-vector-missing-picture-page-website-design-mobile-app-no-photo-available_87543-11093.jpg";

			PokemonDTO pokemonDTO = new PokemonDTO(pokedexNumber, name, description, imageUrl);
			Pokemon pokemon = pokemonMapper.toPokemon(pokemonDTO);

			pokemon.setUser(user);

			pokedexRepository.save(pokemon);
		} catch (Exception e) {
			throw new RuntimeException("Error retrieving pokemon from API, at : " + url  + " | ", e);
		}

	}

	public Optional<Pokemon> getPokemonByIdAndUser(Integer pokedexNumber, String username) {
		return pokedexRepository.findByPokedexNumberAndUser_Username(pokedexNumber, username);
	}

	public void	addPokemon(final Pokemon pokemon) {
		pokedexRepository.save(pokemon);
	}

	public void updatePokemon(final Pokemon pokemon) {
		if (pokedexRepository.existsById(pokemon.getPokedexNumber())) {
			pokedexRepository.save(pokemon);
		}
	}
	
	public void	removePokemon(final int pokedexNumber, String username) {
		pokedexRepository.deleteById(pokedexNumber);
	}

}
