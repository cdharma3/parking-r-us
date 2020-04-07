package ui;

import java.util.Date;

import parking.Reservation;

/* Parking R Us User interface Logic 
 * User Management Service
 */

public class UIController {
	public void addCustomer(String userName, String password, String firstName, String lastName, Date dateOfBirth) {
		/* Called when registration submit button is pushed
		 * Customer newCustomer = new Customer(firstName, lastName, dateOfBirth);
		 * customer object is created, then internally added to customer database
		 */
	}

	public void deleteCustomer(String customerID) {
		/* Called when delete profile button is pushed
		 * Retrieves customerID from current user session
		 * Deletes customer information from customer database
		 * Calls deleteVehicles method to delete vehicles associated with customer from database
		 * Calls deleteReservations method to delete reservations made by customer from database
		 */
	}

	public void addVehicle(String customerID, String licensePlate, String model, String make) {
		/* Called when new vehicle submit button is pushed
		 * Vehicle newVehicle = new Vehicle(licensePlate, make, model);
		 * vehicle object is created, then internally added to vehicle database 
		 * with associated customerID
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
}