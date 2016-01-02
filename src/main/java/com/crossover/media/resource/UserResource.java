package com.crossover.media.resource;

import javax.validation.constraints.NotNull;

import com.crossover.media.model.Role;

public class UserResource {
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Long id;
	
	@NotNull
	private String email;

	@NotNull
	private String password;

	@NotNull
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
