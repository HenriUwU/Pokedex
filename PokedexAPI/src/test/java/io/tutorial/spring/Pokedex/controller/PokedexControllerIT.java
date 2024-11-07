package io.tutorial.spring.Pokedex.controller;

import com.google.gson.Gson;
import io.tutorial.spring.Pokedex.dto.PokemonResponse;
import io.tutorial.spring.Pokedex.model.Pokemon;
import io.tutorial.spring.Pokedex.model.User;
import io.tutorial.spring.Pokedex.repository.PokedexRepository;
import io.tutorial.spring.Pokedex.repository.UserRepository;
import io.tutorial.spring.Pokedex.service.PokedexService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PokedexControllerIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PokedexRepository pokedexRepository;

    private User testUser;
    @Autowired
    private PokedexService pokedexService;

    @BeforeEach
    void setUp() {
        testUser = new User("John", passwordEncoder.encode("rawPassword"));
        userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        pokedexRepository.deleteAll();
    }

    @Test
    void getPokemons_shouldGetAllPokemonsForAuthenticatedUser() throws Exception {
        // Arrange
        Pokemon pokemon = new Pokemon("Pikachu", "Tho notorious", 25, false);
        pokemon.setUser(testUser);
        pokedexRepository.save(pokemon);

        // Act & Assert
        mockMvc.perform(get("/pokemons")
                .with(user("John").password("rawPassword").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pikachu"))
                .andExpect(jsonPath("$[0].pokedexNumber").value(25));

    }

//    @Test
//    void addPokemon_shouldAddPokemon() throws Exception {
//        // Arrange
//        Gson gson = new Gson();
//        Pokemon pokemon = new Pokemon("Pikachu", "Tho notorious", 25, false);
//        pokemon.setUser(testUser);
//
//        // Act
//        mockMvc.perform(post("/pokemons")
//                .with(user("John").password("rawPassword").roles("USER"))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(gson.toJson(pokemon)))
//                .andExpect(status().isOk());
//
//        // Assert
//        PokemonResponse userPokemons = pokedexService.getPokemons(testUser.getUsername(), 0, 1);
//        List<Pokemon> finalResult  = StreamSupport.stream(userPokemons.spliterator(), false).toList();
//
//        assertEquals(1, finalResult.size());
//        assertEquals(pokemon.getPokedexNumber(), finalResult.getFirst().getPokedexNumber());
//        assertEquals(pokemon.getName(), finalResult.getFirst().getName());
//
//    }
}