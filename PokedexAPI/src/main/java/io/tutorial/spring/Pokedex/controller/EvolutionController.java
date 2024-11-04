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

import io.tutorial.spring.Pokedex.model.Evolution;
import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.model.User;
import io.tutorial.spring.Pokedex.repository.PokedexRepository;
import io.tutorial.spring.Pokedex.service.EvolutionService;
import io.tutorial.spring.Pokedex.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/evolutions")
public class EvolutionController {

	private final EvolutionService evolutionService;
	private final PokedexRepository pokedexRepository;
	private final UserService userService;

	public EvolutionController(EvolutionService evolutionService, PokedexRepository pokedexRepository, UserService userService) {
		this.evolutionService = evolutionService;
		this.pokedexRepository = pokedexRepository;
		this.userService = userService;
	}

	@GetMapping
	public Iterable<Evolution> getEvolutions(Authentication authentication) {
		String username = authentication.getName();
		
		if (!authentication.isAuthenticated()) {
			throw new RuntimeException("User not found");
		}
		return evolutionService.getEvolutions(username);
	}

	@PostMapping
	public void addEvolution(@RequestBody Evolution evolution, Authentication authentication, Integer pokemonPokedexNumber) {
		Pokemon pokemon = pokedexRepository.findById(pokemonPokedexNumber)
			.orElseThrow(() -> new RuntimeException("Pokemon not found"));

		User user = userService.findByUsername(authentication.getName())
			.orElseThrow(() -> new RuntimeException("User not found"));

		evolution.setPokemon(pokemon);
		evolution.setUser(user);

		evolutionService.addEvolution(evolution);
	}

	@PutMapping
	public void updateEvolution(@RequestBody Evolution evolution, Authentication authentication) {
		User user = userService.findByUsername(authentication.getName())
			.orElseThrow(() -> new RuntimeException("User not found"));

		evolution.setUser(user);
		evolutionService.addEvolution(evolution);
	}

	@DeleteMapping("/{pokedexNumber}")
	public void deleteEvolution(@PathVariable Integer pokedexNumber) {
		evolutionService.removeEvolution(pokedexNumber);
	}

}
