package io.tutorial.spring.Pokedex.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.tutorial.spring.Pokedex.model.User;
import io.tutorial.spring.Pokedex.repository.UserRepository;
import io.tutorial.spring.Pokedex.security.util.JwtUtil;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);

		Map<String, String> response = new HashMap<>();
		response.put("message", "User registered successfully");
		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
		User dbUser = userRepository.findByUsername(user.getUsername())
			.orElseThrow(() -> new RuntimeException("User not found"));
		
			if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
				String token = jwtUtil.generateToken(user.getUsername());
				Map<String, String> response = new HashMap<>();
				response.put("token", token);
				return ResponseEntity.ok(response);
			} else {
				throw new RuntimeException("Invalid credentials");
			}
	}
}
