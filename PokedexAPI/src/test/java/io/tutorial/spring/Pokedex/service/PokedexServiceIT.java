package io.tutorial.spring.Pokedex.service;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.model.User;
import io.tutorial.spring.Pokedex.repository.PokedexRepository;
import io.tutorial.spring.Pokedex.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class PokedexServiceIT {

    private final PokedexService pokedexService;
    private final UserRepository userRepository;
    private final PokedexRepository pokedexRepository;

    @Autowired
    public PokedexServiceIT(PokedexService pokedexService, UserRepository userRepository, PokedexRepository pokedexRepository) {
        this.pokedexService = pokedexService;
        this.userRepository = userRepository;
        this.pokedexRepository = pokedexRepository;
    }

    @Test
    void getPokemons_shouldReturnAllPokemons() {
        // Arrange
        User user = new User("username", "password");
        userRepository.save(user);

        Pokemon pokemon1 = new Pokemon("Pikachu", "The notorious", 25, false);
        Pokemon pokemon2 = new Pokemon("Dracaufeu", "fire starter", 6, false);
        pokemon1.setUser(user);
        pokemon2.setUser(user);
        pokedexRepository.save(pokemon1);
        pokedexRepository.save(pokemon2);

        // Act
        Iterable<Pokemon> result = pokedexService.getPokemons(user.getUsername());

        // Assert
        List<Pokemon> finalResult  = StreamSupport.stream(result.spliterator(), false).toList();
        assertEquals(2, finalResult.size());
        assertEquals(pokemon2.getPokedexNumber(), finalResult.getFirst().getPokedexNumber());
        assertEquals(pokemon1.getPokedexNumber(), finalResult.getLast().getPokedexNumber());

    }

    @Test
    void getPokemonByIdAndUser_shouldReturnPokemon() {
        // Arrange
        User user = new User("username", "password");
        userRepository.save(user);

        Pokemon pokemon = new Pokemon("Pikachu", "The notorious", 25, false);
        pokemon.setUser(user);
        pokedexRepository.save(pokemon);

        // Act
        Optional<Pokemon> result = pokedexService.getPokemonByIdAndUser(pokemon.getPokedexNumber(), user.getUsername());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(pokemon.getPokedexNumber(), result.get().getPokedexNumber());
        assertEquals(pokemon.getUser().getUsername(), result.get().getUser().getUsername());

    }
}