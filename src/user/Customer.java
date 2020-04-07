package user;

import java.util.Date;

public class Customer {
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private Boolean isMember;

	public Customer(String firstName, String lastName, Date dateOfBirth, Boolean isMember) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.isMember = isMember;
	}
}
