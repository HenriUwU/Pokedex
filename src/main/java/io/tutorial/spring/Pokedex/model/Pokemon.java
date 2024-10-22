package io.tutorial.spring.Pokedex.model;

import java.util.List;

public class Pokemon {
	
	public enum Type {
		NONE,
		NORMAL,
		FIRE,
		WATER,
		GRASS,
		FLYING,
		FIGHTING,
		POISON,
		ELECTRIC,
		GROUND,
		ROCK,
		PSYCHIC,
		ICE,
		BUG,
		GHOST,
		STEEL,
		DRAGON,
		DARK,
		FAIRY
	}
	
	private int			pokedexNumber;
	private String		name;
	private String		description;
	private List<Type>	types;

	public Pokemon(String name, String description, List<Type> types, int pokedexNumber) {
		this.name = name;
		this.description = description;
		this.types = types;
		this.pokedexNumber = pokedexNumber;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Type> getType() {
		return types;
	}

	public int getPokedexNumber() {
		return pokedexNumber;
	}

	public void	setName(final String name) {
		this.name = name;
	}

	public void	setDescription(final String description) {
		this.description = description;
	}

	public void	setTypes(final Type firstType, final Type secondType) {
		this.types.add(firstType);
		this.types.add(secondType);
	}

}
