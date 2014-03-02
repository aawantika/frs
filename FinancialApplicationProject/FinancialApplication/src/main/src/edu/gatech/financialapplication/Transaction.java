package edu.gatech.financialapplication;

import java.util.Date;

public class Transaction {

	private int id;
	private String username;
	private String account_to;
	private String account_from;
	private Date date;
	private double amount;

	public Transaction(int id, String username, String account_to,
			String account_from, Date date, double amount) {
		this.id = id;
		this.username = username;
		this.account_to = account_to;
		this.account_from = account_from;
		this.date = date;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount_to() {
		return account_to;
	}

	public void setAccount_to(String account_to) {
		this.account_to = account_to;
	}

	public String getAccount_from() {
		return account_from;
	}

	public void setAccount_from(String account_from) {
		this.account_from = account_from;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
