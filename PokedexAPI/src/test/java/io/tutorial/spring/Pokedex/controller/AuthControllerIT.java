package io.tutorial.spring.Pokedex.controller;

import com.jayway.jsonpath.JsonPath;
import io.tutorial.spring.Pokedex.model.User;
import io.tutorial.spring.Pokedex.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void register_shouldRegisterUser() throws Exception {
        // Arrange
        String newUserJson = """
            {
                "username": "John",
                "password": "password"
            }
         """;

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("User registered successfully"));

        Optional<User> user = userRepository.findByUsername("John");
        assertTrue(user.isPresent());
        assertTrue(passwordEncoder.matches("password", user.get().getPassword()));
    }

    @Test
    void login_shouldReturnJwt() throws Exception {
        // Arrange
        User user = new User("John", passwordEncoder.encode("password"));
        userRepository.save(user);

        String loginUserJson = """
                {
                    "username": "John",
                    "password": "password"
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());

    }
}