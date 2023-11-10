package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import models.Customer;
import models.Employee;
import models.User;


public class Server {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private static Connection dbConn=null;
	private Statement stmt;
	private ResultSet result=null;
	public Server() {
		this.createConnection();
		this.waitForRequests();
	}
	private void waitForRequests() {
		String action="";
		Customer customerObj = null;
		Employee employeeObj = null;
		getDatabaseConnection();
		try {
			while(true) {
				connectionSocket = serverSocket.accept();
				this.configureStreams();
				try {
					action = (String)objIs.readObject();
					if(action.equals("Add Customer")) {
						customerObj = (Customer) objIs.readObject();
						addCustomerToFile(customerObj);
						objOs.writeObject(true);
					}else if(action.equals("Find Customer")) {
						String customerId = (String) objIs.readObject();
						customerObj = findCustomerById(customerId);
						objOs.writeObject(customerObj);
					}
					if(action.equals("Add Employee")) {
						employeeObj = (Employee) objIs.readObject();
						addEmployeeToFile(employeeObj);
						objOs.writeObject(true);
					}else if(action.equals("Find Customer")) {
						String employeeId = (String) objIs.readObject();
						customerObj = findCustomerById(employeeId);
						objOs.writeObject(employeeObj);
					}
					if(action.equals("Customer Login")) {
                    	String customerId = (String) objIs.readObject();
                    	String customerUsername = (String) objIs.readObject();
                    	String customerPassword = (String) objIs.readObject();
                    	customerLogin(customerId, customerUsername, customerPassword);
                    }else if(action.equals("Employee Login")) {
                    	String employeeId = (String) objIs.readObject();
                    	String employeeUsername = (String) objIs.readObject();
                    	String employeePassword = (String) objIs.readObject();
                    	employeeLogin(employeeId, employeeUsername, employeePassword);
                    }
				}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}catch(ClassCastException ex) {
					ex.printStackTrace();
				}
				this.closeConnection();
			}
		}catch(EOFException ex) {
			System.out.println("Client has terminated the connections with the server");
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	private void createConnection() {
		try {
			serverSocket = new ServerSocket(8888);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	private void configureStreams() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		}catch(IOException ex) {
			
			ex.printStackTrace();
		}
	}
	
	public static Connection getDatabaseConnection() {
		if(dbConn == null) {
			String url = "jdbc:mysql://localhost:3306/grizzlyentertainment";
			try {
				dbConn = DriverManager.getConnection(url, "root", "");
				JOptionPane.showMessageDialog(null, "Connection Established",
						"JDBC Connection Status", JOptionPane.INFORMATION_MESSAGE);
			}catch(SQLException e) {
				System.err.println("SQL Exception: " + e.getMessage());
			}catch(Exception e) {
				System.err.println("Unexpected Error: " + e.getMessage());
			}
		}
		return dbConn;
	}
	
	private void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	public void addCustomerToFile(Customer customer) {
		String sql = "INSERT INTO grizzlyentertainment.customer(customerId, username, password, accountType, accountBalance)"
						+"VALUES('"+customer.getId()+"', '"+customer.getUsername()+"', '"+customer.getPassword()+"', '"+customer.getAccountType()+"', '"+customer.getAccountBalance()+"');";
		try {
			stmt = dbConn.createStatement();
			if((stmt.executeUpdate(sql)==1)) {
				objOs.writeObject(true);
			}else {
				objOs.writeObject(false);
			}
		}catch(SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		}catch(Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
		}
	}
	
	public void addEmployeeToFile(Employee employee) {
		String sql = "INSERT INTO grizzlyentertainment.employee(employeeId, username, password, accountType, position)"
						+"VALUES('"+employee.getId()+"', '"+employee.getUsername()+"', '"+employee.getPassword()+"', '"+employee.getAccountType()+"', '"+employee.getEmployeePosition()+"');";
		try {
			stmt = dbConn.createStatement();
			if((stmt.executeUpdate(sql)==1)) {
				objOs.writeObject(true);
			}else {
				objOs.writeObject(false);
			}
		}catch(SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		}catch(Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
		}
	}
	
	
	private Customer findCustomerById(String customerId) {
		User userObj = new Customer();
		Customer customerObj = new Customer();
		String query = "SELECT * from grizzlyentertainment.customer WHERE customerId = "+customerId;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if(result.next()) {
				userObj.setId(result.getString(2));
				userObj.setUsername(result.getString(3));
				userObj.setPassword(result.getString(4));
				userObj.setAccountType(result.getString(5));
				customerObj.setAccountBalance(result.getDouble(6));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return customerObj;
	}
	
	private Employee findEmployeeById(String employeeId) {
		User userObj = new Customer();
		Employee employeeObj = new Employee();
		String query = "SELECT * from grizzlyentertainment.employee WHERE employeeId = "+employeeId;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if(result.next()) {
				userObj.setId(result.getString(2));
				userObj.setUsername(result.getString(3));
				userObj.setPassword(result.getString(4));
				userObj.setAccountType(result.getString(5));
				employeeObj.setEmployeePosition(result.getString(6));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employeeObj;
	}
	
	private void customerLogin(String customerId, String customerUsername, String customerPassword) {
		String sql = "SELECT * FROM grizzlyentertainment.customer WHERE customerId = ? AND username = ? AND password = ?";
		try {
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setString(1, customerId);
    		stmt.setString(2, customerUsername);
    		stmt.setString(3, customerPassword);
			result = stmt.executeQuery();
			if(result.next()) {
				objOs.writeObject(true);
			}else {
				objOs.writeObject(false);
			}
		}catch(SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		}catch(IOException e) {
			System.err.println("IO Exception: " + e.getMessage());
		}
	}
	
	private void employeeLogin(String employeeId, String employeeUsername, String employeePassword) {
		String sql = "SELECT * FROM grizzlyentertainment.employee WHERE employeeId = ? AND username = ? AND password = ?";
		try {
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setString(1, employeeId);
    		stmt.setString(2, employeeUsername);
    		stmt.setString(3, employeePassword);
			result = stmt.executeQuery();
			if(result.next()) {
				objOs.writeObject(true);
			}else {
				objOs.writeObject(false);
			}
		}catch(SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		}catch(IOException e) {
			System.err.println("IO Exception: " + e.getMessage());
		}
	}

}
