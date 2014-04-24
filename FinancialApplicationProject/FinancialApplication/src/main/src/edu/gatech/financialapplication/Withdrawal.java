package edu.gatech.financialapplication;

/**
 * This class withdraws money from account.
 * 
 * @author Team 15
 */
public class Withdrawal extends Transaction {

    /**
     * Modified Constructor: takes in the transaction's information and
     * withdraws appropriate amount of money.
     * 
     * @param aAccount is the account where the transaction is being made.
     * @param aDate is the date of the transaction.
     * @param aAmount is the amount of money being transacted.
     * @param aDescriptor is the type of transaction being made.
     * @param aCategory is the category under which the transaction is being made.
     */
    public Withdrawal(String account, String date, float amount,
            String descriptor, String category) {
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