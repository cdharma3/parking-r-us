package parking;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class Reservation {
	private String parkingAddress; // address tied to parking lot, use to calculate p_id
	private String licensePlate; // license plate to be assigned to space
	private float hourlyRate; // parking lot hourly rate
	private Date startTime; // start time for reservation
	private Date endTime; // end time for reservation
	private float numHours; // calculated number of hours for reservation
	private String totalSum; // total amount of money to reserver a spot

	public Reservation() {
		this.parkingAddress = "";
		this.licensePlate = "";
		this.hourlyRate = 0;
		this.startTime = null;
		this.endTime = null;
		this.numHours = 0;
		this.totalSum = "";
	}

	public Reservation(String parkingAddress, String licensePlate, float hourlyRate, Date startTime, Date endTime) {
		this.parkingAddress = parkingAddress;
		this.licensePlate = licensePlate;
		this.hourlyRate = hourlyRate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.calculateTotalSum();
	}

	public String getParkingAddress() {
		return this.parkingAddress;
	}

	public void setParkingAddress(String parkingAddress) {
		this.parkingAddress = parkingAddress;
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

	public void setHourlyRate(int hourlyRate) {
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
		NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
		long difference = this.endTime.getTime() - this.startTime.getTime();
		long diffHours = difference / (60 * 60 * 1000);
		System.out.println(diffHours);
		this.numHours = diffHours;
		this.totalSum = money.format(this.numHours * this.hourlyRate);
	}


}
