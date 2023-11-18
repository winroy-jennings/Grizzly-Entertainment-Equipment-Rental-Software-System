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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.Client;
import models.Date;
import models.RentalRequest;

public class ScheduleEquipment extends JInternalFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5988524923589955930L;
	// Declare the components
	private JButton requestBtn;
	private JButton clearBtn;

	private JLabel requestStartDateLbl;
	private JTextField requestStartDateTFld;

	private JLabel requestEndDateLbl;
	private JTextField requestEndDateTFld;

	private JLabel customerIdLbl;
	private JTextField customerIdTFld;

	private JLabel employeeIdLbl;
	private JTextField employeeIdTFld;

	private JLabel equipmentIdLbl;;
	private JTextField equipmentIdTFld;;

	private GridBagConstraints gbc;
	private Client client;

	private static final String DATE_FORMAT_REGEX = "^\\d{4}/\\d{2}/\\d{2}$";
	private static final Pattern pattern = Pattern.compile(DATE_FORMAT_REGEX);

	public ScheduleEquipment() {
		super("Schedule Equipment", true, true, true, true);
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

		requestBtn = new JButton("Request");
		clearBtn = new JButton("Clear");

		requestStartDateLbl = new JLabel("Request Start Date (Format: YYYY/MM/DD): ");
		requestStartDateTFld = new JTextField();

		requestEndDateLbl = new JLabel("Request End Date (Format: YYYY/MM/DD): ");
		requestEndDateTFld = new JTextField();

		customerIdLbl = new JLabel("Customer ID: ");
		customerIdTFld = new JTextField();

		employeeIdLbl = new JLabel("Employee ID: ");
		employeeIdTFld = new JTextField();

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
		this.add(requestBtn, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(clearBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(requestStartDateLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(requestStartDateTFld, gbc);

		//
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(requestEndDateLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(requestEndDateTFld, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(customerIdLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(customerIdTFld, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(employeeIdLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		this.add(employeeIdTFld, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(equipmentIdLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		this.add(equipmentIdTFld, gbc);
	}

	public void addActionListeners() {
		requestBtn.addActionListener(this);
		clearBtn.addActionListener(this);
	}

	public void setWindowsProperties() {
		this.setSize(600, 360);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == requestBtn) {
			if (!requestStartDateTFld.getText().isEmpty() || !requestEndDateTFld.getText().isEmpty()) {
				if (!customerIdTFld.getText().isEmpty() && !employeeIdTFld.getText().isEmpty()
						&& !equipmentIdTFld.getText().isEmpty()) {

					if (isValidDateFormat(requestStartDateTFld.getText())) {
						if (isValidDateFormat(requestEndDateTFld.getText())) {
							client.sendAction("Validate Customer ID");
							client.sendCustomerID(Integer.parseInt(customerIdTFld.getText()));
							boolean validateCustomerID = client.validateCustomerID();

							if (validateCustomerID) {
								client.sendAction("Validate Employee ID");
								client.sendEmployeeID(Integer.parseInt(employeeIdTFld.getText()));
								boolean validateEmployeeID = client.validateEmployeeID();

								if (validateEmployeeID) {
									client.sendAction("Validate Equipment ID");
									client.sendEquipmentID(Integer.parseInt(equipmentIdTFld.getText()));
									boolean validateEquipmentID = client.validateEquipmentID();

									if (validateEquipmentID) {

										String[] startDList = requestStartDateTFld.getText().split("/");
										String[] endDList = requestEndDateTFld.getText().split("/");

										RentalRequest rentalResquest = new RentalRequest();

										rentalResquest.setRequestStartDate(new Date(Integer.parseInt(startDList[2]),
												Integer.parseInt(startDList[1]), Integer.parseInt(startDList[0])));

										rentalResquest.setRequestEndDate(new Date(Integer.parseInt(endDList[2]),
												Integer.parseInt(endDList[1]), Integer.parseInt(endDList[0])));

										rentalResquest.getCustomer().setId(Integer.parseInt(customerIdTFld.getText()));
										rentalResquest.getEmployee().setId(Integer.parseInt(employeeIdTFld.getText()));
										rentalResquest.setEquipment(Integer.parseInt(equipmentIdTFld.getText()));

										client.sendAction("Add Rental Request");
										client.sendRentalRequest(rentalResquest);
										boolean rentalRequestStatus = client.rentalRequestStatus();

										if (rentalRequestStatus) {
											JOptionPane.showMessageDialog(this, "Schedule equipment request successful",
													"Request Status", JOptionPane.INFORMATION_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(this,
													"Error occured while processing request", "Request Status",
													JOptionPane.ERROR_MESSAGE);
										}
									} else {
										JOptionPane.showMessageDialog(this, "Invalid equipment ID, try again",
												"Request Status", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(this, "Invalid employee ID, try again",
											"Request Status", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(this, "Invalid customer ID, try again", "Request Status",
										JOptionPane.ERROR_MESSAGE);
							}

						} else {
							JOptionPane.showMessageDialog(this, "Invalid end date format, try again using YYYY/MM/DD",
									"Request Status", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(this, "Invalid start date format, try again using YYYY/MM/DD",
								"Request Status", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Enter valid ID's for customer, employee, and equopment",
							"Request Status", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Enter valid dates for both start and end dates");
			}
		} else if (e.getSource() == clearBtn) {
			requestStartDateTFld.setText("");
			requestEndDateTFld.setText("");
			customerIdTFld.setText("");
			employeeIdTFld.setText("");
			equipmentIdTFld.setText("");
		}
	}

	public static boolean isValidDateFormat(String date) {
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}
}
