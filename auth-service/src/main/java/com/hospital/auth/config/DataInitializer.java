package com.hospital.auth.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hospital.auth.enums.Role;
import com.hospital.auth.model.User;
import com.hospital.auth.repository.UserRepository;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {

				User user = new User();
				user.setUsername("admin");
				user.setPassword(passwordEncoder.encode("admin123"));
				user.setRole(Role.ADMIN);
				user.setEnabled(true);

				userRepository.save(user);
				System.out.println("✅ Test user created: admin / admin123");
			}

			User user = new User();
			user.setUsername("sam");
			user.setPassword(passwordEncoder.encode("sam123"));
			user.setRole(Role.DOCTOR);
			user.setEnabled(true);

			userRepository.save(user);
			System.out.println("✅ Test user created: sam / sam123");
		};
	}

}
