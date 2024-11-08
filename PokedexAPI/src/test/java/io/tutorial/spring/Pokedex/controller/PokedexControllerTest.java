package io.tutorial.spring.Pokedex.controller;

import io.tutorial.spring.Pokedex.dto.PokemonDTO;
import io.tutorial.spring.Pokedex.mapper.PokemonMapper;
import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.service.PokedexService;
import io.tutorial.spring.Pokedex.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PokedexControllerTest {

    @Mock
    private PokedexService pokedexService;

    @Mock
    private Authentication authentication;

    @Mock
    private UserService userService;

    @Mock
    private PokemonMapper pokemonMapper;

    @InjectMocks
    private PokedexController pokedexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPokemons_shouldThrowExceptionWhenUserNotAuthenticated() {
        // Arrange
        String username = "notAuthenticatedUser";

        when(authentication.isAuthenticated()).thenReturn(false);
        when(authentication.getName()).thenReturn("wrongUsername");

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> pokedexController.getPokemons(1, 1, "pikachu",authentication));
        assertEquals("User not authenticated", exception.getMessage());
    }

    @Test
    void addPokemon_shouldThrowExceptionWhenUserNotAuthenticated() {
        // Arrange
        PokemonDTO pokemonDTO = new PokemonDTO(25, "Pikachu", "The Notorious", "");
        String username = "notAuthenticatedUser";

        when(authentication.isAuthenticated()).thenReturn(false);
        when(userService.findByUsername(username)).thenReturn(null);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> pokedexController.addPokemon(pokemonDTO, authentication));
        assertEquals("User not authenticated", exception.getMessage());

    }

}