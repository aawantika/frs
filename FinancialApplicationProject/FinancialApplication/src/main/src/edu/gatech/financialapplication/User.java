package edu.gatech.financialapplication;

/**
 * This class hold information of user.
 * 
 * @author Team 15
 */
public class User {

    /**
     * First name instance variable.
     */
    private String firstname;
    /**
     * Last name instance variable.
     */
    private String lastname;
    /**
     * Username instance variable.
     */
    private String username;
    /**
     * Password instance variable.
     */
    private String password;
    /**
     * E-mail instance variable.
     */
    private String email;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Modified Constructor: Creates the user with all the given information.
     * 
     * @param aFirstname first name of User.
     * @param aLastname last name of User.
     * @param aUsername username of User.
     * @param aPassword password of User.
     * @param aEmail email name of User.
     */
    public User(String aFirstname, String aLastname, String aUsername,
            String aPassword, String aEmail) {
        this.firstname = aFirstname;
        this.lastname = aLastname;
        this.username = aUsername;
        this.password = aPassword;
        this.email = aEmail;
    }

    /**
     * Gets the first name of the user.
     * 
     * @return String gets the first name of the user.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets a new first name for user.
     * 
     * @param aFirstname sets the first name of user.
     */
    public void setFirstname(String aFirstname) {
        this.firstname = aFirstname;
    }

    /**
     * Gets the last name of the user.
     * 
     * @return String gets the last name of the user.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets a new last name for user.
     * 
     * @param aLastname sets the last name of user.
     */
    public void setLastname(String aLastname) {
        this.lastname = aLastname;
    }

    /**
     * Gets the username of the user.
     * 
     * @return String gets the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets a new username for user.
     * 
     * @param aUsername sets the username of user.
     */
    public void setUsername(String aUsername) {
        this.username = aUsername;
    }

    /**
     * Gets the password of the user.
     * 
     * @return String gets the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets a new password for user.
     * 
     * @param aPassword sets the password of user.
     */
    public void setPassword(String aPassword) {
        this.password = aPassword;
    }

    /**
     * Gets the email of the user.
     * 
     * @return String gets the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets a new email for user.
     * 
     * @param aEmail sets the email of user.
     */
    public void setEmail(String aEmail) {
        this.email = aEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User that = (User) o;
            return (username.equalsIgnoreCase(that.username) && password
                    .equalsIgnoreCase(that.password));
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "User [firstname=" + firstname + ", lastname=" + lastname
                + ", username=" + username + ", password=" + password
                + ", email=" + email + "]";
    }
}
