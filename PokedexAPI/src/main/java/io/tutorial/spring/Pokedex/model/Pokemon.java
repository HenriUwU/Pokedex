package io.tutorial.spring.Pokedex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pokemon {
	
	@Id
	private Integer		pokedexNumber;
	@Column
	private String		name;
	@Column
	private String		description;
	@Column
	private String		imageUrl;

	public Pokemon() {

	}

	public Pokemon(String name, String description, Integer pokedexNumber) {
		super();
		this.name = name;
		this.description = description;
		this.pokedexNumber = pokedexNumber;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getPokedexNumber() {
		return pokedexNumber;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void	setName(final String name) {
		this.name = name;
	}

	public void	setDescription(final String description) {
		this.description = description;
	}

	public void	setPokedexNumber(final int pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
	}

	public void	setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
