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
	@Column
	private boolean		deleted;

	public Pokemon() {

	}

	public Pokemon(String name, String description, Integer pokedexNumber, boolean deleted) {
		super();
		this.name = name;
		this.description = description;
		this.pokedexNumber = pokedexNumber;
		this.deleted = deleted;
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

	public boolean getDeleted() {
		return this.deleted;
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

	public void	setDeleted(final boolean deleted) {
		this.deleted = deleted;
	}
}
