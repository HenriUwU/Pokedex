package io.tutorial.spring.Pokedex.repository;

import java.util.List;
import java.util.Optional; 

import org.springframework.data.repository.CrudRepository;

import io.tutorial.spring.Pokedex.model.Pokemon;

public interface PokedexRepository extends CrudRepository<Pokemon, Integer> {

	List<Pokemon> findByUser_UsernameAndDeletedFalse(String username);

	Optional<Pokemon> findByPokedexNumberAndUser_Username(Integer pokedexNumber, String username);

}
