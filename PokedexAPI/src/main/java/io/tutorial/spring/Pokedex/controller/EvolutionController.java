package io.tutorial.spring.Pokedex.controller;

import io.tutorial.spring.Pokedex.dto.EvolutionDTO;
import io.tutorial.spring.Pokedex.mapper.EvolutionMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
	private final EvolutionMapper evolutionMapper;

	public EvolutionController(EvolutionService evolutionService, PokedexRepository pokedexRepository, UserService userService, EvolutionMapper evolutionMapper) {
		this.evolutionService = evolutionService;
		this.pokedexRepository = pokedexRepository;
		this.userService = userService;
		this.evolutionMapper = evolutionMapper;
	}

	@GetMapping
	public Iterable<EvolutionDTO> getEvolutions(Authentication authentication) {
		String username = authentication.getName();
		
		if (!authentication.isAuthenticated()) {
			throw new RuntimeException("User not found");
		}
		Iterable<Evolution> evolutions = evolutionService.getEvolutions(username);
		return evolutionMapper.toDtoList(evolutions);
	}

	@PostMapping
	public void addEvolution(@RequestBody EvolutionDTO evolutionDTO, Authentication authentication, Integer pokemonPokedexNumber) {
		Pokemon pokemon = pokedexRepository.findById(pokemonPokedexNumber)
			.orElseThrow(() -> new RuntimeException("Pokemon not found"));

		User user = userService.findByUsername(authentication.getName())
			.orElseThrow(() -> new RuntimeException("User not found"));

		Evolution evolution = evolutionMapper.toEntity(evolutionDTO);

		evolution.setPokemon(pokemon);
		evolution.setUser(user);

		evolutionService.addEvolution(evolution);
	}

	@PostMapping("/from-api")
	public void addEvolutionFromAPI(@RequestBody String evolutionName, Authentication authentication, Integer pokemonPokedexNumber) {
		Pokemon pokemon = pokedexRepository.findById(pokemonPokedexNumber)
				.orElseThrow(() -> new RuntimeException("Pokemon not found"));

		User user = userService.findByUsername(authentication.getName())
				.orElseThrow(() -> new RuntimeException("User not found"));

		evolutionService.addEvolutionFromAPI(evolutionName, user, pokemon);
	}

	@PutMapping
	public void updateEvolution(@RequestBody EvolutionDTO evolutionDTO, Authentication authentication) {
		User user = userService.findByUsername(authentication.getName())
			.orElseThrow(() -> new RuntimeException("User not found"));

		Evolution evolution = evolutionMapper.toEntity(evolutionDTO);

		evolution.setUser(user);
		evolutionService.addEvolution(evolution);
	}

	@DeleteMapping("/{pokedexNumber}")
	public void deleteEvolution(@PathVariable Integer pokedexNumber) {
		evolutionService.removeEvolution(pokedexNumber);
	}

}
