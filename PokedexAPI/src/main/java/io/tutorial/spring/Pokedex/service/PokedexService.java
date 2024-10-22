package io.tutorial.spring.Pokedex.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.repository.PokedexRepository;

@Service
public class PokedexService {

	@Autowired
	private PokedexRepository pokedexRepository;
	
	public Iterable<Pokemon> getPokemons() {
		return pokedexRepository.findAll();
	}

	public Optional<Pokemon> getPokemonById(final int pokedexNumber) {
		return pokedexRepository.findById(pokedexNumber);
	}

	public void	addPokemon(final Pokemon pokemon) {
		pokedexRepository.save(pokemon);
	}

	public void	removePokemon(final int pokedexNumber) {
		pokedexRepository.deleteById(pokedexNumber);
	}

	public Pokemon updatePokemon(Pokemon pokemon, final int pokedexNumber) {
		if (pokedexRepository.existsById(pokedexNumber)) {
			pokemon.setPokedexNumber(pokedexNumber);
			return pokedexRepository.save(pokemon);
		}
		return null; // or throw exception
	}

}
