package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import models.Customer;
import models.Employee;

public class Client {
	private Socket connectionSocket;
	private static ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action = "";
	private boolean loginStatus;
	
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Client() {
		this.createConnection();
		this.configureStreams();
	}
	private void createConnection() {
		try {
			connectionSocket = new Socket("127.0.0.1", 8888);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	private void configureStreams() {
		try {
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void receiveResponse() {
		try {
			if(action.equalsIgnoreCase("Add Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true) {
					JOptionPane.showMessageDialog(null, "Account Created Successfully", 
							"Customer Account Creation Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Find Customer")) {
				Customer customer = new Customer();
				customer = (Customer) objIs.readObject();
				if(customer==null) {
					JOptionPane.showMessageDialog(null, "Record could not be added", "Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if(action.equalsIgnoreCase("Add Employee")) {
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Find Employee")) {
				Employee employee = new Employee();
				employee = (Employee) objIs.readObject();
				if(employee==null) {
					JOptionPane.showMessageDialog(null, "Record could not be added", "Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if(action.equalsIgnoreCase("Customer Login")) {
				Boolean flag = (Boolean) objIs.readObject();
				loginStatus = flag;
			}
			if(action.equalsIgnoreCase("Employee Login")) {
				Boolean flag = (Boolean) objIs.readObject();
				loginStatus = flag;
			}
		}catch(ClassCastException ex) {
			ex.printStackTrace();
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendCustomer(Customer customerObj) {
		try {
			objOs.writeObject(customerObj);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmployee(Employee employeeObj) {
		try {
			objOs.writeObject(employeeObj);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendAction(String action) {
		this.action = action;
		try {
			objOs.writeObject(action);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendCustomerId(String customerId) {
		try {
			objOs.writeObject(customerId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmployeeId(String employeeId) {
		try {
			objOs.writeObject(employeeId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendLoginDetails(String id, String username, String password) {
		try {
            objOs.writeObject(id);
            objOs.writeObject(username);
            objOs.writeObject(password);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
