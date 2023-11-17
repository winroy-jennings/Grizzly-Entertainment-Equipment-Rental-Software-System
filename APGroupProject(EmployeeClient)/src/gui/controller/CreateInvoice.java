//Author: Aneska Bryan //

package gui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.Client;


public class CreateInvoice extends JInternalFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8334029352500354022L;
	private JButton createBtn;
	private JButton clearBtn;

	private JLabel equipmentIdLbl;
	private JTextField equipmentIdTFld;

	private JLabel customerIdLbl;
	private JTextField customerIdTFld;

	private GridBagConstraints gbc;
	private Client client;

	private static final Logger logger = LogManager.getLogger(CreateInvoice.class);

	public CreateInvoice() {
		super("Create Invoice", true, true, true, true);
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

		createBtn = new JButton("Create");
		clearBtn = new JButton("Clear");


		customerIdLbl = new JLabel("Customer ID: ");
		customerIdTFld = new JTextField();


		equipmentIdLbl = new JLabel("Equipment ID: ");
		equipmentIdTFld = new JTextField();

	}

	// Add components to the window
	public void addComponentsToWindow() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		gbc.insets = new Insets(5, 10, 10, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(createBtn, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(clearBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(equipmentIdLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(equipmentIdTFld, gbc);

		//
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(customerIdLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(customerIdTFld, gbc);


	}

	public void addActionListeners() {
		createBtn.addActionListener(this);
		clearBtn.addActionListener(this);
	}

	public void setWindowsProperties() {
		this.setSize(600, 360);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createBtn) {
			if (!equipmentIdTFld.getText().isEmpty() || !customerIdTFld.getText().isEmpty()) {

							client.sendAction("Validate Customer ID");
							client.sendCustomerID(Integer.parseInt(customerIdTFld.getText()));
							boolean validateCustomerID = client.validateCustomerID();

							if (validateCustomerID) {
								client.sendAction("Validate Equipment ID");
								client.sendEmployeeID(Integer.parseInt(equipmentIdTFld.getText()));
								boolean validateEquipmentID = client.validateEquipmentID();


									if (validateEquipmentID) {


										client.sendAction("Add Invoice");
										client.sendCustomerID(Integer.parseInt(customerIdTFld.getText()));
										client.sendEmployeeID(Integer.parseInt(equipmentIdTFld.getText()));
										boolean invoiceStatus = client.invoiceReceived();

										if (invoiceStatus) {
											JOptionPane.showMessageDialog(this, "Invoice created successfully!",
													"Invoice Status", JOptionPane.INFORMATION_MESSAGE);
													
										} else {
											JOptionPane.showMessageDialog(this,
													"Error occured while processing invoice", "Invoice Status",
													JOptionPane.ERROR_MESSAGE);
													logger.error("Error occured while processing invoice");
										}
									} else {
										JOptionPane.showMessageDialog(this, "Invalid equipment ID, try again",
												"Invoice Status", JOptionPane.ERROR_MESSAGE);
												logger.error("Invalid equipment ID, try again");
									}
								} else {
									JOptionPane.showMessageDialog(this, "Invalid employee ID, try again",
											"Invoice Status", JOptionPane.ERROR_MESSAGE);
											logger.error("Invalid employee ID, try again");
								}
							} else {
								JOptionPane.showMessageDialog(this, "Invalid customer ID, try again", "Invoice Status",
										JOptionPane.ERROR_MESSAGE);
										logger.error("Invalid customer ID, try again", "Invoice Status");
							}

		} else if (e.getSource() == clearBtn) {
			customerIdTFld.setText("");
			equipmentIdTFld.setText("");
		}
	}
	
	public static void main (String []args) {
		new CreateInvoice();
	}

}
