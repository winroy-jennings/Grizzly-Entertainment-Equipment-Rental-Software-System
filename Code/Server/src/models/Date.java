package models;

public class Date {
	// Date attributes
	int day;
	int month;
	int year;

	public Date() {
		day = 12;
		month = 12;
		year = 2023;
	}

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public Date(Date Date) {
		this.day = Date.day;
		this.month = Date.month;
		this.year = Date.year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "date [day=" + day + ", month=" + month + ", year=" + year + "]";
	}
}
