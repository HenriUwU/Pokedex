package io.tutorial.spring.Pokedex.controller;

import io.tutorial.spring.Pokedex.dto.PokemonDTO;
import io.tutorial.spring.Pokedex.dto.PokemonResponse;
import io.tutorial.spring.Pokedex.mapper.PokemonMapper;
import io.tutorial.spring.Pokedex.repository.PokedexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.model.User;
import io.tutorial.spring.Pokedex.service.PokedexService;
import io.tutorial.spring.Pokedex.service.UserService;

import java.util.ArrayList;
import java.util.List;


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
	public ResponseEntity<PokemonResponse> getPokemons(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "1", required = false) int pageSize,
			Authentication authentication) {
		String username = authentication.getName();
		if (!authentication.isAuthenticated()) {
			throw new RuntimeException("User not authenticated");
		}

		return new ResponseEntity<>(pokedexService.getPokemons(username, pageNo, pageSize), HttpStatus.OK);
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
