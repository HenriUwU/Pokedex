package io.tutorial.spring.Pokedex.repository;

import io.tutorial.spring.Pokedex.model.Evolution;
import io.tutorial.spring.Pokedex.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class EvolutionRepositoryTest {

    @Mock
    private EvolutionRepository evolutionRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldReturnAllActiveEvolutionsOfUser () {
        // Arrange
        User user = new User("username", "password");
        userRepository.save(user);

        Evolution evolution1 = new Evolution("Raichu", "The forgotten", 26, false);
        Evolution evolution2 = new Evolution("Carabaffe", "The false Tortank", 8, true);
        Evolution evolution3 = new Evolution("Tortank", "Tibo Inshape", 9, false);

        evolution1.setUser(user);
        evolution2.setUser(user);
        evolution3.setUser(user);

        List<Evolution> mockEvolutions = List.of(evolution1, evolution3);
        when(evolutionRepository.findByUser_UsernameAndDeletedFalse(user.getUsername())).thenReturn(mockEvolutions);

        // Act
        List<Evolution> evolutions = evolutionRepository.findByUser_UsernameAndDeletedFalse(user.getUsername());

        // Assert
        assertEquals(2, evolutions.size());
        assertTrue(evolutions.contains(evolution1));
        assertTrue(evolutions.contains(evolution3));
        assertFalse(evolutions.contains(evolution2));

    }

}