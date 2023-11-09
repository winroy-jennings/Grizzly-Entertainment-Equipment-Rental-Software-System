package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Customer;
import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCust //extends JFRAME 
{
	private JTextField customerIDField;
	private JTextField nameField;
	private JTextField passwordField;
	private JTextField contactField;
	
	public AddCust() {
		initialize();
	}
	
	private void initialize(){
		
		setTitle("Add New Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);//can change based on how it looks
		
		customerIDField = new JTextField(25);//the customer ID field has been initialized
		nameField = new JTextField(50); //initialize name field
		contactField = new JTextField(20);//initialize contact field
		passwordField = new JTextField(25);//initialize password field
	    
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(6,6,6,6);
		
		addLabelTextFieldPair(panel, "Customer ID:", customerIDField, constraints);
		addLabelTextFieldPair(panel, "Customer Name:",nameField, constraints);
		addLabelTextFieldPair(panel, "Customer Contact:",contactField, constraints);
		
		JButton addButton = new JButton("Add Customer");
		constraints.gridwidth = 2;
		constraints.gridy++;
		panel.add(addButton, constraints);
		
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			String customerID = customerIDField.getText();
			String name = nameField.getText();
			String contact = contactField.getText();
			String password = passwordField.getText();
			
			double accountBalance = 0.0;
			
			sendCustomerToServer(customerID,name,contact,password,accountBalance);
			
			customerIDField.setText("");
			nameField.setText("");
			contactField.setText("");
			passwordField.setText("");
				
		}
	});
	
	Add(panel);
		
} 
private void Add(JPanel panel) {
		// TODO Auto-generated method stub
		
	}

private void sendCustomerToServer(String customerID, String name, String contact, String password,
		double accountBalance) {
	Client client = new Client();
	Customer customer = new Customer(customerID, name, contact,password,accountBalance);
	
	client.sendAction("Add Customer");
	System.out.println("Message has been sent to server");
	
	client.sendCustomer(customer);
	System.out.println("Record has been sent to server");
	
	client.receiveResponse();
	System.out.println("Response has been received from server");
}
private void addLabelTextFieldPair(JPanel panel, String labelText, JTextField textField, GridBagConstraints
		constraints)
{
	JLabel label = new JLabel(labelText);
	constraints.gridy++;
	constraints.gridx = 0;
	panel.add(textField);
}

public static void main (String [] args) {
	SwingUtilities.invokeLater(new Runnable(){
		public void run() {
			new AddCust().setVisible(true);
		}
	});
}
}
