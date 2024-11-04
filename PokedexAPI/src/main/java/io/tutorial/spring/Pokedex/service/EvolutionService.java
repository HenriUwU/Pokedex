package io.tutorial.spring.Pokedex.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.tutorial.spring.Pokedex.model.Evolution;
import io.tutorial.spring.Pokedex.repository.EvolutionRepository;

@Service
public class EvolutionService {

	private final EvolutionRepository evolutionRepository;

	public EvolutionService(EvolutionRepository evolutionRepository) {
		this.evolutionRepository = evolutionRepository;
	}

	public Iterable<Evolution> getEvolutions(String username) {
		return evolutionRepository.findByUser_UsernameAndDeletedFalse(username);
	}

	public void	addEvolution(final Evolution evolution) {
		evolutionRepository.save(evolution);
	}

	public void updateEvolution(final Evolution evolution) {
		if (evolutionRepository.existsById(evolution.getPokedexNumber())) {
			evolutionRepository.save(evolution);
		}
	}

	public void	removeEvolution(final int pokedexNumber) {
		Optional<Evolution> optionalEvolution = evolutionRepository.findById(pokedexNumber);
		if (optionalEvolution.isPresent()) {
			Evolution evolution = optionalEvolution.get();
			evolution.setDeleted(true);
			updateEvolution(evolution);
		}
	}

}
