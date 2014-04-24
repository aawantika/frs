package edu.gatech.financialapplication;

/**
 * This class deposits money from account.
 * 
 * @author Team 15
 */
public class Deposit extends Transaction {

    /**
     * Modified Constructor: takes in the transaction's information and deposits
     * appropriate amount of money.
     * @param aAccount is the account where the transaction is being made.
     * @param aDate is the date of the transaction.
     * @param aAmount is the amount of money being transacted.
     * @param aDescriptor is the type of transaction being made.
     */
    public Deposit(String account, String date, float amount, String descriptor) {
        super(account, date, amount, descriptor, "something", "deposit");

    }

    @Override
    public String toString() {
        return "Amount: " + this.getAmount() + "\nAccountTo: "
                + this.getAccount() + "\nOn Date: " + this.getDate()
                + "\nDescriptor: " + this.getDescription();
    }
}
