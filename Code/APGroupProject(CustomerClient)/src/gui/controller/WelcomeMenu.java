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
		setBounds(100, 100, 568, 290);
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
		lblQuestion.setBounds(146, 103, 287, 25);
		welcomeContentPane.add(lblQuestion);
		
		String loginOrCreate[] = {"","Login", "Create Account"};
		JComboBox<String> comboBox = new JComboBox<>(loginOrCreate);
		comboBox.setBounds(201, 150, 162, 21);
		welcomeContentPane.add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setMnemonic('S'); 
		btnSubmit.setToolTipText("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmit.setBounds(228, 192, 113, 37);
		welcomeContentPane.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                if("Create Account".equals(selectedItem)){
                	new CustomerRegistration().setVisible(true);
                	dispose();
                }
                else if("Login".equals(selectedItem)){
                    new CustomerLogin().setVisible(true);
                    dispose();
                }
            }
        });
	}
}
