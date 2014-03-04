package edu.gatech.financialapplication;

public class Deposit extends Transaction {

	public Deposit(int accountTo, float amount, String descriptor) {
		super(accountTo, 0, "", amount, descriptor, "");
	}

	@Override
	public String toString() {
		return ("Amount: " + this.getAmount() + "\nTransferred To: "
				+ this.getAccountTo() + "\nOn Date: " + this.getDate()
				+ "\nDescriptor: " + this.getDescription());
	}
}
