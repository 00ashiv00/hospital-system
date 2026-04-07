package com.hospital.auth.dto;

public class UserMeResponse {

	private String username;
	private String role;

	public UserMeResponse(String username, String role) {
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

}
