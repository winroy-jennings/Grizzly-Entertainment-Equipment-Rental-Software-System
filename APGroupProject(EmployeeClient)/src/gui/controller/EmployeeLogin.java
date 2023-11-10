package gui.controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class EmployeeLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel employeeLoginContentPane;
	private JTextField textFieldEmployeeId;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JButton btnBack;
	private JLabel lblEmployeeLogin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin frame = new EmployeeLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmployeeLogin() {
		setTitle("Employee Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 381);
		employeeLoginContentPane = new JPanel();
		employeeLoginContentPane.setBackground(new Color(0, 128, 255));
		employeeLoginContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(employeeLoginContentPane);
		employeeLoginContentPane.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblEmployeeId.setBounds(27, 59, 181, 43);
		employeeLoginContentPane.add(lblEmployeeId);
		
		textFieldEmployeeId = new JTextField();
		textFieldEmployeeId.setBounds(182, 67, 196, 35);
		employeeLoginContentPane.add(textFieldEmployeeId);
		textFieldEmployeeId.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(27, 123, 136, 43);
		employeeLoginContentPane.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(182, 131, 196, 35);
		employeeLoginContentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(27, 198, 127, 35);
		employeeLoginContentPane.add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(182, 198, 196, 35);
		employeeLoginContentPane.add(textFieldPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setToolTipText("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBackground(new Color(0, 0, 160));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBounds(10, 259, 196, 61);
		employeeLoginContentPane.add(btnLogin);
		
		btnBack = new JButton("Back");
		btnBack.setMnemonic('B'); 
		btnBack.setToolTipText("Go back");
		btnBack.setForeground(Color.BLACK);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBackground(new Color(0, 0, 160));
		btnBack.setBounds(243, 259, 196, 61);
		employeeLoginContentPane.add(btnBack);
		
		lblEmployeeLogin = new JLabel("Employee Login");
		lblEmployeeLogin.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblEmployeeLogin.setBounds(129, 10, 271, 39);
		employeeLoginContentPane.add(lblEmployeeLogin);
		
		btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new WelcomeMenu().setVisible(true);
            	dispose();
            }
        });
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textFieldEmployeeId.getText().equals("")||textFieldUsername.getText().equals("")||textFieldPassword.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Some fields are empty!","Login Status", JOptionPane.ERROR_MESSAGE);
				}else {
					String employeeId = textFieldEmployeeId.getText();
	            	String username = textFieldUsername.getText();
	            	String password = textFieldPassword.getText();
	            	Client sendClient = new Client();
	            	sendClient.sendAction("Employee Login");
	            	sendClient.sendLoginDetails(employeeId, username, password);
	            	sendClient.receiveResponse();
	            	if (sendClient.isLoginStatus()) {
	            		JOptionPane.showMessageDialog(null, "Login Successful", "Login Status", JOptionPane.INFORMATION_MESSAGE);
	            		dispose();
	            		new EmployeeDashboard().setVisible(true);		            
	            		} else {
		            	JOptionPane.showMessageDialog(null, "Login Failed. Invalid Details Entered.", "Login Status", JOptionPane.INFORMATION_MESSAGE);
		            }
	            	sendClient.closeConnection();
				}
			}
			
		});
	}
}
