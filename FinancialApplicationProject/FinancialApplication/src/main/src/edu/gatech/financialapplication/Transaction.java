package edu.gatech.financialapplication;

public class Transaction {

	private int accountTo;
	private int accountFrom;
	private String date;
	private double amount;
	private String description;
	
	public Transaction(int accountTo, int accountFrom, String date,
			double amount, String description) {
		this.accountTo = accountTo;
		this.accountFrom = accountFrom;
		this.date = date;
		this.amount = amount;
		this.description = description;
	}



	public int getAccountTo() {
		return accountTo;
	}
	
	public String getDescription() {
		return description;
	}

	public void setAccount_to(int accountTo) {
		this.accountTo = accountTo;
	}

	public int getAccountFrom() {
		return accountFrom;
	}

	public void setAccount_from(int accountFrom) {
		this.accountFrom = accountFrom;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setdescription(String description) {
		this.description = description;
	}
}
