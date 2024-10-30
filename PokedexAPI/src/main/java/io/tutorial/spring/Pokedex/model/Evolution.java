package io.tutorial.spring.Pokedex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Evolution {
	
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

	@ManyToOne
	@JoinColumn(name="pokemon_pokedex_number", nullable = false)
	private Pokemon pokemon;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Evolution() {

	}

	public Evolution(String name, String description, Integer pokedexNumber, boolean deleted) {
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

	public Pokemon getPokemon() {
		return pokemon;
	}

	public User getUser() {
		return user;
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
	
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public void setUser(User user) {
		this.user = user;
	}

}