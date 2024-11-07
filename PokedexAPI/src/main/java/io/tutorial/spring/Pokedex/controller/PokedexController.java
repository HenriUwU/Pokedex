package io.tutorial.spring.Pokedex.controller;

import io.tutorial.spring.Pokedex.dto.PokemonDTO;
import io.tutorial.spring.Pokedex.mapper.PokemonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.model.User;
import io.tutorial.spring.Pokedex.service.PokedexService;
import io.tutorial.spring.Pokedex.service.UserService;

import java.util.ArrayList;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pokemons")
public class PokedexController {

	private  final PokedexService	pokedexService;
	private  final UserService		userService;
	private final PokemonMapper pokemonMapper;

	public PokedexController(PokedexService pokedexService, UserService userService, PokemonMapper pokemonMapper) {
		this.pokedexService = pokedexService;
		this.userService = userService;
		this.pokemonMapper = pokemonMapper;
	}

	@GetMapping
	public Iterable<PokemonDTO> getPokemons(Authentication authentication) {
		String username = authentication.getName();
		if (!authentication.isAuthenticated()) {
			throw new RuntimeException("User not authenticated");
		}
		Iterable<Pokemon> pokemons = pokedexService.getPokemons(username);
		return pokemonMapper.toDtoList(pokemons);
	}

	@PostMapping
	public void	addPokemon(@RequestBody final PokemonDTO pokemonDTO, Authentication authentication) {
		String username = authentication.getName();
		User user = userService.findByUsername(username)
			.orElseThrow(() -> new RuntimeException("User not authenticated"));
		Pokemon pokemon = pokemonMapper.toPokemon(pokemonDTO);
		pokemon.setUser(user);
		pokedexService.addPokemon(pokemon);
	}

	@DeleteMapping("/{pokedexNumber}")
	public void	removePokemon(@PathVariable final int pokedexNumber, Authentication authentication) {
		String username = authentication.getName();
		pokedexService.removePokemon(pokedexNumber, username);
	}

	@PutMapping
	public void	updatePokemon(@RequestBody PokemonDTO pokemonDTO, Authentication authentication) {
		String username = authentication.getName();
		User user = userService.findByUsername(username)
			.orElseThrow(() -> new RuntimeException("User not authenticated"));
		Pokemon pokemon = pokemonMapper.toPokemon(pokemonDTO);
		pokemon.setUser(user);
		pokedexService.updatePokemon(pokemon);
	}

}
