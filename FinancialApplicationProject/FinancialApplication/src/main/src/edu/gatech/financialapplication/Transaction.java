package edu.gatech.financialapplication;

public class Transaction {

	private int accountNumber;
	private String accountTo;
	private String accountFrom;
	private String date;
	private double amount;

	public Transaction(String accountTo, String accountFrom, String date,
			double amount, int accountNumber) {
		this.accountTo = accountTo;
		this.accountFrom = accountFrom;
		this.date = date;
		this.amount = amount;
		this.accountNumber = accountNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountTo() {
		return accountTo;
	}

	public void setAccount_to(String accountTo) {
		this.accountTo = accountTo;
	}

	public String getAccountFrom() {
		return accountFrom;
	}

	public void setAccount_from(String accountFrom) {
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
}
