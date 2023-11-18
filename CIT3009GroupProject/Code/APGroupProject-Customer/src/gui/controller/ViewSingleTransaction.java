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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.color.*;
import java.awt.Color;
import java.awt.Font;
import java.util.Queue;


import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import client.Client;
import models.Transaction;

//Author: Aneska Bryan

public class ViewSingleTransaction {
	private JFrame frame = new JFrame ("View Single Transaction");
	public JMenuBar transactionBar;
	public JMenu transactionMenu;
	public JMenuItem menuItem;
	public JPanel panel = new JPanel();
	private JButton backBtn;
	private JButton enterBtn;
	private JLabel transLabel;
	private JTable response = new JTable();
	private JScrollPane scrollPane;
	JTextField transField = new JTextField();
	
	
	
	public ViewSingleTransaction() {
		frame.setResizable(false);
        frame.setBounds(700, 300, 700, 591);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null); // center output on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0, 128, 255));
        frame.setVisible(true);
        
        transLabel = new JLabel("Enter Transaction ID:  ");
        transLabel.setBounds(20, 10, 290, 35); // x, y, width, length
        transLabel.setFont(new Font("Serif", Font.BOLD, 16));
        frame.getContentPane().add(transLabel);
        
        JTextField transField = new JTextField();
        transField.setFont(new Font("Serif", Font.PLAIN, 14));
        transField.setBounds(170, 10, 130, 30);
        frame.getContentPane().add(transField);
        
        
        JMenuBar transactionBar = new JMenuBar();
        transactionBar.setBounds(10, 10, 100, 30);
        transactionBar.setBackground(new Color(220,220,220));
        transactionBar.setForeground(Color.BLUE);
        frame.setSize(400, 300);
        frame.getContentPane().add(transactionBar);
        
        JMenu transactionMenu = new JMenu("View");
        transactionMenu.setBounds(10, 10, 100, 30);
        transactionMenu.setBackground(new Color(220,220,220));
        transactionMenu.setFont(new Font("Serif", Font.PLAIN, 14));
        transactionMenu.setForeground(Color.BLUE);
        frame.getContentPane().add(transactionMenu);
        
        
        
        JMenuItem menuItem = new JMenuItem("View Single Transaction");
        menuItem.setBounds(0, 0, 0, 0);
        menuItem.setBackground(new Color (220,220,220));
        menuItem.setFont(new Font("Serif", Font.BOLD, 10));
        frame.getContentPane().add(menuItem);
        
        
        
        JButton enterBtn = new JButton ("Enter");
        enterBtn.setFont(new Font("Serif", Font.BOLD, 14));
        enterBtn.setForeground(Color.white);
        enterBtn.setBorderPainted(false);
        enterBtn.setBounds(300, 10, 80, 30);
        enterBtn.setBackground(new Color(96, 96, 96));
        frame.getContentPane().add(enterBtn);
        
        frame.add(enterBtn);
        frame.add(transField);
        frame.add(backBtn);
        frame.add(menuItem);
        frame.add(response);
        frame.add(scrollPane);
        frame.add(transLabel);
        frame.add(transactionBar);
        frame.add(transactionMenu);
        
        
        // REMEMBER TO ADD ACTION LISTENER //
        
        backBtn = new JButton("Go Back");
        backBtn.setFont(new Font("Serif", Font.BOLD, 14));
        backBtn.setForeground(Color.white);
        backBtn.setBorderPainted(false);
        backBtn.setBounds(400, 10, 100, 30);
        backBtn.setBackground(new Color(96, 96, 96));
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.getContentPane().add(backBtn);
        
        enterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (transField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Transaction ID Missing!", "View Status", JOptionPane.WARNING_MESSAGE);
                } else {
                	String TransactionId =  transField.getText();
                    Client client = new Client();                
                    client.sendAction("Find Transactions");
                    System.out.println("Message sent to server");
                    client.sendTransactionId(Integer.parseInt(TransactionId));
                    System.out.println("Record sent to server");
                    client.receiveResponse();
                    System.out.println("Response received from server");
                    //displayTransactionDetails(singleTransaction);
                }
            }
        });
        
		}
        public void showTransactionInfo(Queue<Transaction> singleTransaction) {
            Object[] columns = {"id", "startDate", "endDate", "transactionCost"};
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columns);

            for (Transaction transaction : singleTransaction) {
            	transField.setText(Integer.toString(transaction.getId()));
                Object[] row = {transaction.getId(), transaction.getStartDate(),
                        transaction.getEndDate(), transaction.getTransactionCost()};
                model.addRow(row);
            }
            
            response.setModel(model);
            response.setBackground(Color.blue);
            response.setForeground(Color.black);
            response.setSelectionBackground(Color.lightGray);
            response.setSelectionForeground(Color.white);
            response.setGridColor(Color.red);
            response.setRowHeight(30);
            
            scrollPane = new JScrollPane(response);
            scrollPane.setForeground(Color.lightGray);
            scrollPane.setBackground(Color.WHITE);
            scrollPane.setBounds(20, 80, 930, 350);
          
            
	}
        public void ViewTable(Queue<Transaction> singleTransaction) 
    	{	
    		Object[]columns = {"id", "startDate", "endDate", "transactionCost"};
    		JTable ViewTable = new JTable();
    		DefaultTableModel mode = (DefaultTableModel) ViewTable.getModel();
    		ViewTable.setFont(new Font("Serif", Font.PLAIN, 14));
    		mode.setColumnIdentifiers(columns);
    	
    		for (Transaction transaction: singleTransaction) 
    		{
    			transField.setText(Integer.toString(transaction.getId()));
                Object[] row = {transaction.getId(),transaction.getStartDate(),transaction.getEndDate(),transaction.getTransactionCost()};
    			mode.addRow(row);
            }

    		ViewTable.setModel(mode);
    		ViewTable.setBackground(Color.lightGray);
    		ViewTable.setForeground(Color.GRAY);
    		ViewTable.setSelectionBackground(Color.lightGray);
    		ViewTable.setSelectionForeground(Color.white);
    		ViewTable.setGridColor(Color.blue);
    		ViewTable.setRowHeight(30);
    		
    		JScrollPane scroll = new JScrollPane(ViewTable);
    		scroll.setForeground(Color.lightGray);
    		scroll.setBackground(Color.WHITE);
    		scroll.setBounds(20,80,930,350);

    		frame.add(scroll);
    		frame.setVisible(true);
    	}

    }
        
    
	
	
	
	

