package edu.gatech.financialapplication;

public class User {

	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;

	private String email;
	private int ssn;
	private String dob;
	private int phone;
	private String accounts;

	private String address;
	private String city;
	private String state;
	private int zipcode;
	
	public User(){
		
	}

	public User(String firstname, String lastname, String username,
			String password, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;

		this.email = email;
	}

	public User(String firstname, String lastname, String username,
			String password, String email, int ssn, String dob, int phone,
			String accounts, String address, String city, String state,
			int zipcode) {

		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;

		this.email = email;
		this.ssn = ssn;
		this.dob = dob;
		this.phone = phone;
		this.accounts = accounts;

		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public boolean equals(Object o){
		if (o instanceof User) {
			User that = (User) o;
			return (username.equalsIgnoreCase(that.username) && password.equalsIgnoreCase(that.password)) ? true : false;
		}
		return false;
	}
}
