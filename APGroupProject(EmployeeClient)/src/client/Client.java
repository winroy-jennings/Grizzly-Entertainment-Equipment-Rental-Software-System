/*
 Lecturer/Tutot/Lab Teacher: Mr. Christopher Panther
 Occurrence: UN1
 Group Member Names and ID Numbers:
 Briana Taylor - 2100212
 Winroy Jennings - 2106397
 Shade Mcleod - 2102952
 Aneska Bryan - 2102374
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import models.Customer;
import models.Employee;
import models.Equipment;
import models.Message;
import models.RentalRequest;
import models.Transaction;

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
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Check Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Add Equipment")) {
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Find Equipment")) {
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Find Message")) {
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Add Message")) {
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true) {
					JOptionPane.showMessageDialog(null, "Message Sent Successfully", 
							"Message Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Add Transaction")) {
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Find Transaction")) {
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Add Rental Request")) {
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Find Rental Request")) {
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Add Employee")) {
				Boolean flag = (Boolean) objIs.readObject();
			}
			if(action.equalsIgnoreCase("Find Employee")) {
				Employee employee = new Employee();
				employee = (Employee) objIs.readObject();
				if(employee==null) {
					JOptionPane.showMessageDialog(null, "Record could not be found", "Find Record Status", JOptionPane.ERROR_MESSAGE);
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
	
	public void sendEquipment(Equipment equipmentObj) {
		try {
			objOs.writeObject(equipmentObj);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(Message message) {
		try {
			objOs.writeObject(message);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendTransaction(Transaction transactionObj) {
		try {
			objOs.writeObject(transactionObj);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendRentalRequest(RentalRequest rentalRequestObj) {
		try {
			objOs.writeObject(rentalRequestObj);
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
	
	public void sendCustomerId(int customerId) {
		try {
			objOs.writeObject(customerId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmployeeId(int employeeId) {
		try {
			objOs.writeObject(employeeId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEquipmentId(int equipmentId) {
		try {
			objOs.writeObject(equipmentId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessageId(int messageId) {
		try {
			objOs.writeObject(messageId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendTransactionId(int transactionId) {
		try {
			objOs.writeObject(transactionId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendRentalRequestId(int rentalRequestId) {
		try {
			objOs.writeObject(rentalRequestId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEquipmentCategory(String equipmentCategory) {
		try {
			objOs.writeObject(equipmentCategory);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendLoginDetails(int id, String username, String password) {
		try {
            objOs.writeObject(id);
            objOs.writeObject(username);
            objOs.writeObject(password);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// Winroy Jennings
	public void viewAllMessages(int userID) {
		try {
			objOs.writeObject(userID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Message> retrieveAllMessages() {
		List<Message> result = null;

		try {
			result = (List<Message>) objIs.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void sendCustomerID(int customerID) {
		try {
			objOs.writeObject(customerID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean validateCustomerID() {
		boolean result = false;

		try {
			result = (boolean) objIs.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void sendEmployeeID(int employeeID) {
		try {
			objOs.writeObject(employeeID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean validateEmployeeID() {
		boolean result = false;

		try {
			result = (boolean) objIs.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void sendEquipmentID(int equipmentID) {
		try {
			objOs.writeObject(equipmentID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean validateEquipmentID() {
		boolean result = false;

		try {
			result = (boolean) objIs.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean rentalRequestStatus() {
		boolean result = false;

		try {
			result = (boolean) objIs.readObject();
			System.out.println("CLIENT: " + result);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

}
