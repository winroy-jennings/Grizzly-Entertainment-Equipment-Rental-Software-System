/*
 Lecturer/Tutot/Lab Teacher: Mr. Christopher Panther
 Occurrence: UN1
 Group Member Names and ID Numbers:
 Briana Taylor - 2100212
 Winroy Jennings - 2106397
 Shade Mcleod - 2102952
 Aneska Bryan - 2102374
 */
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gui.controller.CreateInvoice;
import models.Customer;
import models.Date;
import models.Employee;
import models.Equipment;
import models.EquipmentInventory;
import models.Message;
import models.RentalRequest;
import models.Transaction;

public class Server {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private static Connection dbConn = null;
	private Statement stmt;
	private ResultSet result = null;

	private static final Logger logger = LogManager.getLogger(Server.class);

	public Server() {
		this.createConnection();
		this.waitForRequests();
	}

	private void waitForRequests() {
		String action = "";
		Customer customerObj = null;
		Employee employeeObj = null;
		Message messageObj = null;
		getDatabaseConnection();
		try {
			while (true) {
				connectionSocket = serverSocket.accept();
				this.configureStreams();
				try {
					action = (String) objIs.readObject();
					if (action.equals("Add Customer")) {
						customerObj = (Customer) objIs.readObject();
						addCustomerToFile(customerObj);
						objOs.writeObject(true);
					} else if (action.equals("Find Customer")) {
						int customerId = (int) objIs.readObject();
						searchForCustomer1(customerId);
						objOs.writeObject(true);
					} else if (action.equals("Check Customer")) {
						int customerId = (int) objIs.readObject();
						searchForCustomer2(customerId);
						objOs.writeObject(true);
					} else if (action.equals("Add Message")) {
						messageObj = (Message) objIs.readObject();
						addMessageToFile(messageObj);
						objOs.writeObject(true);
					} else if (action.equals("Add Employee")) {
						employeeObj = (Employee) objIs.readObject();
						addEmployeeToFile(employeeObj);
						objOs.writeObject(true);
					} else if (action.equals("Find Employee")) {
						int employeeId = (int) objIs.readObject();
						searchForEmployee(employeeId);
						objOs.writeObject(true);
					} else if (action.equals("Customer Login")) {
						int customerId = (int) objIs.readObject();
						String customerUsername = (String) objIs.readObject();
						String customerPassword = (String) objIs.readObject();
						customerLogin(customerId, customerUsername, customerPassword);
					} else if (action.equals("Employee Login")) {
						int employeeId = (int) objIs.readObject();
						String employeeUsername = (String) objIs.readObject();
						String employeePassword = (String) objIs.readObject();
						employeeLogin(employeeId, employeeUsername, employeePassword);
					}
					else if (action.equalsIgnoreCase("View Equipment Inventory")) {
					
						List<EquipmentInventory> tmp = retrieveAllAvailableEquipmentInventory();

						if (tmp != null) {
							if (!connectionSocket.isClosed()) {
								objOs.writeObject(tmp);
							}
						} else {
							if (!connectionSocket.isClosed()) {
								objOs.writeObject(false);
							}
						}
					} 
					

					// Winroy Jennings
					else if (action.equalsIgnoreCase("View Available Equipments")) {
						String category;

						try {
							category = (String) objIs.readObject();
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}

						List<Equipment> tmp = retrieveAllAvailableEquipments(category);

						if (tmp != null) {
							if (!connectionSocket.isClosed()) {
								objOs.writeObject(tmp);
							}
						} else {
							if (!connectionSocket.isClosed()) {
								objOs.writeObject(false);
							}
						}
					} else if (action.equalsIgnoreCase("Validate Equipment Availablity")) {
						String equipmentID;
						boolean isValid = false;

						// True means successful and False means unsuccessful

						try {
							equipmentID = (String) objIs.readObject();
							isValid = validateEquipmentAvailablity(equipmentID);
							objOs.writeObject(isValid);
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}
					} else if (action.equalsIgnoreCase("Process Equipment")) {
						String equipmentID;
						boolean isValid = false;

						// True means successful and False means unsuccessful

						try {
							equipmentID = (String) objIs.readObject();
							isValid = rentedEquipmentAvailablity(equipmentID);
							objOs.writeObject(isValid);
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}
					} else if (action.equalsIgnoreCase("Retrieve Cost")) {
						String equipmentID;
						double costRes = 0.0;

						// True means successful and False means unsuccessful

						try {
							equipmentID = (String) objIs.readObject();
							costRes = retrieveEquipmentCost(equipmentID);
							objOs.writeObject(costRes);
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}
					} else if (action.equalsIgnoreCase("Process Transaction")) {
						Transaction trnsTemp;
						boolean res;

						try {
							trnsTemp = (Transaction) objIs.readObject();
							res = processTransaction(trnsTemp);
							objOs.writeObject(res);
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}
					}

					// Employee Client
					else if (action.equalsIgnoreCase("View All Messages")) {
						int userID;
						List<Message> messageList;

						// True means successful and False means unsuccessful

						try {
							userID = (int) objIs.readObject();
							messageList = retrieveAllMessages(userID);
							objOs.writeObject(messageList);
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}
					} else if (action.equalsIgnoreCase("Validate Customer ID")) {
						int customerID;
						boolean result;

						// True means successful and False means unsuccessful

						try {
							customerID = (int) objIs.readObject();
							result = validateCustomerID(customerID);
							objOs.writeObject(result);
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}
					} else if (action.equalsIgnoreCase("Validate Employee ID")) {
						int employeeID;
						boolean result;

						// True means successful and False means unsuccessful

						try {
							employeeID = (int) objIs.readObject();
							result = validateEmployeeID(employeeID);
							objOs.writeObject(result);
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}
					} else if (action.equalsIgnoreCase("Validate Equipment ID")) {
						int equipmentID;
						boolean result;

						// True means successful and False means unsuccessful

						try {
							equipmentID = (int) objIs.readObject();
							result = validateEmployeeID(equipmentID);
							objOs.writeObject(result);
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}
					} else if (action.equalsIgnoreCase("Add Rental Request")) {
						RentalRequest rentalRequest;
						boolean result;

						// True means successful and False means unsuccessful

						try {
							rentalRequest = (RentalRequest) objIs.readObject();
							result = addSheduleEquipment(rentalRequest);
							objOs.writeObject(result);
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}
					}

					// Add Invoice
					else if (action.equalsIgnoreCase("Add Invoice")) {
						int customerID;
						int equipmentID;
						boolean result = false;

						// True means successful and False means unsuccessful

						try {
							customerID = (int) objIs.readObject();
							equipmentID = (int) objIs.readObject();
							result = addInvoice(customerID, equipmentID);
							objOs.writeObject(result);
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e.getMessage());
							break;
						}
					}

				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				} catch (ClassCastException ex) {
					ex.printStackTrace();
				}
				this.closeConnection();
			}
		} catch (EOFException ex) {
			System.out.println("Client has terminated the connections with the server");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public List<EquipmentInventory> retrieveAllAvailableEquipmentIneventory() {
		List<EquipmentInventory> equipmentList = new ArrayList<>();
		String query = "SELECT id, category, equipmentType, COUNT(equipmentType) AS equipmentQuantity FROM EquipmentInventory";
		

		try (Statement stat = dbConn.createStatement(); ResultSet result = stat.executeQuery(query)) {
			while (result.next()) {
				int id = result.getInt("id");
				String category = result.getString("category");
 
				String equipmentType = result.getString("equipmentType");
				int equipmentQuantity = result.getInt("equipmentType");

				EquipmentInventory equipmentInventory = new EquipmentInventory(category,id, equipmentType,equipmentQuantity);

				equipmentList.add(equipmentInventory);
			}

			logger.info("Retrieve list of equipment inventory.");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return equipmentList;
	}

	private void createConnection() {
		try {
			serverSocket = new ServerSocket(8888);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void configureStreams() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException ex) {

			ex.printStackTrace();
		}
	}

	public static Connection getDatabaseConnection() {
		if (dbConn == null) {
			String url = "jdbc:mysql://localhost:3306/grizzlyentertainment";
			try {
				dbConn = DriverManager.getConnection(url, "root", "");
				JOptionPane.showMessageDialog(null, "Connection Established", "JDBC Connection Status",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				System.err.println("SQL Exception: " + e.getMessage());
			} catch (Exception e) {
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
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void addCustomerToFile(Customer customer) {
		String sql = "INSERT INTO grizzlyentertainment.customer(customerId, username, password, accountType, accountBalance)"
				+ "VALUES('" + customer.getId() + "', '" + customer.getUsername() + "', '" + customer.getPassword()
				+ "', '" + customer.getAccountType() + "', '" + customer.getAccountBalance() + "');";
		try {
			stmt = dbConn.createStatement();
			if ((stmt.executeUpdate(sql) == 1)) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
		}
	}

	public void addEmployeeToFile(Employee employee) {
		String sql = "INSERT INTO grizzlyentertainment.employee(employeeId, username, password, accountType, position)"
				+ "VALUES('" + employee.getId() + "', '" + employee.getUsername() + "', '" + employee.getPassword()
				+ "', '" + employee.getAccountType() + "', '" + employee.getEmployeePosition() + "');";
		try {
			stmt = dbConn.createStatement();
			if ((stmt.executeUpdate(sql) == 1)) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
		}
	}

	public void addMessageToFile(Message message) {
		String sql = "INSERT INTO grizzlyentertainment.message(customerId, equipmentId, message)" + "VALUES('"
				+ message.getCustomerId() + "', '" + message.getEquipmentId() + "', '" + message.getMessage() + "');";
		try {
			stmt = dbConn.createStatement();
			if ((stmt.executeUpdate(sql) == 1)) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
		}
	}

	public void searchForCustomer1(int customerId) {
		String searchSQL = "SELECT * from grizzlyentertainment.customer WHERE customerId = " + customerId;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(searchSQL);
			if (result.next()) {
				String id = result.getString("customerId");
				String username = result.getString("username");
				String password = result.getString("password");
				String accountType = result.getString("accountType");
				String accountBalance = result.getString("accountBalance");
				String message = "Account Type: " + accountType + "\nID: " + id + "\nUsername: " + username
						+ "\nPassword: " + password + "\nAccount Balance: " + accountBalance;
				JOptionPane.showMessageDialog(null, message, "Customer Account Information",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				System.err.println("ERROR: The record with that customer ID was not found.");
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
		}
	}

	public void searchForCustomer2(int customerId) {
		String searchSQL = "SELECT * from grizzlyentertainment.customer WHERE customerId = " + customerId;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(searchSQL);
			if (result.next()) {
				String id = result.getString("customerId");
				String username = result.getString("username");
				String password = result.getString("password");
				String accountType = result.getString("accountType");
				String accountBalance = result.getString("accountBalance");
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Entry. Customer ID not found.", "Error Status",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
		}
	}

	private Employee searchForEmployee(int employeeId) {
		Employee employeeObj = new Employee();
		String query = "SELECT * from grizzlyentertainment.employee WHERE employeeId = " + employeeId;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				employeeObj.setId(result.getInt(1));
				employeeObj.setUsername(result.getString(2));
				employeeObj.setPassword(result.getString(3));
				employeeObj.setAccountType(result.getString(4));
				employeeObj.setEmployeePosition(result.getString(5));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return employeeObj;
	}

	private void customerLogin(int customerId, String customerUsername, String customerPassword) {
		String sql = "SELECT * FROM grizzlyentertainment.customer WHERE customerId = ? AND username = ? AND password = ?";
		try {
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setInt(1, customerId);
			stmt.setString(2, customerUsername);
			stmt.setString(3, customerPassword);
			result = stmt.executeQuery();
			if (result.next()) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IO Exception: " + e.getMessage());
		}
	}

	private void employeeLogin(int employeeId, String employeeUsername, String employeePassword) {
		String sql = "SELECT * FROM grizzlyentertainment.employee WHERE employeeId = ? AND username = ? AND password = ?";
		try {
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			stmt.setString(2, employeeUsername);
			stmt.setString(3, employeePassword);
			result = stmt.executeQuery();
			if (result.next()) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IO Exception: " + e.getMessage());
		}
	}

	// Winroy Jennings
	// View Equipment
	public List<Equipment> retrieveAllAvailableEquipments(String cat) {
		List<Equipment> equipmentList = new ArrayList<>();
		String query = null;

		switch (cat) {
		case "All":
			query = "SELECT * FROM equipment WHERE rentalStatus='" + 0 + "'";
			break;
		case "Staging":
			query = "SELECT * FROM equipment WHERE rentalStatus='" + 0 + "' AND equipmentType='Staging'";
			break;
		case "Lighting":
			query = "SELECT * FROM equipment WHERE rentalStatus='" + 0 + "' AND equipmentType='Lighting'";
			break;
		case "Power":
			query = "SELECT * FROM equipment WHERE rentalStatus='" + 0 + "' AND equipmentType='Power'";
			break;
		case "Sound":
			query = "SELECT * FROM equipment WHERE rentalStatus='" + 0 + "' AND equipmentType='Sound'";
			break;
		}

		try (Statement stat = dbConn.createStatement(); ResultSet result = stat.executeQuery(query)) {
			while (result.next()) {
				int id = result.getInt("id");
				String category = result.getString("category");
				String[] dateAvailable = result.getString("dateAvailable").split("-");

				double cost = result.getDouble("cost");
				String equipmentType = result.getString("equipmentType");
				int rentalStatus = result.getInt("rentalStatus");

				boolean status = false;

				if (rentalStatus == 1) {
					status = true;
				}

				int year = Integer.parseInt(dateAvailable[2]);
				int month = Integer.parseInt(dateAvailable[1]);
				int day = Integer.parseInt(dateAvailable[0]);

				Equipment equipment = new Equipment(id, category, new Date(day, month, year), cost, equipmentType,
						status);

				equipmentList.add(equipment);
			}

			logger.info("Retrieve list of available equipments from the database.");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return equipmentList;
	}

	// Rent Equipment | True means successful and False means unsuccessful
	public boolean validateEquipmentAvailablity(String equipmentID) {
		boolean result = false;

		String query = "SELECT * FROM equipment WHERE id='" + equipmentID + "'";

		try {
			Statement stat = dbConn.createStatement();
			result = stat.execute(query);
			logger.info("Validate available equipment.");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return result;
	}

	// 1 means successful or 0 means unsuccessful
	public boolean rentedEquipmentAvailablity(String equipmentID) {
		int result = 0;

		String query = "UPDATE equipment SET rentalStatus=1 WHERE id='" + equipmentID + "'";

		try {
			Statement stat = dbConn.createStatement();
			result = stat.executeUpdate(query);
			logger.info("An equipment was updated in the database.");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		if (result == 1) {
			return true;
		}

		return false;
	}

	public double retrieveEquipmentCost(String equipmentID) {
		ResultSet result = null;
		double equipCost = 0;

		String query = "SELECT cost FROM equipment WHERE id='" + equipmentID + "'";

		try {
			Statement stat = dbConn.createStatement();
			result = stat.executeQuery(query);
			logger.info("An equipment cost was retrieved from the database.");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		try {
			if (result.next()) {
				equipCost = result.getDouble("cost");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return equipCost;
	}

	// Transaction
	public boolean processTransaction(Transaction transation) {
		int result = 0;

		String sqlStartDateFormat = transation.getStartDate().getYear() + "-" + transation.getStartDate().getMonth()
				+ "-" + transation.getStartDate().getDay();

		String sqlEndDateFormat = transation.getEndDate().getYear() + "-" + transation.getEndDate().getMonth() + "-"
				+ transation.getEndDate().getDay();

		String query = "INSERT INTO transaction (startDate, endDate, transactionCost) VALUES ('" + sqlStartDateFormat
				+ "', '" + sqlEndDateFormat + "', '" + transation.getTransactionCost() + "')";

		System.out.println(transation);
		System.out.println(sqlStartDateFormat);
		System.out.println(sqlEndDateFormat);

		try {
			Statement stat = dbConn.createStatement();
			result = stat.executeUpdate(query);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		if (result == 1) {
			return true;
		}

		return false;
	}

	// Employee Client
	public List<Message> retrieveAllMessages(int userID) {
		List<Message> messageList = new ArrayList<>();

		String query = "SELECT * FROM message WHERE employeeId='" + userID + "'";

		try (Statement stat = dbConn.createStatement(); ResultSet result = stat.executeQuery(query)) {
			while (result.next()) {
				int id = result.getInt("id");
				int customerId = result.getInt("customerId");
				int employeeId = result.getInt("employeeId");
				int equipmentId = result.getInt("equipmentId");
				String messageSrg = result.getString("message");

				Message message = new Message(id, customerId, employeeId, equipmentId, messageSrg);
				messageList.add(message);
				logger.info("Retrieve list of message from the database.");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return messageList;
	}

	public boolean validateCustomerID(int customerID) {
		boolean result = false;

		String query = "SELECT * FROM customer WHERE id='" + customerID + "'";

		try {
			Statement stat = dbConn.createStatement();
			result = stat.execute(query);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return result;
	}

	public boolean validateEmployeeID(int employeeID) {
		boolean result = false;

		String query = "SELECT * FROM employee WHERE id='" + employeeID + "'";

		try {
			Statement stat = dbConn.createStatement();
			result = stat.execute(query);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return result;
	}

	public boolean validateEquipmentID(int equipmentID) {
		boolean result = false;

		String query = "SELECT * FROM equipment WHERE id='" + equipmentID + "'";

		try {
			Statement stat = dbConn.createStatement();
			result = stat.execute(query);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return result;
	}

	public boolean addSheduleEquipment(RentalRequest rentalRequest) {
		int result = 0;

		String sqlStartDateFormat = rentalRequest.getRequestStartDate().getYear() + "-"
				+ rentalRequest.getRequestStartDate().getMonth() + "-" + rentalRequest.getRequestStartDate().getDay();

		String sqlEndDateFormat = rentalRequest.getRequestEndDate().getYear() + "-"
				+ rentalRequest.getRequestEndDate().getMonth() + "-" + rentalRequest.getRequestEndDate().getDay();

		String query = "INSERT INTO rentalrequest (requestStartDate, requestEndDate, customerID, employeeID, equipmentID) VALUES ('"
				+ sqlStartDateFormat + "', '" + sqlEndDateFormat + "', '" + rentalRequest.getCustomer() + "', '"
				+ rentalRequest.getEmployee() + "', '" + rentalRequest.getEquipment() + "')";

		try {
			Statement stat = dbConn.createStatement();
			result = stat.executeUpdate(query);
			logger.info("Shedule Equipment was added to the database.");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		if (result == 1) {
			return true;
		}

		return false;
	}

	public boolean addInvoice(int customerID, int equipmentID) {
		int result = 0;
		ResultSet resultSet;
		double cost = 0;

		String query = "SELECT cost FROM equipment WHERE id='" + equipmentID + "'";
		Statement stat;

		try {
			stat = dbConn.createStatement();
			resultSet = stat.executeQuery(query);

			if (resultSet.next()) {
				cost = resultSet.getDouble("cost");
			}

			// Get the current date
			LocalDate currentDate = LocalDate.now();

			// Define the desired date format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			// Format the current date
			String formattedDate = currentDate.format(formatter);

			query = "INSERT INTO invoice(customerID, equipmentID, equipmentCost, invoiceDate)" + "VALUES('" + customerID
					+ "', '" + equipmentID + "', '" + cost + "', '" + formattedDate + "');";

			try {
				Statement stat2 = dbConn.createStatement();
				result = stat2.executeUpdate(query);
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		if (result == 1) {
			return true;
		}

		return false;
	}
	
	// Aneska Bryan //
	public boolean addReceipt(int customerID, int equipmentID, int employeeID) {
		int result = 0;
		ResultSet resultSet;
		double cost = 0;

		String query = "SELECT cost FROM equipment WHERE id='" + equipmentID + "'";
		Statement stat;

		try {
			stat = dbConn.createStatement();
			resultSet = stat.executeQuery(query);

			if (resultSet.next()) {
				cost = resultSet.getDouble("cost");
			}

			// Get the current date
			LocalDate currentDate = LocalDate.now();

			// Define the desired date format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			// Format the current date
			String formattedDate = currentDate.format(formatter);

			query = "INSERT INTO receipt(customerID, employeeID, equipmentID, equipmentCost, invoiceDate)" + "VALUES('" + customerID
					+ "', '" + equipmentID + "', '" + employeeID +"','" + cost + "', '" + formattedDate + "');";

			try {
				Statement stat2 = dbConn.createStatement();
				result = stat2.executeUpdate(query);
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		if (result == 1) {
			return true;
		}

		return false;
	}

}

