package gui.controller;
//Author: Winroy Jennings
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
import models.Transaction;

public class RentAvailableEquipment extends JInternalFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6820806378666841008L;

	private JButton submitBtn;
	private JButton clearBtn;

	private JLabel idLbl;
	private JTextField idTxtFld;

	private JLabel startDateLbl;
	private JTextField startDateFld;

	private JLabel endDateLbl;
	private JTextField endDateFld;

	private GridBagConstraints gbc;
	private Client client;

	private static final String DATE_FORMAT_REGEX = "^\\d{4}/\\d{2}/\\d{2}$";
	private static final Pattern pattern = Pattern.compile(DATE_FORMAT_REGEX);

	public RentAvailableEquipment() {
		super("Rent Available Equipment", true, true, true, true);
		this.setLayout(new GridBagLayout());
		this.client = new Client();

		initializeComponent();
		addComponentsToWindow();
		addActionListeners();
		setWindowsProperties();
	}

	// Initialize the components
	public void initializeComponent() {
		gbc = new GridBagConstraints();

		submitBtn = new JButton("Submit");
		clearBtn = new JButton("Clear");

		idLbl = new JLabel("Equipment ID: ");
		idTxtFld = new JTextField();

		startDateLbl = new JLabel("Start Date (Format: YYYY/MM/DD): ");
		startDateFld = new JTextField();

		endDateLbl = new JLabel("End Date (Format: YYYY/MM/DD): ");
		endDateFld = new JTextField();
	}

	// Add components to the window
	public void addComponentsToWindow() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		gbc.insets = new Insets(5, 10, 10, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(submitBtn, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(clearBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(idLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(idTxtFld, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(startDateLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(startDateFld, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(endDateLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(endDateFld, gbc);
	}

	public void setWindowsProperties() {
		this.setSize(600, 360);
		this.setVisible(true);
	}

	public void addActionListeners() {
		submitBtn.addActionListener(this);
		clearBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submitBtn) {
			// Checks idTxtFld
			if (!idTxtFld.getText().isEmpty()) {
				if (!startDateFld.getText().isEmpty() && !endDateFld.getText().isEmpty()) {
					if (isValidDateFormat(startDateFld.getText())) {
						if (isValidDateFormat(endDateFld.getText())) {
							boolean result = false;

							client.sendAction("Validate Equipment Availablity");
							client.sendEquipmentId(idTxtFld.getText());
							result = client.receiveEquipmentValidation();

							// Changing equipment status and making a transaction
							if (result) {
								client.sendAction("Process Equipment");
								client.sendEquipmentId(idTxtFld.getText());
								boolean queryStatus = client.receiveEquipmentUpdate();

								if (queryStatus) {
									// Generate a transaction
									String[] startDList = startDateFld.getText().split("/");
									String[] endDList = endDateFld.getText().split("/");

									Transaction tempTrans = new Transaction();

									tempTrans.setStartDate(new Date(Integer.parseInt(startDList[2]),
											Integer.parseInt(startDList[1]), Integer.parseInt(startDList[0])));

									tempTrans.setEndDate(new Date(Integer.parseInt(endDList[2]),
											Integer.parseInt(endDList[1]), Integer.parseInt(endDList[0])));

									client.sendAction("Retrieve Cost");
									client.sendEquipmentId(idTxtFld.getText());
									double cost = client.receiveEquipmentCost();

									tempTrans.setTransactionCost(cost);

									client.sendAction("Process Transaction");
									client.sendTransaction(tempTrans);
									boolean transactionStatus = client.receiveTransactionStatus();

									if (transactionStatus) {
										JOptionPane.showMessageDialog(this, "Transaction was added successfully",
												"Request Status", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(this,
												"An error occured while procussing the request, try again.",
												"Request Status", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(this,
											"An error occured while procussing the request, try again.",
											"Request Status", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(this, "Invalid equipment ID, try again.",
										"Request Status", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(this, "Both date fields need to have a valid date first",
							"Request Status", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Enter a valid equipment ID first");
			}
		} else if (e.getSource() == clearBtn) {
			idTxtFld.setText("");
			startDateFld.setText("");
			endDateFld.setText("");
		}
	}

	public static boolean isValidDateFormat(String date) {
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}
}
