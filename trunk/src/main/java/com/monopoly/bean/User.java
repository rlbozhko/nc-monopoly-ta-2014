package com.monopoly.bean;

public class User {
	private String email;
	private String password;
	private String hash;

	public User(String email, String password, String hash) {
		this.email = email;
		this.password = password;
		this.hash = hash;
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

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public int hashCode() {
		return hash.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}		
		User other = (User) obj; 
		if (this.email.equals(other.email)) {
			return true;
		}		
		
		return false;
	}
}