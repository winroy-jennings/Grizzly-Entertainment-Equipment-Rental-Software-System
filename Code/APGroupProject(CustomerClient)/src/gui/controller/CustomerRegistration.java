package gui.controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Client;
import models.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class CustomerRegistration extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel customerRegistrationContentPane;
	private JTextField textFieldCustomerId;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JButton btnBack;
	private JLabel lblCustomerRegistration;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRegistration frame = new CustomerRegistration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CustomerRegistration() {
		setTitle("Customer Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 381);
		customerRegistrationContentPane = new JPanel();
		customerRegistrationContentPane.setBackground(new Color(0, 128, 255));
		customerRegistrationContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(customerRegistrationContentPane);
		customerRegistrationContentPane.setLayout(null);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblCustomerId.setBounds(27, 59, 181, 43);
		customerRegistrationContentPane.add(lblCustomerId);
		
		textFieldCustomerId = new JTextField();
		textFieldCustomerId.setBounds(182, 67, 196, 35);
		customerRegistrationContentPane.add(textFieldCustomerId);
		textFieldCustomerId.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(27, 123, 136, 43);
		customerRegistrationContentPane.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(182, 131, 196, 35);
		customerRegistrationContentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(27, 198, 127, 35);
		customerRegistrationContentPane.add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(182, 198, 196, 35);
		customerRegistrationContentPane.add(textFieldPassword);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreateAccount.setBackground(new Color(0, 0, 160));
		btnCreateAccount.setForeground(new Color(0, 0, 0));
		btnCreateAccount.setBounds(10, 259, 196, 61);
		btnCreateAccount.setToolTipText("Create Account");
		customerRegistrationContentPane.add(btnCreateAccount);
		
		btnBack = new JButton("Back");
		btnBack.setMnemonic('B'); 
		btnBack.setToolTipText("Go back");
		btnBack.setForeground(Color.BLACK);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBackground(new Color(0, 0, 160));
		btnBack.setBounds(243, 259, 196, 61);
		customerRegistrationContentPane.add(btnBack);
		
		lblCustomerRegistration = new JLabel("Customer Registration");
		lblCustomerRegistration.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblCustomerRegistration.setBounds(107, 10, 271, 39);
		customerRegistrationContentPane.add(lblCustomerRegistration);
		
		btnCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(textFieldCustomerId.getText().equals("")||textFieldUsername.getText().equals("")||textFieldPassword.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Some fields are empty!","Customer Registration Status", JOptionPane.ERROR_MESSAGE);
				}else {
	            	String customerId = textFieldCustomerId.getText();
	            	String username = textFieldUsername.getText();
	            	String password = textFieldPassword.getText();
	            	Client sendClient = new Client();
	            	Customer customer = new Customer(customerId, username, password, "Customer", 200.00);
	            	sendClient.sendAction("Add Customer");
	            	sendClient.sendCustomer(customer);
	            	sendClient.receiveResponse();
	            	new CustomerLogin().setVisible(true);
				}
            }
        });
		
		btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new WelcomeMenu().setVisible(true);
            	dispose();
            }
        });
	}
}
