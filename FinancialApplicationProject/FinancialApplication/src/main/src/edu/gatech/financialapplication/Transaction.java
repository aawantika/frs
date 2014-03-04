package edu.gatech.financialapplication;

public class Transaction {

	private int accountTo;
	private int accountFrom;
	private String date;
	private float amount;
	private String description;
	private String category;
	
	public Transaction(int accountTo, int accountFrom, String date,
			float amount, String description, String category) {
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public void setdescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
