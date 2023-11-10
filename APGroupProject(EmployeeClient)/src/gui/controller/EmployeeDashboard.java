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

public class EmployeeDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel employeeDashboardContentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDashboard frame = new EmployeeDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmployeeDashboard() {
		setTitle("Employee Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 356);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("Options");
		setJMenuBar(menuBar);
		
		JMenu mnEmployeeDashboard = new JMenu("Click to view employee dashboard options");
		menuBar.add(mnEmployeeDashboard);
		
		JMenuItem viewRentalRequestMenuItem = new JMenuItem("View Rental Requests");
		mnEmployeeDashboard.add(viewRentalRequestMenuItem);
		
		JMenuItem viewEquipmentInventoryMenuItem = new JMenuItem("View Equipment Inventory");
		mnEmployeeDashboard.add(viewEquipmentInventoryMenuItem);
		
		JMenuItem scheduleEquipmentMenuItem = new JMenuItem("Schedule Equipment");
		mnEmployeeDashboard.add(scheduleEquipmentMenuItem);
		
		JMenuItem createInvoiceMenuItem = new JMenuItem("Create Invoice");
		mnEmployeeDashboard.add(createInvoiceMenuItem);
		
		JMenuItem createReceiptMenuItem = new JMenuItem("Create Receipt");
		mnEmployeeDashboard.add(createReceiptMenuItem);
		
		JMenuItem createQuotationMenuItem = new JMenuItem("Create Quotation");
		mnEmployeeDashboard.add(createQuotationMenuItem);
		
		JMenuItem viewNewMessagesMenuItem = new JMenuItem("View New Messages");
		mnEmployeeDashboard.add(viewNewMessagesMenuItem);
		
		JMenuItem leaveAMessageMenuItem = new JMenuItem("Leave A Message");
		mnEmployeeDashboard.add(leaveAMessageMenuItem);
		
		JMenuItem logOutMenuItem = new JMenuItem("Log Out");
		mnEmployeeDashboard.add(logOutMenuItem);
		employeeDashboardContentPane = new JPanel();
		employeeDashboardContentPane.setBackground(new Color(0, 0, 255));
		employeeDashboardContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(employeeDashboardContentPane);
		
		logOutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new WelcomeMenu().setVisible(true);
				dispose();
			}
			
		});
	}

}
