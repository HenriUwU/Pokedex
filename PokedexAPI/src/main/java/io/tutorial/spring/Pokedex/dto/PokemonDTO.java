package io.tutorial.spring.Pokedex.dto;

public class PokemonDTO {

    private Integer pokedexNumber;
    private String name;
    private String description;
    private String imageUrl;

    public PokemonDTO() { }

    public PokemonDTO(Integer pokedexNumber, String name, String description, String imageUrl) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Integer getPokedexNumber() { return pokedexNumber; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }

    public void setPokedexNumber(Integer pokedexNumber) { this.pokedexNumber = pokedexNumber; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }


}
