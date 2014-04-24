package edu.gatech.financialapplication;

/**
 * This class holds all the information for a User's account.
 * 
 * @author Team 15
 */
public class Account {

    private String firstname, lastname, username, balance, accountNumber, initialBalance;

    /**
     * Default empty Constructor.
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
    public Account(String firstname, String lastname, String username,
            String balance, String accountNumber, String initialBalance) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.initialBalance = initialBalance;
    }

	@Override
	public String toString() {
		return "Account [firstname=" + firstname + ", lastname=" + lastname
				+ ", username=" + username + ", balance=" + balance
				+ ", accountNumber=" + accountNumber + ", initialBalance="
				+ initialBalance + "]";
	}

	// used for the listview adapters
    public String debug() {
        return "Username: " + username + "\nAccount: " + accountNumber
                + "\nBalance: " + balance;
    }

    public String getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(String initialBalance) {
		this.initialBalance = initialBalance;
	}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String aFirstname) {
        this.firstname = aFirstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String aLastname) {
        this.lastname = aLastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String aUsername) {
        this.username = aUsername;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String aBalance) {
        this.balance = aBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String aAccountNumber) {
        this.accountNumber = aAccountNumber;
    }

}
