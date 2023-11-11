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

public class Employee extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String employeePosition;
	
	public Employee() {
		this.employeePosition = "";
	}
	
	public Employee(int id, String username, String password, String accountType, String employeePosition) {
		super(id, username, password, accountType);
		this.employeePosition = employeePosition;
	}

	public Employee (Employee employee) {
		this.employeePosition = employee.employeePosition;
	}
	
	public String getEmployeePosition() {
		return employeePosition;
	}
	
	public void setEmployeePosition(String employeePosition) {
		this.employeePosition = employeePosition;
	}
	
    @Override
	public String toString() {
		return "Employee Position: " + employeePosition;
	}

	public void display() {
		System.out.println(toString());
    }
}
