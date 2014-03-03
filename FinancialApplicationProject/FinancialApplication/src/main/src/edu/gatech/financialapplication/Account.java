package edu.gatech.financialapplication;

public class Account {

	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private double balance;
	private int accountNumber;

	public Account() {
	}

	public Account(String firstname, String lastname, String username,
			double balance, int accountNumber) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
}
