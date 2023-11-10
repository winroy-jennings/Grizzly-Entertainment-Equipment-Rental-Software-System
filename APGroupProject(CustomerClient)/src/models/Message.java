package models;

import java.io.Serializable;

public class Message implements Serializable{
	private int id;
	private String message;
	private String sender;
	private String receiver;

	public Message() {
		this.id = 0;
		this.message = "";
		this.sender = "";
		this.receiver = "";
	}
	
	public Message(int id, String message, String sender, String receiver) {
		this.id = id;
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public Message (Message message) {
		this.id = message.id;
		this.message = message.message;
		this.sender = message.sender;
		this.receiver = message.receiver;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String reciever) {
		this.receiver = reciever;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\nMessage: " + message + "\nSender: " + sender + "\nReceiver: " + receiver ;
	}
	
	
}