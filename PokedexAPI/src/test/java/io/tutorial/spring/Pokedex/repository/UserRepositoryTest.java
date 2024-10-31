package io.tutorial.spring.Pokedex.repository;

import io.tutorial.spring.Pokedex.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldReturnUserByUsername() {
        // Arrange
        User user1 = new User("username", "password");
        User user2 = new User("john", "password");
        userRepository.save(user1);
        userRepository.save(user2);

        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user1));

        // Act
        Optional<User> optionalUser = userRepository.findByUsername("john");

        // Assert
        assertTrue(optionalUser.isPresent());
        assertEquals(user1, optionalUser.get());

    }

}