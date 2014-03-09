package edu.gatech.financialapplication;

public class Account {

	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String balance;
	private String accountNumber;

	public Account() {
	}

	public Account(String firstname, String lastname, String username,
			String balance, String accountNumber) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", username=" + username + ", balance=" + balance
				+ ", accountNumber=" + accountNumber + "]";
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

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
