package io.tutorial.spring.Pokedex.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.repository.PokedexRepository;

@Service
public class PokedexService {

	private PokedexRepository pokedexRepository;

	public PokedexService(PokedexRepository pokedexRepository) {
		this.pokedexRepository = pokedexRepository;
	}
	
	public Iterable<Pokemon> getPokemons(String username) {
		return pokedexRepository.findByUser_UsernameAndDeletedFalse(username);
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
		Optional<Pokemon> optionalPokemon = getPokemonByIdAndUser(pokedexNumber, username);
		if (optionalPokemon.isPresent()) {
			Pokemon pokemon = optionalPokemon.get();
			pokemon.setDeleted(true);
			updatePokemon(pokemon);
		}
	}

}
