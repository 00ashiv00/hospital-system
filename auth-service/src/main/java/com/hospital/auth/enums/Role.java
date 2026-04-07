package com.hospital.auth.enums;

public enum Role {
	ADMIN, PATIENT, DOCTOR;

	public String asAuthority() {
		return "ROLE_" + this.name();
	}
}
