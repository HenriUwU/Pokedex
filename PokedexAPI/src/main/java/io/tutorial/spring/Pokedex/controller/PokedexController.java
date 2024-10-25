package io.tutorial.spring.Pokedex.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.service.PokedexService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pokemons")
public class PokedexController {

	private  final PokedexService	pokedexService;

	public PokedexController(PokedexService pokedexService) {
		this.pokedexService = pokedexService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Pokemon> getPokemons() {
		return pokedexService.getPokemons();
	}

	@RequestMapping(method = RequestMethod.GET, value ="/{pokedexNumber}")
	public Optional<Pokemon> getPokemon(@PathVariable final int pokedexNumber) {
		return pokedexService.getPokemonById(pokedexNumber);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{pokedexNumber}")
	public void	removePokemon(@PathVariable final int pokedexNumber) {
		pokedexService.removePokemon(pokedexNumber);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void	addPokemon(@RequestBody final Pokemon pokemon) {
		pokedexService.addPokemon(pokemon);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{pokedexNumber}")
	public void	updatePokemon(@RequestBody Pokemon pokemon,@PathVariable int pokedexNumber) {
		pokedexService.updatePokemon(pokemon, pokedexNumber);
	}

}
