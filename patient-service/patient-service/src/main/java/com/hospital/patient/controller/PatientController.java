package com.hospital.patient.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
	@GetMapping("/getPatients")
	public String getPatients() {
		return "List of patients";
	}

}
