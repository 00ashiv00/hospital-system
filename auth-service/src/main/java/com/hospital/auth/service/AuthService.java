package com.hospital.auth.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.auth.dto.LoginResponse;
import com.hospital.auth.model.User;
import com.hospital.auth.repository.UserRepository;
import com.hospital.auth.security.JwtService;

@Service
public class AuthService {

	private PasswordEncoder passwordEncoder;
	private UserRepository userRepo;
	private JwtService jwtService;

	public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepo, JwtService jwtService) {
		this.passwordEncoder = passwordEncoder;
		this.userRepo = userRepo;
		this.jwtService = jwtService;
	}

	public LoginResponse login(String userName, String password) {
		User user = userRepo.findByUsername(userName)
				.orElseThrow(() -> new BadCredentialsException("user doesn't exist"));

		if (!user.isEnabled()) {
			throw new BadCredentialsException("User is disabled");
		}

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Credentials don't match");
		}

		return new LoginResponse(jwtService.generateToken(user));

	}

}
