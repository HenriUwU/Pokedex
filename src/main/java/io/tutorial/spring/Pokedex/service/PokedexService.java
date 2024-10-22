package io.tutorial.spring.Pokedex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.tutorial.spring.Pokedex.model.Pokemon;

@Service
public class PokedexService {

	private static List<Pokemon> pokemons;

	static {
		pokemons = new ArrayList<>();
		pokemons.add(new Pokemon("Pikachu", "The notorious", List.of(Pokemon.Type.ELECTRIC), 25));
		pokemons.add(new Pokemon("Bulbasaur", "The seed Pokémon", List.of(Pokemon.Type.GRASS, Pokemon.Type.POISON), 1));
	}
	
	public List<Pokemon> getPokemons() {
		return pokemons;
	}

	public Optional<Pokemon> getPokemonById(final int pokedexNumber) {
		return pokemons.stream()
				.filter(pokemon -> pokemon.getPokedexNumber() == pokedexNumber)
				.findFirst();
	}

	public void	addPokemon(final Pokemon pokemon) {
		pokemons.add(pokemon);
	}

	public void	removePokemon(final int pokedexNumber) {
		pokemons.removeIf(pokemon -> pokemon.getPokedexNumber() == pokedexNumber);
	}

	public void	updatePokemon(Pokemon pokemon, final int pokedexNumber) {
		pokemons.forEach(pokemon1 -> {
			if (pokemon1.getPokedexNumber() == pokedexNumber) {
				pokemons.set(pokemons.indexOf(pokemon1), pokemon);
			}
		});
	}

}
