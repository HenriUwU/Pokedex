package io.tutorial.spring.Pokedex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pokemon {
	
	@Id
	private int			pokedexNumber;
	@Column
	private String		name;
	@Column
	private String		description;
	@Column
	private String		type;
	@Column
	private String		imageUrl;

	public Pokemon() {

	}

	public Pokemon(String name, String description, String type, int pokedexNumber) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.pokedexNumber = pokedexNumber;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
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

	public void	setTypes(final String type) {
		this.type = type;
	}

	public void	setPokedexNumber(final int pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
	}

	public void	setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
