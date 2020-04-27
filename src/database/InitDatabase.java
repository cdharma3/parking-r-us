package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDatabase {

	public static void main(String[] args) {
		try {
			Connection parkingDatabase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/parking-db", "postgres", "123456");
			System.out.println("Connection established...");
			DatabaseMetaData pdbmd = parkingDatabase.getMetaData();
			ResultSet tables = pdbmd.getTables(null, null, "customer", null);
			Statement st = parkingDatabase.createStatement();

			// initialize / reset customer database
			// if customer table exists, drop
			if (tables.next()) {
				st.executeUpdate("DROP TABLE customer CASCADE;");
				System.out.println("Customer table dropped");
			}

			String customerTable =
					"CREATE TABLE customer "
							+ "(C_ID varChar(50) NOT NULL, "
							+ "Password varChar(256), "
							+ "FirstName varChar(50), "
							+ "LastName varChar(50), "
							+ "DateOfBirth DATE, "
							+ "isMember BOOLEAN, "
							+ "PRIMARY KEY (C_ID));";

			st.executeUpdate(customerTable);
			System.out.println("Customer table created");

			tables = pdbmd.getTables(null, null, "vehicle", null);
			if(tables.next()) {
				st.executeUpdate("DROP TABLE vehicle;");
				System.out.println("Vehicle table dropped");
			}

			String vehicleTable =
					"CREATE TABLE vehicle "
							+ "(C_ID varChar(50) NOT NULL, "
							+ "LicensePlate varChar(50), "
							+ "Model varChar(50), "
							+ "Make varChar(50), "
							+ "FOREIGN KEY (C_ID) REFERENCES customer(c_id));";

			st.executeUpdate(vehicleTable);
			System.out.println("Vehicle table created");

			tables = pdbmd.getTables(null, null, "reservation", null);
			if(tables.next()) {
				st.executeUpdate("DROP TABLE reservation CASCADE;");
				System.out.println("Reservation table dropped");
			}

			String reservationTable =
					"CREATE TABLE reservation "
							+ "(R_ID varChar(50) NOT NULL, "
							+ "P_ID varChar(50) NOT NULL, "
							+ "licensePlate varChar(50), "
							+ "hourlyRate NUMERIC(15, 2), "
							+ "startDate DATE, "
							+ "startTime TIME, "
							+ "endDate DATE, "
							+ "endTime TIME, "
							+ "numHours NUMERIC(15, 2), "
							+ "totalSum varChar(50), "
							+ "PRIMARY KEY (R_ID), "
							+ "FOREIGN KEY (P_ID) REFERENCES parkinglot(P_ID), "
							+ "FOREIGN KEY (licensePlate) REFERENCES vehicle(licensePlate));";

			st.executeUpdate(reservationTable);
			System.out.println("Reservation table created");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

}
