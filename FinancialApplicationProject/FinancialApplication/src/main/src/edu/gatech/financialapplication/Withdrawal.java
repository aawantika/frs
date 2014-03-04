package edu.gatech.financialapplication;

public class Withdrawal extends Transaction {

	public Withdrawal(int accountTo, int accountFrom, float amount,
			String descriptor, String category) {
		super(accountTo, accountFrom, "", amount, descriptor, category);
	}

	@Override
	public String toString() {
		return "Amount: " + this.getAmount() + "\nFrom: "
				+ this.getAccountFrom() + "\nTransferred To: "
				+ this.getAccountTo() + "\nOn Date: " + this.getDate()
				+ "\nDescriptor: " + this.getDescription() + "\nCategory: "
				+ this.getCategory();
	}
}