package parking;

import java.util.ArrayList;

public class ParkingLot {
	private Double timeStamp;
	private ArrayList openSpots;
	private ArrayList reservedSpots;
	private ArrayList memberSpots;

	public ParkingLot(int parkingLotID) {
		/*
		 * Pull parking lot information from database then
		 * instantiate parking lot object with said information
		 * Also pull timestamp from current time to maintain parkinglot 
		 * accuracy
		 */
	}

	public void freeSpot(int spotID) {
		/*
		 * remove spot from reservedSpots list and add to openSpots list
		 * or add to memberSpots list of memberspot
		 */
	}

	public void scheduleTime(int reservationID) {
		/*
		 * add reservation to time slots and move open spots
		 * to reserved spots for time stamp
		 */
	}
}
