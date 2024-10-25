package io.tutorial.spring.Pokedex.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.tutorial.spring.Pokedex.model.Pokemon;

public interface PokedexRepository extends CrudRepository<Pokemon, Integer> {

	@Query("SELECT p FROM Pokemon p WHERE p.deleted = false")
	Iterable<Pokemon> findAllActive();

}
