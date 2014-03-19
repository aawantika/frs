package edu.gatech.financialapplication;

public class User {

	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;

	public User() {}
	
	public User(String firstname, String lastname, String username,
			String password, String email) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname
				+ ", username=" + username + ", password=" + password
				+ ", email=" + email + "]";
	}
}
