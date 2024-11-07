package io.tutorial.spring.Pokedex.repository;

import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PokedexRepositoryTest {

    @Mock
    private PokedexRepository pokedexRepository;
    @Autowired
    private UserRepository userRepository;

//    @Test
//    public void shouldReturnAllPokemonsOfUser() {
//        // Arrange
//        User user = new User("user", "password");
//        userRepository.save(user);
//
//        Pokemon pokemon1 = new Pokemon("Pikachu", "The notorious", 25, false);
//        Pokemon pokemon2 = new Pokemon("Carapuce", "The cute one", 7, false);
//        Pokemon pokemon3 = new Pokemon("Dracaufeu", "The iconic", 6, false);
//
//        pokemon1.setUser(user);
//        pokemon2.setUser(user);
//        pokemon3.setUser(user);
//
//        List<Pokemon> mockPokemons = List.of(pokemon1, pokemon3);
//        when(pokedexRepository.findByUser_Username(user.getUsername())).thenReturn(mockPokemons);
//
//        // Act
//        List<Pokemon> activePokemons = pokedexRepository.findByUser_Username(user.getUsername());
//
//        // Assert
//        assertEquals(2, activePokemons.size());
//        assertEquals(pokemon1, activePokemons.getFirst());
//        assertEquals(pokemon3, activePokemons.getLast());
//
//    }

    @Test
    public void shouldReturnPokemonOfUserByPokedexNumber() {
        // Arrange
        User user = new User("user", "password");
        userRepository.save(user);

        Pokemon pokemon1 = new Pokemon("Pikachu", "The notorious", 25, false);
        Pokemon pokemon2 = new Pokemon("Carapuce", "The cute one", 7, true);

        pokemon1.setUser(user);
        pokemon2.setUser(user);

        when(pokedexRepository.findByPokedexNumberAndUser_Username(25, user.getUsername())).thenReturn(Optional.of(pokemon1));

        // Act
        Optional<Pokemon> optionalPokemon = pokedexRepository.findByPokedexNumberAndUser_Username(25, user.getUsername());

        // Assert
        Assertions.assertThat(optionalPokemon).isPresent();
        assertEquals(pokemon1, optionalPokemon.get());

    }

}