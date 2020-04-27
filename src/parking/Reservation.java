package parking;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Reservation {
	private String R_ID; // unique id used to reference reservation, only populate with database values
	private String P_ID; // p_id tied to parking lot
	private String parkingAddress; // address tied to p_id
	private String username; // username of user tied to reservation
	private String licensePlate; // license plate to be assigned to space
	private float hourlyRate; // parking lot hourly rate
	private Date startTime; // start time for reservation
	private Date endTime; // end time for reservation
	private float numHours; // calculated number of hours for reservation
	private String totalSum; // total amount of money to reserver a spot

	public Reservation() {
		this.R_ID = "";
		this.P_ID = "";
		this.username = "";
		this.licensePlate = "";
		this.hourlyRate = 0;
		this.startTime = null;
		this.endTime = null;
		this.numHours = 0;
		this.totalSum = "";
	}

	public Reservation(String P_ID, String username, String licensePlate, float hourlyRate, Date startTime, Date endTime) {
		this.P_ID = P_ID;
		this.username = username;
		this.licensePlate = licensePlate;
		this.hourlyRate = hourlyRate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.calculateTotalSum();
	}

	public String getR_ID() {
		return this.R_ID;
	}

	public void setR_ID(String R_ID) {
		this.R_ID = R_ID;
	}

	public String getP_ID() {
		return this.P_ID;
	}

	public void setP_ID(String P_ID) {
		this.P_ID = P_ID;
	}

	public String getParkingAddress() {
		return this.parkingAddress;
	}

	public void setParkingAddress(String parkingAddress) {
		this.parkingAddress = parkingAddress;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLicensePlate() {
		return this.licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public float getHourlyRate() {
		return this.hourlyRate;
	}

	public void setHourlyRate(float hourlyRate) {
		this.hourlyRate = hourlyRate;

		// update total sum and numHours
		this.calculateTotalSum();
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;

		// update total sum and numHours
		this.calculateTotalSum();
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;

		// update total sum and numHours
		this.calculateTotalSum();
	}

	public float getNumHours() {
		return this.numHours;
	}

	public String getTotalSum() {
		return this.totalSum;
	}

	// updates sum and hours whenever parameters are changed
	public void calculateTotalSum() {
		if (this.endTime != null && this.startTime != null) {
			NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
			long difference = this.endTime.getTime() - this.startTime.getTime();
			long diffHours = difference / (60 * 60 * 1000);
			this.numHours = diffHours;
			this.totalSum = money.format(this.numHours * this.hourlyRate);
		}
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy kk:mm");
		String str =
				"\nReservation at lot located at " + this.parkingAddress +
				"\nTime slot: " + dateFormat.format(this.startTime) + " - " + dateFormat.format(this.endTime) +
				"\nHourly Rate: " + this.hourlyRate +
				"\nAmount of hours: " + this.numHours +
				"\nTotal Cost: " + this.totalSum + "\n\n";
		return str;
	}

}
