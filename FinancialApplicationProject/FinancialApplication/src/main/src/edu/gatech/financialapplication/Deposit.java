package edu.gatech.financialapplication;

public class Deposit extends Transaction {

	public Deposit(String account, String date, float amount, String descriptor) {
		super(account, date, amount, descriptor, "something", "deposit");
		
	}

	@Override
	public String toString() {
		return ("Amount: " + this.getAmount() + "\nAccountTo: "
				+ this.getAccount() + "\nOn Date: " + this.getDate()
				+ "\nDescriptor: " + this.getDescription());
	}
}
