package models;

import java.io.Serializable;

public class RentalRequest implements Serializable{
	private int id;
	private Date requestStartDate;
	private Date requestEndDate;
	private User customer;
	private User employee;
	
	public RentalRequest(){
		id = 0;
		requestStartDate = new Date();
		requestEndDate = new Date();
		customer = new Customer();
		employee = new Employee();
	}

	public RentalRequest(int id, Date requestStartDate, Date requestEndDate, User customer, User employee) {
		this.id = id;
		this.requestStartDate = requestStartDate;
		this.requestEndDate = requestEndDate;
		this.customer = customer;
		this.employee = employee;
	}
	
	public RentalRequest(RentalRequest rentalRequest) {
		this.id = rentalRequest.id;
		this.requestStartDate = rentalRequest.requestStartDate;
		this.requestEndDate = rentalRequest.requestEndDate;
		this.customer = rentalRequest.customer;
		this.employee = rentalRequest.employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRequestStartDate() {
		return requestStartDate;
	}

	public void setRequestStartDate(Date requestStartDate) {
		this.requestStartDate = requestStartDate;
	}

	public Date getRequestEndDate() {
		return requestEndDate;
	}

	public void setRequestEndDate(Date requestEndDate) {
		this.requestEndDate = requestEndDate;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\nCustomer: " + customer + "/nEmployee: " + employee + "\nRequest Start Date: " + requestStartDate + "\nRequest End Date: " + requestEndDate;
	}
	
	public void display() {
		System.out.println(toString());
	}
}
