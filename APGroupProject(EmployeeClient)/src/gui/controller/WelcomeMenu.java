package gui.controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Client;
import models.Employee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class WelcomeMenu extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel welcomeContentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeMenu frame = new WelcomeMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WelcomeMenu() {
		setTitle("Grizzly Entertainment's Equipment Rental System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 286);
		welcomeContentPane = new JPanel();
		welcomeContentPane.setBackground(new Color(0, 128, 255));
		welcomeContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(welcomeContentPane);
		welcomeContentPane.setLayout(null);
		
		JLabel lblWelcomeMessage = new JLabel("WELCOME TO GRIZZLY ENTERTAINMENT'S EQUIPMENT RENTAL SYSTEM!!");
		lblWelcomeMessage.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblWelcomeMessage.setBounds(10, 20, 561, 73);
		welcomeContentPane.add(lblWelcomeMessage);
		
		String user[] = {"","Customer", "Employee"};
		
		JLabel lblQuestion = new JLabel("Would you like to create an account or login?");
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuestion.setBounds(144, 103, 287, 25);
		welcomeContentPane.add(lblQuestion);
		
		String loginOrCreate[] = {"","Login", "Create Account"};
		JComboBox<String> comboBox = new JComboBox<>(loginOrCreate);
		comboBox.setBounds(198, 134, 162, 21);
		welcomeContentPane.add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setMnemonic('S'); 
		btnSubmit.setToolTipText("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmit.setBounds(229, 180, 113, 37);
		welcomeContentPane.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//            	Employee employee = new Employee("99", "Briana", "Taylor", "Employee", "Secretary");
//            	Client sendClient = new Client();
//            	sendClient.sendAction("Add Employee");
//            	sendClient.sendEmployee(employee);
//            	sendClient.receiveResponse();
                String selectedItem = (String) comboBox.getSelectedItem();
                	if("Create Account".equals(selectedItem)){
                		JOptionPane.showMessageDialog(null, "You can ONLY login as an employee at this time.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if("Login".equals(selectedItem)){
                        new EmployeeLogin().setVisible(true);
                        dispose();
                    }
                }
        });
	}
}
