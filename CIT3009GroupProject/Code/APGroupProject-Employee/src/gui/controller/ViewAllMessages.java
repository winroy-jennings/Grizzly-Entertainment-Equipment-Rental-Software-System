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
//Author of this class: Winroy Jennings
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import client.Client;
import models.Message;

public class ViewAllMessages extends JInternalFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4093235621112545336L;
	// Declare the components
	private JTable equipmentTable;
	private JScrollPane tableScrollPane;
	private DefaultTableModel equipmentTableModel;

	private JLabel employeeID;
	private JTextField employeeIDTxtFld;

	private JButton refreshBtn;

	private List<Message> messageList;
	private GridBagConstraints gbc;
	private Client client;

	public ViewAllMessages() {
		super("View All Messages", true, true, true, true);
		this.setLayout(new GridBagLayout());
		this.client = new Client();

		intializeComponent();
		addComponentsToWindow();
		addActionListeners();
		setWindowsProperties();
	}

	// Initialize the components
	public void intializeComponent() {
		gbc = new GridBagConstraints();

		equipmentTable = new JTable();
		tableScrollPane = new JScrollPane(equipmentTable);
		equipmentTableModel = (DefaultTableModel) equipmentTable.getModel();

		employeeID = new JLabel("Employee ID");
		employeeIDTxtFld = new JTextField();

		refreshBtn = new JButton("Refresh");
	}

	// Add components to the window
	public void addComponentsToWindow() {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 10, 10, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(employeeID, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(employeeIDTxtFld, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(refreshBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		tableScrollPane.setSize(500, 500);
		this.add(tableScrollPane, gbc);
	}

	public void addActionListeners() {
		refreshBtn.addActionListener(this);
	}

	public void setWindowsProperties() {
		this.setSize(600, 360);
		this.setVisible(true);
	}

	// Populate the table with equipment data
	private void populateTable() {
		if (messageList != null) {
			String[] colName = { "Message ID", "Customer ID", "Employee ID", "Equipment ID", "Message" };
			equipmentTableModel.setColumnIdentifiers(colName);

			for (Message message : messageList) {
				equipmentTableModel.addRow(new String[] { String.valueOf(message.getId()),
						String.valueOf(message.getCustomerId()), String.valueOf(message.getEmployeeId()),
						String.valueOf(message.getEquipmentId()), message.getMessage() });
			}
		}
	}

	public void updateEquipmentTable(List<Message> messageList) {
		// Initialize the table model if not already initialized
		if (equipmentTableModel == null) {
			equipmentTableModel = new DefaultTableModel();
		}

		// Clear existing data
		equipmentTableModel.setRowCount(0);

		for (Message message : messageList) {
			equipmentTableModel.addRow(new String[] { String.valueOf(message.getId()),
					String.valueOf(message.getCustomerId()), String.valueOf(message.getEmployeeId()),
					String.valueOf(message.getEquipmentId()), message.getMessage() });
		}

		equipmentTable.setModel(equipmentTableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == refreshBtn) {

			if (!employeeIDTxtFld.getText().isEmpty()) {
				client.sendAction("Validate Employee ID");
				client.sendEmployeeID(Integer.parseInt(employeeIDTxtFld.getText()));
				boolean validateEmployeeID = client.validateEmployeeID();

//				if (validateEmployeeID) {
					client.sendAction("View All Messages");
					client.viewAllMessages(Integer.parseInt(employeeIDTxtFld.getText()));
					messageList = client.retrieveAllMessages();
//				} else {
//					JOptionPane.showMessageDialog(this, "Invalid employee ID, try again", "Request Status",
//							JOptionPane.ERROR_MESSAGE);
//				}

				updateEquipmentTable(messageList);
			} else {
				JOptionPane.showMessageDialog(this, "Enter an Employee ID first",
						"Request Status",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
