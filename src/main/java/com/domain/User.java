package com.domain;

import org.springframework.data.annotation.Id;

public class User {
	@Id
	private String id;
	
	private String username;

	public User(){}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}
	
}
