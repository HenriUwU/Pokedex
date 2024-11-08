package io.tutorial.spring.Pokedex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import io.tutorial.spring.Pokedex.model.Pokemon;

public interface PokedexRepository extends CrudRepository<Pokemon, Integer> {

	Page<Pokemon> findByUser_UsernameAndNameContainingIgnoreCase(String username, String name, Pageable pageable);
	Page<Pokemon> findByUser_Username(String username, Pageable pageable);
	Optional<Pokemon> findByPokedexNumberAndUser_Username(Integer pokedexNumber, String username);

}
