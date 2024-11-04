package io.tutorial.spring.Pokedex.controller;

import io.tutorial.spring.Pokedex.model.User;
import io.tutorial.spring.Pokedex.repository.UserRepository;
import io.tutorial.spring.Pokedex.security.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_shouldRegisterUserSuccessfully() {
        // Arrange
        User user = new User("username", "plainPassword");
        String encodedPassword = passwordEncoder.encode("encodedPassword");

        when(passwordEncoder.encode("plainPassword")).thenReturn(encodedPassword);

        // Act
        ResponseEntity<Map<String, String>> response = authController.register(user);

        assertEquals("User registered successfully", Objects.requireNonNull(response.getBody()).get("message"));
        assertEquals(200, response.getStatusCode().value());
        assertEquals(encodedPassword, user.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    void login_shouldReturnTokenWhenUserExists() {
        // Arrange
        User user = new User("username", "plainPassword");
        User dbUser = new User("username", "encodedPassword");
        String token = "generatedJwtToken";

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(dbUser));
        when(passwordEncoder.matches("plainPassword", "encodedPassword")).thenReturn(true);
        when(jwtUtil.generateToken("username")).thenReturn(token);

        // Act
        ResponseEntity<Map<String, String>> response = authController.login(user);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(token, Objects.requireNonNull(response.getBody()).get("token"));

    }

    @Test
    void login_shouldThrowExceptionWhenUserDoesNotExist() {
        // Arrange
        User user = new User("username", "plainPassword");

        when(userRepository.findByUsername("username")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> authController.login(user));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void login_shouldThrowExceptionWhenPasswordDoesNotMatch() {
        // Arrange
        User user = new User("username", "encodedPassword");
        User dbUser = new User("username", "plainPassword");

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(dbUser));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> authController.login(user));
        assertEquals("Invalid credentials", exception.getMessage());
    }
}