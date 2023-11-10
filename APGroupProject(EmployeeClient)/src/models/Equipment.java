package models;

import java.io.Serializable;

public class Equipment implements Serializable{
	private String category;
	private Date dateAvailable;
	private double cost;
	private int id;
	private String equipmentType;
	private boolean rentalStatus;
	
	public Equipment(){
		category = "";
		dateAvailable = new Date();
		cost = 0.0;
		id = 0;
		equipmentType = "";
		rentalStatus = false;
	}
	
	public Equipment(String category, Date dateAvailabale, double cost, int id, String equipmentType, boolean rentalStatus){
		this.category = category;
		this.dateAvailable = dateAvailabale;
		this.cost = cost;
		this.id = id;
		this.equipmentType = equipmentType;
		this.rentalStatus = rentalStatus;
	}
	
	public Equipment(Equipment equipment){
		this.category = equipment.category;
		this.dateAvailable = equipment.dateAvailable;
		this.cost = equipment.cost;
		this.id = equipment.id;
		this.equipmentType = equipment.equipmentType;
		this.rentalStatus = equipment.rentalStatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(Date dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public boolean getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(boolean rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	@Override
	public String toString() {
		return "Category: " + category + "\nDate Available: " + dateAvailable + "\nCost: " + cost + "\nID: " + id
				+ "\nType: " + equipmentType + "\nRental Status: " + rentalStatus;
	}
	
	public void display() {
		System.out.println(toString());
	}
}
