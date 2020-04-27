package ui;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import parking.ParkingLot;
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
	private static final String databaseUsername = "postgres";
	private static final String databasePassword = "12345";

	/**
	 * Hashes password with given plaintext password and given salt
	 * @param password plaintext password to be hashed and salted
	 * @param salt randomly generated 32bit salt length to generate unique hashes
	 * @return returns hashed password as encoded base64 string
	 * @throws IllegalArgumentException when plaintext password is null or empty
	 */
	private static String hash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Empty passwords are not supported.");
		}
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = f.generateSecret(new PBEKeySpec(
				password.toCharArray(), salt, iterations, desiredKeyLen));
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	/**
	 * Helper function to bundle salt and hashed password together to store in database
	 * @param password plaintext password to be hashed and salted
	 * @returns salt and hashed password as encoded base64 string separated by $
	 */
	public static String getSaltedHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
		byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
		// store the salt with the password
		return Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
	}

	/** Checks whether given plaintext password corresponds to a stored salted hash of the password.
	 * @param password plaintext password to be checked against stored hash
	 * @param stored salted hash to be checked again in this format 'salt$hash'
	 * @returns returns true if plaintext password equals the stored password
	 */
	public static boolean check(String password, String stored) throws NoSuchAlgorithmException, InvalidKeySpecException {
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
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", databaseUsername, databasePassword);
		String customerInfo =
				"INSERT INTO customer "
						+ "(C_ID, Password, FirstName, LastName, DateOfBirth, isMember)"
						+ "VALUES(?, ?, ?, ?, ?, ?)";

		java.sql.Date convertedDateOfBirth = new java.sql.Date(newCustomer.getDateOfBirth().getTime());
		PreparedStatement ps = parkingDatabase.prepareStatement(customerInfo);
		int placeholder = 1;
		ps.setString(placeholder++, newCustomer.getUsername());
		try {
			ps.setString(placeholder++, getSaltedHash(newCustomer.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ps.setString(placeholder++, newCustomer.getFirstName());
		ps.setString(placeholder++, newCustomer.getLastName());
		ps.setDate(placeholder++, convertedDateOfBirth);
		ps.setBoolean(placeholder++, newCustomer.getIsMember());
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
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", databaseUsername, databasePassword);
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
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", databaseUsername, databasePassword);
		String vehicleInfo =
				"INSERT INTO vehicle "
						+ "(C_ID, LicensePlate, Model, Make)"
						+ "VALUES(?, ?, ?, ?);";
		PreparedStatement ps = parkingDatabase.prepareStatement(vehicleInfo);
		int placeholder = 1;
		ps.setString(placeholder++, customerID);
		ps.setString(placeholder++, licensePlate);
		ps.setString(placeholder++, model);
		ps.setString(placeholder++, make);
		ps.executeUpdate();
		System.out.println("Vehicle with license plate " + licensePlate + " added under user " + customerID);
	}

	/** Called when vehicle is to be deleted
	 * @throws SQLException
	 */
	public static void deleteVehicle(String customerID, String licensePlate) throws SQLException {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", databaseUsername, databasePassword);
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
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", databaseUsername, databasePassword);
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
	 * Searches for username in customer database, then checks if passwords match
	 * @throws Exception
	 */
	/* 	if match then
	 * 		allow entry to site
	 * 		return current userID
	 * 	if not match then
	 * 		refuse entry with an error message, then return
	 */
	public static boolean login(String userName, String password) throws Exception {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", databaseUsername, databasePassword);
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

	/**
	 * Adds new parking lot and generates unique id
	 * @param newParkingLot
	 * @throws SQLException
	 */
	public static void addParkingLot(ParkingLot newParkingLot) throws SQLException {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", databaseUsername, databasePassword);
		String parkingLotInfo =
				"INSERT INTO parkinglot "
						+ "(Address, ReservedSpots, OpenSpots, MemberSpots) "
						+ "VALUES (?, ?, ?, ?);";
		PreparedStatement ps = parkingDatabase.prepareStatement(parkingLotInfo);
		int placeholder = 1;
		ps.setString(placeholder++, newParkingLot.getAddress());
		ps.setInt(placeholder++, newParkingLot.getReservedSpots());
		ps.setInt(placeholder++, newParkingLot.getOpenSpots());
		ps.setInt(placeholder++, newParkingLot.getMemberSpots());
		ps.executeUpdate();
		System.out.println("New parking lot added at address " + newParkingLot.getAddress());
	}

	/**
	 * deletes parking lot based on P_ID
	 * @param P_ID
	 * @throws SQLException
	 */
	public static void deleteParkingLot(UUID P_ID) throws SQLException {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", databaseUsername, databasePassword);
		String deleteParkingLotInfo =
				"DELETE FROM parkinglot "
						+ "WHERE P_ID = ?;";
		PreparedStatement ps = parkingDatabase.prepareStatement(deleteParkingLotInfo);
		ps.setObject(1, P_ID);
		ps.executeUpdate();
		System.out.println("Parking lot with P_ID " + P_ID + " deleted from database");

	}

	/**
	 * looks up parking lot id from address
	 * @param address
	 * @return parking lot id in form of string
	 * @throws SQLException
	 */
	public static UUID getParkingLot(String address) throws SQLException {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", databaseUsername, databasePassword);
		String getParkingLotID =
				"SELECT P_ID FROM parkinglot "
						+ "WHERE address = ?;";
		PreparedStatement ps = parkingDatabase.prepareStatement(getParkingLotID);
		ps.setString(1, address);
		ResultSet parkingLotID = ps.executeQuery();
		if (parkingLotID.next()) {
			return (UUID)parkingLotID.getObject("P_ID");
		} else {
			System.out.println("No parking lot with that address found!");
			return null;
		}
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
	public static void displayParkingLot(String parkingLotID) {

	}

	/** Adds reservation to reservation database
	 * @throws SQLException
	 *
	 */
	public static void addReservation(UUID parkingLotID, Reservation newReservation) throws SQLException {
		parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", databaseUsername, databasePassword);
		String reservationInfo =
				"INSERT INTO reservation "
						+ "(P_ID, C_ID, licensePlate, hourlyRate, startDate,"
						+ " startTime, endDate, endTime, numHours, totalSum) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		// converting start and end time to date
		java.sql.Date convertedStartDate = new java.sql.Date(newReservation.getStartTime().getTime());
		java.sql.Time convertedStartTime = new java.sql.Time(newReservation.getStartTime().getTime());

		java.sql.Date convertedEndDate = new java.sql.Date(newReservation.getEndTime().getTime());
		java.sql.Time convertedEndTime = new java.sql.Time(newReservation.getEndTime().getTime());

		PreparedStatement ps = parkingDatabase.prepareStatement(reservationInfo);
		int placeHolder = 1;
		ps.setObject(placeHolder++, parkingLotID);
		ps.setString(placeHolder++, newReservation.getUsername());
		ps.setString(placeHolder++, newReservation.getLicensePlate());
		ps.setFloat(placeHolder++, newReservation.getHourlyRate());
		ps.setDate(placeHolder++, convertedStartDate);
		ps.setTime(placeHolder++, convertedStartTime);
		ps.setDate(placeHolder++, convertedEndDate);
		ps.setTime(placeHolder++, convertedEndTime);
		ps.setFloat(placeHolder++, newReservation.getNumHours());
		ps.setString(placeHolder++, newReservation.getTotalSum());
		ps.executeUpdate();
		System.out.println("Reservation added at parking lot " + parkingLotID +
				" with license plate " + newReservation.getLicensePlate());

	}

	/**
	 *
	 * @param reservationID
	 * @return
	 */
	public static Reservation showReservation(int reservationID) {
		return new Reservation(); // placeholder
	}

	public static void main(String[] args) throws Exception {
		String pattern = "MM/dd/yyyy HH:mm";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Customer bill = new Customer("bWatts", "hello123", "Bill", "Watts", format.parse("12/12/1970 05:00"), false);
		Customer bbongus = new Customer ("bBongus", "purple-goats-midnight-dancing", "Bingus", "Bongus", format.parse("01/01/2001 05:00"), false);

		UIController.deleteCustomer("bWatts");
		UIController.deleteCustomer("bBongus");

		UIController.addCustomer(bill);
		UIController.addCustomer(bbongus);

		UIController.deleteVehicle("bWatts", "A123456");
		UIController.addVehicle("bWatts", "A123456", "Sorento", "KIA");

		UIController.addMembership("bWatts", false);
		System.out.println("Login attempt for bWatts: " + Boolean.toString(login("bBongus", "purple-goats-midnight-dancing")));

		ParkingLot ABC_Lots = new ParkingLot("123 ABC St.", 15, 2, 5);
		UIController.deleteParkingLot(UIController.getParkingLot("123 ABC St."));
		UIController.addParkingLot(ABC_Lots);

		Reservation billReservation = new Reservation("123 ABC St.", "bWatts", "A123456", (float) 15.00, format.parse("04/27/2020 12:00"), format.parse("04/27/2020 15:00"));
		UIController.addReservation(UIController.getParkingLot("123 ABC St."), billReservation);
	}
}