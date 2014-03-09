package edu.gatech.financialapplication;

public class Withdrawal extends Transaction {

	public Withdrawal(String account, String date, float amount, String descriptor,
			String category) {
		super(account, date, amount, descriptor, category, "withdrawal");
	}

	@Override
	public String toString() {
		return "Amount: " + this.getAmount() + "\nAccount From: "
				+ this.getAccount() + "\nOn Date: " + this.getDate()
				+ "\nDescriptor: " + this.getDescription() + "\nCategory: "
				+ this.getCategory();
	}
}