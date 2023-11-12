/*
 Lecturer/Tutot/Lab Teacher: Mr. Christopher Panther
 Occurrence: UN1
 Group Member Names and ID Numbers:
 Briana Taylor - 2100212
 Winroy Jennings - 2106397
 Shade Mcleod - 2102952
 Aneska Bryan - 2102374
 */
package models;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -7581521551207507072L;
	
	protected int id;
	protected String username;
	protected String password;
	protected String accountType;
	
	public User() {
		id = 0;
		username = "";
		password = "";
		accountType = "";
	}
	
	public User(int id, String username, String password, String accountType) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
