package models;

public class Employee {
	private String employeePosition;
	
	public Employee() {
		this.employeePosition = "";

		}
	public Employee (String employeePosition) {
		this.employeePosition = employeePosition;
	}
	
	public Employee (Employee emp) {
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

	public void display() {

    }
}
