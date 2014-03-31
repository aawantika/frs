package edu.gatech.financialapplication;

/**
 * This class holds all the information for a User's account.
 * 
 * @author Team 15
 */
public class Account {

    /**
     * id instance variable.
     */
    private int id;
    /**
     * first name instance variable.
     */
    private String firstname;
    /**
     * last name instance variable.
     */
    private String lastname;
    /**
     * username instance variable.
     */
    private String username;
    /**
     * balance instance variable.
     */
    private String balance;
    /**
     * account number instance variable.
     */
    private String accountNumber;

    /**
     * Default Constructor.
     */
    public Account() {
    }

    /**
     * Modified Constructor: takes in the users information and assigns it to
     * the account.
     * 
     * @param aFirstname is User's first name.
     * @param aLastname is User's last name.
     * @param aUsername is User's username.
     * @param aBalance is User's balance.
     * @param aAccountNumber is User's account number.
     */
    public Account(String aFirstname, String aLastname, String aUsername,
            String aBalance, String aAccountNumber) {
        this.firstname = aFirstname;
        this.lastname = aLastname;
        this.username = aUsername;
        this.balance = aBalance;
        this.accountNumber = aAccountNumber;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", firstname=" + firstname + ","
                + "lastname=" + lastname + ", username=" + username + ","
                + "balance=" + balance + ", accountNumber=" + accountNumber
                + "]";
    }

    /**
     * Used for debugging code.
     * 
     * @return String: returns username, account number, and balance for
     *         debugging.
     */
    public String debug() {
        return "Username: " + username + "\nAccount: " + accountNumber
                + "\nBalance: " + balance;
    }

    /**
     * Gets the id of the account.
     * 
     * @return int gets the id of account.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a new id of the account.
     * 
     * @param aId sets the id of the account.
     */
    public void setId(int aId) {
        this.id = aId;
    }

    /**
     * Gets the first name of the user.
     * 
     * @return String gets the first name of user.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets a new first name for user.
     * 
     * @param aFirstname sets the first name of the user.
     */
    public void setFirstname(String aFirstname) {
        this.firstname = aFirstname;
    }

    /**
     * Gets the last name of the user.
     * 
     * @return String gets the last name of user.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets a new last name for user.
     * 
     * @param aLastname sets the last name of the user.
     */
    public void setLastname(String aLastname) {
        this.lastname = aLastname;
    }

    /**
     * Gets the username of the user.
     * 
     * @return String gets the username of user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets a new username for user.
     * 
     * @param aUsername sets the username of the user.
     */
    public void setUsername(String aUsername) {
        this.username = aUsername;
    }

    /**
     * Gets the get balance of the account.
     * 
     * @return String gets the balance of account.
     */
    public String getBalance() {
        return balance;
    }

    /**
     * Sets a new balance for account.
     * 
     * @param aBalance sets the balance of the account.
     */
    public void setBalance(String aBalance) {
        this.balance = aBalance;
    }

    /**
     * Gets the account number of the account.
     * 
     * @return String gets the account number of account.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets a new account number for account.
     * 
     * @param aAccountNumber sets the account number of the account.
     */
    public void setAccountNumber(String aAccountNumber) {
        this.accountNumber = aAccountNumber;
    }

}
