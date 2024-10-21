package io.tutorial.spring.Pokedex.model;

public class Pokemon {

	enum Type {
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
	
	private String		name;
	private String		description;
	private int[]		types;
	private int			pokedexNumber;

	public Pokemon(String name, String description, int[] types, int pokedexNumber) {
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

	public int[] getType() {
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

	public void	setTypes(final int firstType, final int secondType) {
		this.types[0] = firstType;
		this.types[1] = secondType;
	}

}
