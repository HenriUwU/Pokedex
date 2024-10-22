package io.tutorial.spring.Pokedex.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.service.PokedexService;

@RestController
public class PokedexController {
	
	@Autowired
	PokedexService	pokedexService;

	@RequestMapping("/pokemons")
	public Iterable<Pokemon> getPokemons() {
		return pokedexService.getPokemons();
	}

	@RequestMapping("/pokemon/{pokedexNumber}")
	public Optional<Pokemon> getPokemon(@PathVariable final int pokedexNumber) {
		return pokedexService.getPokemonById(pokedexNumber);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/removePokemon/{pokedexNumber}")
	public void	removePokemon(@PathVariable final int pokedexNumber) {
		pokedexService.removePokemon(pokedexNumber);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addPokemon")
	public void	addPokemon(@RequestBody final Pokemon pokemon) {
		pokedexService.addPokemon(pokemon);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updatePokemon/{pokedexNumber}")
	public void	updatePokemon(@RequestBody Pokemon pokemon,@PathVariable int pokedexNumber) {
		pokedexService.updatePokemon(pokemon, pokedexNumber);
	}

}
