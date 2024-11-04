package io.tutorial.spring.Pokedex.service;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.repository.PokedexRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PokedexServiceTest {

    @Mock
    PokedexRepository pokedexRepository;

    @InjectMocks
    PokedexService pokedexService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPokemons_shouldReturnAllActivePokemonsOfUser() {
        // Arrange
        String username = "username";
        Pokemon pokemon1 = new Pokemon("Pikachu", "The notorious", 25, false);
        Pokemon pokemon2 = new Pokemon("Carapuce", "The cute one", 7, true);
        Pokemon pokemon3 = new Pokemon("Dracaufeu", "The iconic", 6, false);

        List<Pokemon> expectedPokemons = List.of(pokemon1, pokemon2, pokemon3);

        when(pokedexRepository.findByUser_UsernameAndDeletedFalse(username)).thenReturn(expectedPokemons);

        // Act
        Iterable<Pokemon> result = pokedexService.getPokemons(username);

        // Assert
        assertEquals(expectedPokemons, result);

    }

    @Test
    void getPokemonByIdAndUser() {
        // Arrange
        String username = "username";
        Pokemon pokemon = new Pokemon("Pikachu", "The notorious", 25, false);

        when(pokedexRepository.findByPokedexNumberAndUser_Username(pokemon.getPokedexNumber(), username)).thenReturn(Optional.of(pokemon));

        // Act
        Optional<Pokemon> result = pokedexService.getPokemonByIdAndUser(pokemon.getPokedexNumber(), username);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(pokemon, result.get());

    }
}