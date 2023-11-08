package models;

import models.User;

public class Customer {
	private double amountBalance;

	// Default constructor
	public Customer() {
		super();
		this.amountBalance = 0.0;
	}

	// Primary constructor
	public Customer(double amountBalance) {
		super();
		this.amountBalance = amountBalance;
	}

	// Copy constructor
	public Customer(final Customer costomer) {
		super();
		this.amountBalance = costomer.amountBalance;
	}

	// Retrieve the customer's account balance
	public double getAccountBalance() {
		return 0.0;
	}

	// Sets the customer's account balance
	public void setAccountBalance(double amountBalance) {
		this.amountBalance = amountBalance;
	}

	@Override
	public String toString() {
		return "Customer Amount Balance: " + amountBalance;
	}

	public void display() {
		System.out.println(toString());
	}
}
