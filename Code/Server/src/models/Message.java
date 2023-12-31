package models;

public class Message {
	private String id;
	private String message;
	private String sender;
	private String reciever;
	private String date;
	
	
	public Message(String id, String message, String sender, String reciever,String date) {
		super();
		this.id = id;
		this.message = message;
		this.sender = sender;
		this.reciever = reciever;
		this.date = date;
	}
	
	public Message () {
		this.id = " ";
		this.message = " ";
		this.sender = " ";
		this.reciever = " ";
		this.date = " "; 
		
		
	}
	
	public Message (Message mess) {
		this.id = mess.id;
		this.message = mess.message;
		this.sender = mess.sender;
		this.reciever = mess.reciever;
		this.date = mess.date;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}

	
	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", sender=" + sender + ", reciever=" + reciever + "]";
	}
	
	public void display() {
        System.out.println("Message ID: " + id);
        System.out.println("Sender: " + sender);
        System.out.println("Receiver: " + reciever);
        System.out.println("Message: " + message);
        System.out.println("Date: " + date);
        
    }
	
	
	
	

}
