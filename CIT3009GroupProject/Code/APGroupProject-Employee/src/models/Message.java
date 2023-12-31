/*
 Lecturer/Tutot/Lab Teacher: Mr. Christopher Panther
 Occurrence: UN1
 Group Member Names and ID Numbers:
 Briana Taylor - 2100212
 Winroy Jennings - 2106397
 Shade Mcleod - 2102952
 Aneska Bryan - 2102374
 */
package models;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -3149594265881572254L;
	
	private int id;
	private int customerId;
	private int employeeId;
	private int equipmentId;
	private String message;

	public Message() {
		id = 0;
		message = "";
		customerId = 0;
		message = "";
	}

	public Message(int id, int customerId, int employeeId, int equipmentId, String message) {
		this.id = id;
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.equipmentId = equipmentId;
		this.message = message;
	}
	
	public Message(int customerId, int employeeId, String message) {
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.message = message;
	}

	public Message(Message m) {
		this.id = m.id;
		this.message = m.message;
		this.customerId = m.customerId;
		this.equipmentId = m.equipmentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", customerId=" + customerId + ", employeeId=" + employeeId + ", equipmentId="
				+ equipmentId + ", message=" + message + "]";
	}
	
	public void display() {
		System.out.print(toString());
	}
}