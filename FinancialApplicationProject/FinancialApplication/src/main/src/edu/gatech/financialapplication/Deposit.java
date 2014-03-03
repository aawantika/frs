package edu.gatech.financialapplication;

public class Transaction {

	private Account accountTo;
	private String descriptor;
	private String date;
	private float amount;

	public Deposit(String accountTo, String descriptor,
	          String date, float amount) {
		this.accountTo = accountTo;
		this.descriptor = descriptor;
		this.date = date;
		this.amount = amount;
	}

	public String getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(String accountTo) {
		this.accountTo = accountTo;
	}
	
	public String getDescriptor() {
	  return descriptor;
	}
	
	public void setDescriptor(String descriptor) {
	  this.descriptor = descriptor;
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
	
	@Override
	public String toString() {
		System.out.println("Amount: " + amount + "\nTransferred To: " + accountTo +
		            "\nOn Date: " + data + "\nDescriptor: " + descriptor);
	}
}
