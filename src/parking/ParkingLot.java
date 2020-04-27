package parking;

public class ParkingLot {
	private String p_id;
	private String address;
	private int openSpots;
	private int reservedSpots;
	private int memberSpots;

	// default constructor
	public ParkingLot() {

	}

	public ParkingLot(String p_id, String address, int openSpots, int reservedSpots, int memberSpots) {
		this.p_id = p_id;
		this.address = address;
		this.reservedSpots = reservedSpots;
		this.openSpots = openSpots;
		this.memberSpots = memberSpots;
	}

	public String getP_id() {
		return this.p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getOpenSpots() {
		return this.openSpots;
	}

	public void setOpenSpots(int openSpots) {
		this.openSpots = openSpots;
	}

	public int getReservedSpots() {
		return this.reservedSpots;
	}

	public void setReservedSpots(int reservedSpots) {
		this.reservedSpots = reservedSpots;
	}

	public int getMemberSpots() {
		return this.memberSpots;
	}

	public void setMemberSpots(int memberSpots) {
		this.memberSpots = memberSpots;
	}

	public void reserveSpot() {
		this.openSpots--;
		this.reservedSpots++;
	}

	public void openSpot() {
		this.openSpots++;
		this.reservedSpots--;
	}

	public void reserveMemberSpot() {
		this.memberSpots--;
		this.reservedSpots++;
	}

	public void openMemberSpot() {
		this.memberSpots++;
		this.reservedSpots--;
	}
}
