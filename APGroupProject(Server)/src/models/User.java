package models;

import java.io.Serializable;

public class User implements Serializable{
	protected String id;
	protected String username;
	protected String password;
	protected String accountType;
	
	public User() {
		id = "";
		username = "";
		password = "";
		accountType = "";
	}
	
	public User(String id, String username, String password, String accountType) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.accountType = accountType;
	}
	
	public User(User user) {
		this.id = user.id;
		this.username = user.username;
		this.password = user.password;
		this.accountType = user.accountType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "User ID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\nAccount Type: " + accountType;
	}
	
	public void display() {
		System.out.println(toString());
	}
	
}
