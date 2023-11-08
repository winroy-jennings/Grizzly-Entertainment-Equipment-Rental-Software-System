package approject;

public class Employee {
	private String employeePosition;
	
	public Employee(String message) {
		this.employeePosition = "";

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
