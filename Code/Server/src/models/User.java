package models;

public class User {
	protected int id;
	protected String username;
	protected String password;
	protected String accountType;
	protected String firstName;
	protected String lastName;
	
	public User() {
		id = 0;
		username = "";
		password = "";
		accountType = "";
		firstName = "";
		lastName = "";
	}
	
	public User(int id, String username, String password, String accountType, String firstName, String lastName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User(User user) {
		this.id = user.id;
		this.username = user.username;
		this.password = user.password;
		this.accountType = user.accountType;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User ID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\nAccount Type: " + accountType
				+ "\nFirst Name: " + firstName + "\nLast Name: " + lastName;
	}
	
	public void display() {
		System.out.println(toString());
	}
	
}