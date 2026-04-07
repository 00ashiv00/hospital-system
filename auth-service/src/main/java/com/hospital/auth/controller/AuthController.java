package com.hospital.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.auth.dto.LoginRequest;
import com.hospital.auth.dto.LoginResponse;
import com.hospital.auth.dto.UserMeResponse;
import com.hospital.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping("/public")
	public String publicEndpoint() {
		return "This is a public endpoint";
	}

	@GetMapping("/private")
	@PreAuthorize("hasRole('ADMIN')")
	public String privateEndpoint() {
		return "This is a protected endpoint";
	}

	@GetMapping("/me")
	public ResponseEntity<UserMeResponse> me(Authentication authentication) {

		String username = authentication.getName();
		String role = authentication.getAuthorities().iterator().next().getAuthority();

		return ResponseEntity.ok(new UserMeResponse(username, role));

	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request.getUserName(), request.getPassword()));
	}

}
