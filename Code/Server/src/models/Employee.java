package models;

public class Employee extends User {
	private String employeePosition;
	
	public Employee() {
		super();
		this.employeePosition = "";

		}
	public Employee (String employeePosition) {
		super();
		this.employeePosition = employeePosition;
	}
	
	public Employee (Employee emp) {
		super();
		this.employeePosition = emp.employeePosition;
	}
	
	public String getemployeePosition() {
		return employeePosition;
	}
	
	public void setMessage(String employeePosition) {
		this.employeePosition = employeePosition;
	}
	
   
    @Override
	public String toString() {
		return "Employee [employeePosition =" + employeePosition + "]";
	}
}
