package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import parking.Reservation;
import user.Customer;

/* Parking R Us User interface Logic 
 * User Management Service
 */

public class UIController {
	Connection parkingDatabase;
	
	/* Called when registration submit button is pushed
	 * customer object is created, then internally added to customer database
	 */
	public void addCustomer(Customer newCustomer) throws SQLException {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", "postgres", "123456");
		String customerInfo =
			"INSERT INTO customer "
			+ "(C_ID, Password, FirstName, LastName, DateOfBirth, isMember)"
			+ "VALUES(?, ?, ?, ?, ?, ?)";
				
		java.sql.Date convertedDateOfBirth = new java.sql.Date(newCustomer.getDateOfBirth().getTime());
		PreparedStatement ps = parkingDatabase.prepareStatement(customerInfo);
		ps.setString(1, newCustomer.getUsername());
		ps.setString(2, newCustomer.getPassword());
		ps.setString(3, newCustomer.getFirstName());
		ps.setString(4, newCustomer.getLastName());
		ps.setDate(5, convertedDateOfBirth);
		ps.setBoolean(6, newCustomer.getIsMember());
		ps.executeUpdate();
		System.out.println("Customer " + newCustomer.getUsername() + " inserted into customer database!");
	}
	
	public void deleteCustomer(String customerID) throws SQLException {
		/* Called when delete profile button is pushed
		 * Retrieves customerID from current user session
		 * Deletes customer information from customer database
		 * Calls deleteVehicles method to delete vehicles associated with customer from database
		 * Calls deleteReservations method to delete reservations made by customer from database
		 */
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", "postgres", "123456");
		String deleteCustomerStatement = "DELETE FROM customer WHERE C_ID = ?";
		PreparedStatement deleteCustomerPS = parkingDatabase.prepareStatement(deleteCustomerStatement);
		deleteCustomerPS.setString(1, customerID);
		deleteCustomerPS.executeUpdate();
		System.out.println("Customer " + customerID + " deleted from customer database!");
	}

	public void addVehicle(String customerID, String licensePlate, String model, String make) throws SQLException {
		/* Called when new vehicle submit button is pushed
		 * Vehicle newVehicle = new Vehicle(licensePlate, make, model);
		 * vehicle object is created, then internally added to vehicle database 
		 * with associated customerID
		 */
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", "postgres", "123456");
		String vehicleInfo =
			"INSERT INTO vehicle "
			+ "(C_ID, LicensePlate, Model, Make)"
			+ "VALUES(?, ?, ?, ?);";
		PreparedStatement ps = parkingDatabase.prepareStatement(vehicleInfo);
		ps.setString(1, customerID);
		ps.setString(2, licensePlate);
		ps.setString(3, model);
		ps.setString(4, make);
		ps.executeUpdate();
		System.out.println("Vehicle with license plate " + licensePlate + " added under user " + customerID);
	}

	public void addMembership(String customerID, Boolean isMonthly) {
		/* Called when logged in has signed up for a membership
		 * Can be certain types of membership payments, monthly or yearly
		 * Set up monthly or annual billing depending on membership type
		 * update membership flag in customer database for customerID
		 */
	}

	public void displayParkingLot(int parkingLotID) {
		/* Called when parking information is looked up
		 * prints to main body open parking lot spaces available
		 * 
		 * if user is a member then 
		 * 		print open parking lot spaces + member only spaces
		 * else 
		 * 		only print non-member space availability
		 * 
		 * prints parking lot location
		 * prints timeslots with available parking spaces
		 * prints parking lot costs
		 */
	}

	public int login(String userName, String password) {
		/* Called when login submit button is pushed
		 * 
		 * Searches for username in customer database, then checks if passwords match
		 * 		if match then
		 * 			allow entry to site
		 * 			return current userID
		 * 		if not match then
		 * 			refuse entry with an error message, then return
		 * 
		 * when entry is allowed
		 * 		display user information in box, set as default home screen
		 * 		display active reservations and times
		 * 		display button to edit reservation
		 * 		display button to navigate to creating new reservation
		 */
		return 0;
	}

	public Reservation showReservation(int reservationID) {
		/* retrieves reservation from database using unique id, then passes information to UI
		 */
		return new Reservation(); // placeholder
	}
	
	public static void main(String[] args) throws ParseException, SQLException { 
		String pattern = "MM/dd/yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
		Customer bill = new Customer("bWatts", "123456", "Bill", "Watts", format.parse("12/12/1970"), false);
		UIController mainController = new UIController();
		mainController.addCustomer(bill);
		mainController.addVehicle("bWatts", "A123456", "Sorento", "KIA");
	}
}