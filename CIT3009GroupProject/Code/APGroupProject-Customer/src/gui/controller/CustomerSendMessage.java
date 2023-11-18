/*
 Lecturer/Tutot/Lab Teacher: Mr. Christopher Panther
 Occurrence: UN1
 Group Member Names and ID Numbers:
 Briana Taylor - 2100212
 Winroy Jennings - 2106397
 Shade Mcleod - 2102952
 Aneska Bryan - 2102374
 */
package gui.controller;
//Author: Briana Taylor and Shade McLeod
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import client.Client;
import models.Message;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class CustomerSendMessage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField customerIdTextField;
	private JTextField equipmentIdTextField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSendMessage customerSendMessage = new CustomerSendMessage();
					customerSendMessage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CustomerSendMessage() {
		setTitle("Send Message");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 260);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel headerLabel = new JLabel("Send Message About Equipment");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		headerLabel.setBounds(10, 10, 322, 25);
		contentPane.add(headerLabel);
		
		customerIdTextField = new JTextField();
		customerIdTextField.setBounds(102, 38, 129, 19);
		contentPane.add(customerIdTextField);
		customerIdTextField.setColumns(10);
		
		JLabel customerIdLabel = new JLabel("Customer ID ");
		customerIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		customerIdLabel.setBounds(10, 34, 96, 25);
		contentPane.add(customerIdLabel);
		
		JLabel messageLabel = new JLabel("Message");
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		messageLabel.setBounds(10, 90, 96, 25);
		contentPane.add(messageLabel);
		
		JTextArea messageTextArea = new JTextArea();
		messageTextArea.setBounds(102, 100, 162, 70);
		contentPane.add(messageTextArea);
		
		JButton sendButton = new JButton("Send");
		sendButton.setBounds(37, 179, 85, 21);
		contentPane.add(sendButton);
		
		equipmentIdTextField = new JTextField();
		equipmentIdTextField.setBounds(102, 67, 129, 19);
		contentPane.add(equipmentIdTextField);
		equipmentIdTextField.setColumns(10);
		
		JLabel equipmentIdLabel = new JLabel("Equipment ID");
		equipmentIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		equipmentIdLabel.setBounds(10, 69, 86, 13);
		contentPane.add(equipmentIdLabel);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(161, 180, 85, 21);
		contentPane.add(backButton);
		
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
	            	if(customerIdTextField.getText().equals("")||equipmentIdTextField.getText().equals("")|| messageTextArea.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Some fields are empty!","Customer Registration Status", JOptionPane.ERROR_MESSAGE);
					}else {
		            	int customerId = Integer.parseInt(customerIdTextField.getText());
		            	int equipmentId = Integer.parseInt(equipmentIdTextField.getText());
		            	String message = messageTextArea.getText();
		            	Client sendClient = new Client();
		            	Message messageSending = new Message(customerId, equipmentId, message);
		            	sendClient.sendAction("Add Message");
		            	sendClient.sendMessage(messageSending);
		            	sendClient.receiveResponse();
		            	Client c = new Client();
		            	c.sendAction("Check Customer");
		            	c.sendCustomerId(customerId);
		            	c.receiveResponse();
					}
            	}
            catch(NumberFormatException n) {
            	JOptionPane.showMessageDialog(null, "Invalid Input. ID's must be an integer!", "Error Status", JOptionPane.ERROR_MESSAGE);
            }
				
			}
			
		});
		
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomerDashboard().setVisible(true);
				dispose();
			}
			
		});
	}
}
