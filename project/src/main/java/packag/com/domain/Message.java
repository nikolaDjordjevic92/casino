package packag.com.domain;

import java.util.Date;

public class Message {
	private String welcome;
	private Date date;
	private String dateString;
	
	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public Message(String welcome) {
		super();
		this.welcome = welcome;
		date = new Date();
	}
	 @Override
	public String toString() {
		// TODO Auto-generated method stub
		return welcome;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
}
