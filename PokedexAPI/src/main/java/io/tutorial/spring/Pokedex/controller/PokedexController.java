package io.tutorial.spring.Pokedex.controller;

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


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pokemons")
public class PokedexController {

	private  final PokedexService	pokedexService;
	private  final UserService		userService;

	public PokedexController(PokedexService pokedexService, UserService userService) {
		this.pokedexService = pokedexService;
		this.userService = userService;
	}

	@GetMapping
	public Iterable<Pokemon> getPokemons(Authentication authentication) {
		String username = authentication.getName();
		return pokedexService.getPokemons(username);
	}

	@PostMapping
	public void	addPokemon(@RequestBody final Pokemon pokemon, Authentication authentication) {
		String username = authentication.getName();
		User user = userService.findByUsername(username)
			.orElseThrow(() -> new RuntimeException("User not found"));
		pokemon.setUser(user);
		pokedexService.addPokemon(pokemon);
	}

	@DeleteMapping("/{pokedexNumber}")
	public void	removePokemon(@PathVariable final int pokedexNumber, Authentication authentication) {
		String username = authentication.getName();
		pokedexService.removePokemon(pokedexNumber, username);
	}

	@PutMapping
	public void	updatePokemon(@RequestBody Pokemon pokemon, Authentication authentication) {
		String username = authentication.getName();
		User user = userService.findByUsername(username)
			.orElseThrow(() -> new RuntimeException("User not found"));
		pokemon.setUser(user);
		pokedexService.updatePokemon(pokemon);
	}

}
