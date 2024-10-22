package io.tutorial.spring.Pokedex.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.tutorial.spring.Pokedex.model.Pokemon;


@RestController
public class PokedexController {
	
	@RequestMapping("/pokemon")
	public Pokemon getPokemon() {
		List<Pokemon.Type>	types = Arrays.asList(Pokemon.Type.ELECTRIC);
		Pokemon				newPokemon = new Pokemon("Pikachu", "The notorious", types, 25);

		return newPokemon;
	}
}
