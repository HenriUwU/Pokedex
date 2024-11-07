package io.tutorial.spring.Pokedex.controller;

import io.tutorial.spring.Pokedex.model.Evolution;
import io.tutorial.spring.Pokedex.service.EvolutionService;
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

class EvolutionControllerTest {

    @Mock
    private EvolutionService evolutionService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private EvolutionController evolutionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEvolutions_shouldThrowExceptionWhenUserIsNotAuthenticated() {
        // Arrange
        String username = "notAuthenticatedUser";

        when(authentication.isAuthenticated()).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> evolutionController.getEvolutions(authentication));
        assertEquals("User not found", exception.getMessage());

    }

}