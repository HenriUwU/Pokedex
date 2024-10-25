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
	
	public Iterable<Pokemon> getPokemons() {
		return pokedexRepository.findAllActive();
	}

	public Optional<Pokemon> getPokemonById(final int pokedexNumber) {
		return pokedexRepository.findById(pokedexNumber);
	}

	public void	addPokemon(final Pokemon pokemon) {
		pokedexRepository.save(pokemon);
	}

	public Pokemon updatePokemon(final Pokemon pokemon) {
		if (pokedexRepository.existsById(pokemon.getPokedexNumber())) {
			return pokedexRepository.save(pokemon);
		}
		return null;
	}
	
	public void	removePokemon(final int pokedexNumber) {
		Optional<Pokemon> optionalPokemon = getPokemonById(pokedexNumber);
		if (optionalPokemon.isPresent()) {
			Pokemon pokemon = optionalPokemon.get();
			pokemon.setDeleted(true);
			updatePokemon(pokemon);
		}
	}

}
