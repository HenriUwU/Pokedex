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
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PokedexControllerTest {

    @Mock
    private PokedexService pokedexService;

    @Mock
    private Authentication authentication;

    @Mock
    private UserService userService;

    @InjectMocks
    private PokedexController pokedexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void getPokemons_shouldReturnPokemons() {
//        // Arrange
//        String username = "authenticatedUser";
//        Iterable<Pokemon> pokemons = List.of(
//                new Pokemon("Pikachu", "The notorious", 25, false),
//                new Pokemon("Dracaufeu", "The billionaire", 6, false)
//        );
//
//        when(authentication.isAuthenticated()).thenReturn(true);
//        when(authentication.getName()).thenReturn(username);
//        when(pokedexService.getPokemons(username)).thenReturn(pokemons);
//
//        // Act
//        Iterable<PokemonDTO> resultDTO = pokedexController.getPokemons(authentication);
//
//        // Assert
//        assertEquals(pokemons, result);
//        verify(pokedexService).getPokemons(username);
//
//    }
//
//    @Test
//    void getPokemons_shouldThrowExceptionWhenUserNotAuthenticated() {
//        // Arrange
//        String username = "notAuthenticatedUser";
//
//        when(authentication.isAuthenticated()).thenReturn(false);
//        when(authentication.getName()).thenReturn("wrongUsername");
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> pokedexController.getPokemons(authentication));
//        assertEquals("User not authenticated", exception.getMessage());
//    }
//
//    @Test
//    void addPokemon_shouldThrowExceptionWhenUserNotAuthenticated() {
//        // Arrange
//        Pokemon pokemon = new Pokemon("Pikachu", "The notorious", 25, false);
//        String username = "notAuthenticatedUser";
//
//        when(authentication.isAuthenticated()).thenReturn(false);
//        when(userService.findByUsername(username)).thenReturn(null);
//
////      Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> pokedexController.addPokemon(pokemon, authentication));
//        assertEquals("User not authenticated", exception.getMessage());
//
//    }

}