package edu.gatech.financialapplication;

public class Transaction {

	private String account;
	private String date;
	private float amount;
	private String description;
	private String category;
	private String type;

	public Transaction(String account, String date, float amount,
			String description, String category, String type) {
		this.account = account;
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.category = category;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Transaction [account=" + account + ", date=" + date
				+ ", amount=" + amount + ", description=" + description
				+ ", category=" + category + "]";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getDescription() {
		return description;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
