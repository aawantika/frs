package edu.gatech.financialapplication;

public class Withdrawal {

	private Account accountFrom;
	private String descriptor;
	private String date;
	private float amount;

	public Withdrawal(Account accountFrom, String descriptor,
	          String date, float amount) {
		this.accountFrom = accountFrom;
		this.descriptor = descriptor;
		this.date = date;
		this.amount = amount;
	}

	public String getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(String accountFrom) {
		this.accountFrom = accountFrom;
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
		return "Amount: " + amount + "\nTransferred To: " + accountTo
		                  + "\nOn Date: " + data + "\nDescriptor: " + descriptor;
	}
}
