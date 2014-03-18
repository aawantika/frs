package edu.gatech.financialapplication;

public class Withdrawal extends Transaction {

	public Withdrawal(String account, String date, float amount, String descriptor,
			String category) {
		super(account, date, amount, descriptor, category, "withdrawal");
	}

	@Override
	public String toString() {
		return "Withdrawal [toString()=" + super.toString() + ", getAccount()="
				+ getAccount() + ", getDate()=" + getDate() + ", getAmount()="
				+ getAmount() + ", getDescription()=" + getDescription()
				+ ", getCategory()=" + getCategory() + ", getType()="
				+ getType() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
}