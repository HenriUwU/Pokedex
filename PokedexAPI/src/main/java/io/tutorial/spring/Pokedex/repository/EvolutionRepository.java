package io.tutorial.spring.Pokedex.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.tutorial.spring.Pokedex.model.Evolution;

public interface EvolutionRepository extends CrudRepository<Evolution, Integer> {

	List<Evolution> findByUser_UsernameAndDeletedFalse(String username);

}
