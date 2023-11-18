package gui.controller;
//Author: Winroy Jennings
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import client.Client;
import models.Equipment;

public class ViewAvailableEquipment extends JInternalFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7667992574579881625L;
	// Declare the components
	private JTable equipmentTable;
	private JScrollPane tableScrollPane;
	private DefaultTableModel equipmentTableModel;
	private JButton refreshBtn;
	private JComboBox<String> viewCategoryBtn;

	private List<Equipment> equipmentList;
	private GridBagConstraints gbc;
	private Client client;

	public ViewAvailableEquipment() {
		super("View Available Equipment", true, true, true, true);
		this.setLayout(new GridBagLayout());
		this.client = new Client();

		initializeComponent();
		addComponentsToWindow();
		addActionListeners();
		setWindowsProperties();

		client.sendAction("View Available Equipments");
		client.viewAllAvailableEquipmentsResponse("All");
		equipmentList = client.retrieveAllAvailableEquipmentsResponse();

		populateTable();
	}

	// Initialize the components
	public void initializeComponent() {
		gbc = new GridBagConstraints();

		equipmentTable = new JTable();
		tableScrollPane = new JScrollPane(equipmentTable);
		equipmentTableModel = (DefaultTableModel) equipmentTable.getModel();

		refreshBtn = new JButton("Refresh");
		viewCategoryBtn = new JComboBox<>();

		viewCategoryBtn.addItem("All");
		viewCategoryBtn.addItem("Staging");
		viewCategoryBtn.addItem("Lighting");
		viewCategoryBtn.addItem("Power");
		viewCategoryBtn.addItem("Sound");
	}

	// Add components to the window
	public void addComponentsToWindow() {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 10, 10, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(refreshBtn, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(viewCategoryBtn, gbc);

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
		if (equipmentList != null) {
			String[] colName = { "ID", "Category", "Date Available", "Cost", "Equipment Type", "Rental Status" };

			equipmentTableModel.setColumnIdentifiers(colName);

			String rentalStatus = "";

			for (Equipment equipment : equipmentList) {
				if (equipment.getRentalStatus() == 0) {
					rentalStatus = "Available";
				} else {
					rentalStatus = "Rented";
				}

				equipmentTableModel.addRow(new String[] { String.valueOf(equipment.getId()), equipment.getCategory(),
						equipment.getDateAvailable().toString(), String.valueOf("$ " + equipment.getCost()),
						equipment.getEquipmentType(), rentalStatus });
			}
		}
	}

	public void updateEquipmentTable(List<Equipment> equipmentList) {
		// Initialize the table model if not already initialized
		if (equipmentTableModel == null) {
			equipmentTableModel = new DefaultTableModel();
		}

		// Clear existing data
		equipmentTableModel.setRowCount(0);

		String rentalStatus = "";

		for (Equipment equipment : equipmentList) {
			if (equipment.getRentalStatus() == 0) {
				rentalStatus = "Available";
			} else {
				rentalStatus = "Rented";
			}

			equipmentTableModel.addRow(new String[] { String.valueOf(equipment.getId()), equipment.getCategory(),
					equipment.getDateAvailable().toString(), String.valueOf("$ " + equipment.getCost()),
					equipment.getEquipmentType(), rentalStatus });
		}

		equipmentTable.setModel(equipmentTableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == refreshBtn) {
			if (viewCategoryBtn.getSelectedItem() == "All") {
				client.sendAction("View Available Equipments");
				client.viewAllAvailableEquipmentsResponse("All");
				equipmentList = client.retrieveAllAvailableEquipmentsResponse();

			} else if (viewCategoryBtn.getSelectedItem() == "Staging") {
				client.sendAction("View Available Equipments");
				client.viewAllAvailableEquipmentsResponse("Staging");
				equipmentList = client.retrieveAllAvailableEquipmentsResponse();

			} else if (viewCategoryBtn.getSelectedItem() == "Lighting") {
				client.sendAction("View Available Equipments");
				client.viewAllAvailableEquipmentsResponse("Lighting");
				equipmentList = client.retrieveAllAvailableEquipmentsResponse();

			} else if (viewCategoryBtn.getSelectedItem() == "Power") {
				client.sendAction("View Available Equipments");
				client.viewAllAvailableEquipmentsResponse("Power");
				equipmentList = client.retrieveAllAvailableEquipmentsResponse();

			} else if (viewCategoryBtn.getSelectedItem() == "Sound") {
				client.sendAction("View Available Equipments");
				client.viewAllAvailableEquipmentsResponse("Sound");
				equipmentList = client.retrieveAllAvailableEquipmentsResponse();
			}

			updateEquipmentTable(equipmentList);
		}
	}
}
