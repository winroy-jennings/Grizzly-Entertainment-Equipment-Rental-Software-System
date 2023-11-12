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

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Color;

public class CustomerDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel customerDashboardContentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDashboard customerDashboard = new CustomerDashboard();
					customerDashboard.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CustomerDashboard() {
		setTitle("Customer Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 356);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("Options");
		setJMenuBar(menuBar);

		JMenu mnCustomerDashboard = new JMenu("Click to view customer dashboard options");
		menuBar.add(mnCustomerDashboard);

		JMenuItem viewAvailableEquipmentMenuItem = new JMenuItem("View Available Equipment");
		mnCustomerDashboard.add(viewAvailableEquipmentMenuItem);

		JMenuItem rentAvailableEquipmentMenuItem = new JMenuItem("Rent Available Equipment");
		mnCustomerDashboard.add(rentAvailableEquipmentMenuItem);

		JMenuItem viewSingleTransactionMenuItem = new JMenuItem("View Single Transaction");
		mnCustomerDashboard.add(viewSingleTransactionMenuItem);

		JMenuItem viewAllTransactionsMenuItem = new JMenuItem("View All Transactions");
		mnCustomerDashboard.add(viewAllTransactionsMenuItem);

		JMenuItem viewNewMessagesMenuItem = new JMenuItem("View New Messages");
		mnCustomerDashboard.add(viewNewMessagesMenuItem);

		JMenuItem leaveAMessageMenuItem = new JMenuItem("Leave A Message");
		mnCustomerDashboard.add(leaveAMessageMenuItem);

		JMenuItem logOutMenuItem = new JMenuItem("Log Out");
		mnCustomerDashboard.add(logOutMenuItem);
		customerDashboardContentPane = new JPanel();
		customerDashboardContentPane.setBackground(new Color(0, 0, 255));
		customerDashboardContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(customerDashboardContentPane);

		logOutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new WelcomeMenu().setVisible(true);
				dispose();
			}

		});

		leaveAMessageMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomerSendMessage().setVisible(true);
				dispose();
			}

		});

		viewAvailableEquipmentMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewAvailableEquipment();
			}
		});

		rentAvailableEquipmentMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RentAvailableEquipment();
			}
		});
	}

}
