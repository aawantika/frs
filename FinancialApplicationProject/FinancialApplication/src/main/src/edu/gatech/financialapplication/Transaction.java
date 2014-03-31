package edu.gatech.financialapplication;

/**
 * This class does the transactions from an account.
 * 
 * @author Prav Tadikonda
 */
public class Transaction {

    /**
     * Account instance variable.
     */
    private String account;
    /**
     * Date instance variable.
     */
    private String date;
    /**
     * Amount instance variable.
     */
    private float amount;
    /**
     * Description instance variable.
     */
    private String description;
    /**
     * Category instance variable.
     */
    private String category;
    /**
     * Type instance variable.
     */
    private String type;

    /**
     * Modified Constructor: takes in the transaction's information and either
     * deposits/withdraws appropriate amount of money.
     * 
     * @param aAccount the account where the transaction will be made.
     * @param aDate the date the transaction will be made.
     * @param aAmount the amount of money being transacted.
     * @param aDescription the type of transaction being made.
     * @param aCategory the category under which the transaction is being made.
     * @param aType is either withdrawal or deposit transaction
     */
    public Transaction(String aAccount, String aDate, float aAmount,
            String aDescription, String aCategory, String aType) {
        this.account = aAccount;
        this.date = aDate;
        this.amount = aAmount;
        this.description = aDescription;
        this.category = aCategory;
        this.type = aType;
    }

    @Override
    public String toString() {
        return "Transaction [account=" + account + ", date=" + date
                + ", amount=" + amount + ", description=" + description
                + ", category=" + category + "]";
    }

    /**
     * Gets the account of the transaction.
     * 
     * @return String is the account where the transaction will be made.
     */
    public String getAccount() {
        return account;
    }

    /**
     * Sets a new account for transaction.
     * 
     * @param aAccount sets the account where the transaction will be made.
     */
    public void setAccount(String aAccount) {
        this.account = aAccount;
    }

    /**
     * Gets the date of the transaction.
     * 
     * @return String gets the date of transaction
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets a new date for transaction.
     * 
     * @param aDate sets the date of transaction.
     */
    public void setDate(String aDate) {
        this.date = aDate;
    }

    /**
     * Gets the amount of the transaction.
     * 
     * @return float gets the amount of transaction
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets a new amount for transaction.
     * 
     * @param aAmount sets the amount of transaction.
     */
    public void setAmount(float aAmount) {
        this.amount = aAmount;
    }

    /**
     * Gets the description of the transaction.
     * 
     * @return String gets the description of transaction
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a new description for transaction.
     * 
     * @param aDescription sets the description of transaction.
     */
    public void setdescription(String aDescription) {
        this.description = aDescription;
    }

    /**
     * Gets the category of the transaction.
     * 
     * @return String gets the category of transaction
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets a new category for transaction.
     * 
     * @param aCategory sets the category of transaction.
     */
    public void setCategory(String aCategory) {
        this.category = aCategory;
    }

    /**
     * Gets the type of the transaction.
     * 
     * @return String gets the type of transaction
     */
    public String getType() {
        return type;
    }

    /**
     * Sets a new type for transaction.
     * 
     * @param aType sets the type of transaction.
     */
    public void setType(String aType) {
        this.type = aType;
    }
}
