// Author: Shade McLoed

package gui.controller;

import models.EquipmentInventory;

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
public class ViewEquipmentInventory extends JInternalFrame implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Declare the components
	private JTable equipmentTable;
	private JScrollPane tableScrollPane;
	private DefaultTableModel equipmentTableModel;
	private JButton refreshBtn;
	private JComboBox<String> viewCategoryBtn;

	private List<EquipmentInventory> equipmentList;
	private GridBagConstraints gbc;
	private Client client;

	public ViewEquipmentInventory() {
		super("View Equipment Inventory", true, true, true, true);
		this.setLayout(new GridBagLayout());
		this.client = new Client();

		intializeComponent();
		addComponentsToWindow();
		addActionListeners();
		setWindowsProperties();

		client.sendAction("View Equipment Inventory");
		equipmentList = client.viewEquipmentInventoryResponse();

		populateTable();
	}

	// Initialize the components
	public void intializeComponent() {
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
			String[] colName = { "ID", "Category", "Equipment Type", "Quantity" };

			equipmentTableModel.setColumnIdentifiers(colName);


			for (EquipmentInventory equipment : equipmentList) {
				
				equipmentTableModel.addRow(new String[] { String.valueOf(equipment.getId()), equipment.getCategory(),
						equipment.getEquipmentType(), String.valueOf(equipment.getEquipmentQuantity())});
				}
			}
		}

	public void updateEquipmentTable(List<EquipmentInventory> equipmentList) {
		// Initialize the table model if not already initialized
		if (equipmentTableModel == null) {
			equipmentTableModel = new DefaultTableModel();
		}

		// Clear existing data
		equipmentTableModel.setRowCount(0);

		for (EquipmentInventory equipmentInventory : equipmentList) {
			equipmentTableModel.addRow(new String[] { String.valueOf(equipmentInventory.getId()), equipmentInventory.getCategory(),
					equipmentInventory.getEquipmentType(), String.valueOf(equipmentInventory.getEquipmentQuantity())});
			}
		

		equipmentTable.setModel(equipmentTableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

			updateEquipmentTable(equipmentList);
		}
	

public static void main (String [] args) {
	new ViewEquipmentInventory (); 
}
}
