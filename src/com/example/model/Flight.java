package com.example.model;

import com.example.dao.FlightDAO;

public class Flight {
	private int flightId;
	private String flightName;
	private String flightNumber;

	public Flight(String flightName, String flightNumber) {
		this.flightId = FlightDAO.getFlightCount() + 1;
		this.flightName = flightName;
		this.flightNumber = flightNumber;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Override
	public String toString() {
		String str = "Flight Id: " + getFlightId() + "\nFlight name: " + getFlightName() + "\nFlight number: "
				+ getFlightNumber() + "\n";
		return str;
	}
}
