package models;

import java.io.Serializable;

public class Customer extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	private double amountBalance;

	public Customer() {
		this.amountBalance = 0.0;
	}

	public Customer(String id, String username, String password, String accountType, double amountBalance) {
		super(id, username, password, accountType);
		this.amountBalance = amountBalance;
	}

	public Customer(double amountBalance) {
		this.amountBalance = amountBalance;
	}

	public Customer(final Customer costomer) {
		this.amountBalance = costomer.amountBalance;
	}

	public double getAccountBalance() {
		return amountBalance;
	}

	public void setAccountBalance(double amountBalance) {
		this.amountBalance = amountBalance;
	}

	@Override
	public String toString() {
		return "Amount Balance: " + amountBalance;
	}

	public void display() {
		System.out.println(toString());
	}
}
