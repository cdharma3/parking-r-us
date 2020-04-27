package user;

import java.util.Date;

public class Customer {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private Boolean isMember;

	public Customer(String username, String password, String firstName, String lastName, Date dateOfBirth, Boolean isMember) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.isMember = isMember;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getIsMember() {
		return this.isMember;
	}

	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}

	@Override
	public String toString() {
		String str = "Customer " + this.firstName + " " + this.lastName;
		return str;
	}
}
