package models;

import models.Date;

public class Transaction {
	private int id;
	private Date startDate;
	private Date endDate;
	private double transactionCost;

	// Default constructor
	public Transaction() {
		this.id = 0;
		this.startDate = new Date();
		this.endDate = new Date();
		this.transactionCost = 0.0;
	}

	// Primary constructor
	public Transaction(int id, Date startDate, Date endDate, double transactionCost) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.transactionCost = transactionCost;
	}

	// Copy constructor
	public Transaction(final Transaction transaction) {
		this.id = transaction.id;
		this.startDate = transaction.startDate;
		this.endDate = transaction.endDate;
		this.transactionCost = transaction.transactionCost;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getTransactionCost() {
		return transactionCost;
	}

	public void setTransactionCost(double transactionCost) {
		this.transactionCost = transactionCost;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\nStart Date: : " + startDate.toString() + "\nEnd Date: : " + endDate.toString()
				+ "Transaction Cost: " + transactionCost;
	}
}
