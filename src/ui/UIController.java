package ui;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import parking.Reservation;
import user.Customer;


/** Parking R Us User interface Logic
 * User Management Service
 */

public class UIController {
	private static Connection parkingDatabase;
	private static final int iterations = 20*1000;
	private static final int saltLen = 32;
	private static final int desiredKeyLen = 256;

	/**
	 * Hashes password with given plaintext password and given salt
	 */
	private static String hash(String password, byte[] salt) throws Exception {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Empty passwords are not supported.");
		}
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = f.generateSecret(new PBEKeySpec(
				password.toCharArray(), salt, iterations, desiredKeyLen));
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	/**
	 * Helper function to hash and salt password with PBKDF2
	 * returns salt and hashed password separated by $
	 * @throws Exception
	 */
	public static String getSaltedHash(String password) throws Exception {
		byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
		// store the salt with the password
		return Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
	}

	/** Checks whether given plaintext password corresponds
    to a stored salted hash of the password. */
	public static boolean check(String password, String stored) throws Exception{
		String[] saltAndHash = stored.split("\\$");
		if (saltAndHash.length != 2) {
			throw new IllegalStateException(
					"The stored password must have the form 'salt$hash'");
		}
		String hashOfInput = hash(password, Base64.getDecoder().decode((saltAndHash[0])));
		return hashOfInput.equals(saltAndHash[1]);
	}

	/** Called when registration submit button is pushed
	 * customer object is created, then internally added to customer database
	 * @throws SQLException
	 */
	public static void addCustomer(Customer newCustomer) throws SQLException {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", "postgres", "123456");
		String customerInfo =
				"INSERT INTO customer "
						+ "(C_ID, Password, FirstName, LastName, DateOfBirth, isMember)"
						+ "VALUES(?, ?, ?, ?, ?, ?)";

		java.sql.Date convertedDateOfBirth = new java.sql.Date(newCustomer.getDateOfBirth().getTime());
		PreparedStatement ps = parkingDatabase.prepareStatement(customerInfo);
		ps.setString(1, newCustomer.getUsername());
		try {
			ps.setString(2, getSaltedHash(newCustomer.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ps.setString(3, newCustomer.getFirstName());
		ps.setString(4, newCustomer.getLastName());
		ps.setDate(5, convertedDateOfBirth);
		ps.setBoolean(6, newCustomer.getIsMember());
		ps.executeUpdate();
		System.out.println("Customer " + newCustomer.getUsername() + " inserted into customer database!");

	}

	/** Called when delete profile button is pushed
	 * Retrieves customerID from current user session
	 * Deletes vehicles associated with customer from database
	 * Deletes customer information from customer database
	 * Calls deleteVehicles method to delete vehicles associated with customer from database
	 * Calls deleteReservations method to delete reservations made by customer from database
	 * @throws SQLException
	 */
	public static void deleteCustomer(String customerID) throws SQLException {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", "postgres", "123456");
		// delete vehicles
		String deleteCustomerVehicles = "DELETE FROM vehicle WHERE C_ID = ?";
		PreparedStatement deleteVehiclePS = parkingDatabase.prepareStatement(deleteCustomerVehicles);
		deleteVehiclePS.setString(1, customerID);
		deleteVehiclePS.executeUpdate();

		// delete customer information
		String deleteCustomerStatement = "DELETE FROM customer WHERE C_ID = ?";
		PreparedStatement deleteCustomerPS = parkingDatabase.prepareStatement(deleteCustomerStatement);
		deleteCustomerPS.setString(1, customerID);
		deleteCustomerPS.executeUpdate();
		System.out.println("Customer " + customerID + " deleted from customer database!");
	}

	/** Called when new vehicle submit button is pushed
	 * Vehicle newVehicle = new Vehicle(licensePlate, make, model);
	 * vehicle object is created, then internally added to vehicle database
	 * with associated customerID
	 * @throws SQLException
	 */
	public static void addVehicle(String customerID, String licensePlate, String model, String make) throws SQLException {
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

	/** Called when vehicle is to be deleted
	 * @throws SQLException
	 */
	public static void deleteVehicle(String customerID, String licensePlate) throws SQLException {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", "postgres", "123456");
		String deleteVehicle =
				"DELETE FROM vehicle "
						+ "WHERE C_ID = ? "
						+ "AND licensePlate = ?;";
		PreparedStatement ps = parkingDatabase.prepareStatement(deleteVehicle);
		ps.setString(1, customerID);
		ps.setString(2, licensePlate);
		ps.executeUpdate();
		System.out.println("User " + customerID + "'s vehicle with license plate " + licensePlate + " has been deleted");
	}

	/** Called when logged in has signed up for a membership
	 * Can be certain types of membership payments, monthly or yearly
	 * Set up monthly or annual billing depending on membership type
	 * update membership flag in customer database for customerID
	 * @throws SQLException
	 */
	public static void addMembership(String customerID, Boolean isMember) throws SQLException {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", "postgres", "123456");
		String addMember =
				"UPDATE customer "
						+ "SET isMember = ?"
						+ "WHERE C_ID = ?";
		PreparedStatement ps = parkingDatabase.prepareStatement(addMember);
		ps.setBoolean(1, isMember);
		ps.setString(2, customerID);
		ps.executeUpdate();
		System.out.println("User " + customerID + "'s isMember value has been set to " + Boolean.toString(isMember));
	}

	/** Called when login submit button is pushed
	 * @throws Exception
	 */
	/* Searches for username in customer database, then checks if passwords match
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
	public static boolean login(String userName, String password) throws Exception {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", "postgres", "123456");
		String passwordQuery =
				"SELECT password "
						+ "FROM customer "
						+ "WHERE C_ID = ?;";
		PreparedStatement passwordStatement = parkingDatabase.prepareStatement(passwordQuery);
		passwordStatement.setString(1, userName);
		ResultSet storedPassword = passwordStatement.executeQuery();
		storedPassword.next();
		return check(password, storedPassword.getString("password"));

	}

	/** Called when parking information is looked up
	 * prints to main body open parking lot spaces available
	 */
	/* if user is a member then
	 * 		print open parking lot spaces + member only spaces
	 * else
	 * 		only print non-member space availability
	 *
	 * prints parking lot location
	 * prints timeslots with available parking spaces
	 * prints parking lot costs
	 */
	public static void displayParkingLot(int parkingLotID) {

	}


	public static Reservation showReservation(int reservationID) {
		/* retrieves reservation from database using unique id, then passes information to UI
		 */
		return new Reservation(); // placeholder
	}

	public static void main(String[] args) throws Exception {
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Customer bill = new Customer("bWatts", "hello123", "Bill", "Watts", format.parse("12/12/1970"), false);
		UIController.deleteCustomer("bWatts");
		UIController.addCustomer(bill);
		UIController.addVehicle("bWatts", "A123456", "Sorento", "KIA");
		UIController.deleteVehicle("bWatts", "A123456");
		UIController.addMembership("bWatts", false);
		System.out.println("Login attempt for bWatts: " + Boolean.toString(login("bWatts", "hello123")));
	}
}