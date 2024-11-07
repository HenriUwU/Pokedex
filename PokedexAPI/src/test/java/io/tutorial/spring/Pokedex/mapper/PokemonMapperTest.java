package io.tutorial.spring.Pokedex.mapper;

import io.tutorial.spring.Pokedex.dto.PokemonDTO;
import io.tutorial.spring.Pokedex.model.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonMapperTest {

    private final PokemonMapper pokemonMapper = new PokemonMapperImpl();

    @Test
    void toDto() {
        // Arrange
        Pokemon pokemon = new Pokemon();
        pokemon.setPokedexNumber(25);
        pokemon.setName("Pikachu");
        pokemon.setDescription("Electric mouse");
        pokemon.setImageUrl("pikachu.png");

        // Act
        PokemonDTO pokemonDTO = pokemonMapper.toDto(pokemon);

        // Assert
        assertNotNull(pokemonDTO);
        assertEquals(pokemon.getPokedexNumber(), pokemonDTO.getPokedexNumber());
        assertEquals(pokemon.getName(), pokemonDTO.getName());
        assertEquals(pokemon.getDescription(), pokemonDTO.getDescription());
        assertEquals(pokemon.getImageUrl(), pokemonDTO.getImageUrl());
    }

    @Test
    void toPokemon() {
        // Arrange
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setPokedexNumber(6);
        pokemonDTO.setName("Charizard");
        pokemonDTO.setDescription("Fire-breathing dragon");
        pokemonDTO.setImageUrl("charizard.png");

        // Act
        Pokemon pokemon = pokemonMapper.toPokemon(pokemonDTO);

        // Assert
        assertNotNull(pokemon);
        assertEquals(pokemonDTO.getPokedexNumber(), pokemon.getPokedexNumber());
        assertEquals(pokemonDTO.getName(), pokemon.getName());
        assertEquals(pokemonDTO.getDescription(), pokemon.getDescription());
        assertEquals(pokemonDTO.getImageUrl(), pokemon.getImageUrl());
    }

    @Test
    void toDtoList() {
        // Arrange
        List<Pokemon> pokemons = List.of(
                new Pokemon("Pikachu", "Electric mouse", 25, false),
                new Pokemon("Charizard", "Fire-breathing dragon", 6, false)
        );

        // Act
        List<PokemonDTO> pokemonDTOs = pokemonMapper.toDtoList(pokemons);

        // Assert
        assertNotNull(pokemonDTOs);
        assertEquals(pokemons.size(), pokemonDTOs.size());

        for (int i = 0; i < pokemons.size(); i++) {
            assertEquals(pokemons.get(i).getPokedexNumber(), pokemonDTOs.get(i).getPokedexNumber());
            assertEquals(pokemons.get(i).getName(), pokemonDTOs.get(i).getName());
            assertEquals(pokemons.get(i).getDescription(), pokemonDTOs.get(i).getDescription());
            assertEquals(pokemons.get(i).getImageUrl(), pokemonDTOs.get(i).getImageUrl());
        }
    }
}