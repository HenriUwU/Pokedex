package io.tutorial.spring.Pokedex.service;

import java.util.List;
import java.util.Optional;

import io.tutorial.spring.Pokedex.dto.PokemonDTO;
import io.tutorial.spring.Pokedex.dto.PokemonResponse;
import io.tutorial.spring.Pokedex.mapper.PokemonMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.repository.PokedexRepository;

@Service
public class PokedexService {

	private final PokedexRepository pokedexRepository;
	private final PokemonMapper pokemonMapper;

	public PokedexService(PokedexRepository pokedexRepository, PokemonMapper pokemonMapper) {
		this.pokedexRepository = pokedexRepository;
		this.pokemonMapper = pokemonMapper;
	}
	
	public PokemonResponse getPokemons(String username, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Pokemon> pageOfPokemons = pokedexRepository.findByUser_Username(username, pageable);
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

	public PokemonResponse getPokemonsByName(String username, String name, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Pokemon> pageOfPokemons = pokedexRepository.findByUser_UsernameAndNameContainingIgnoreCase(username, name, pageable);
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
