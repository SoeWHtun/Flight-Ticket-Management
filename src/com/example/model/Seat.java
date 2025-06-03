package com.example.model;

import com.example.dao.SeatDAO;

public class Seat {
	private int seatId;
	private String seatNumber;
	private Flight flight;

	public Seat(Flight flight, String seatNumber) {
		this.seatId = SeatDAO.getSeatCount() + 1;
		this.flight = flight;
		this.seatNumber = seatNumber;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		String str = "Seat Id: " + getSeatId() + "\nSeat number: " + getSeatNumber() + "\nFlight Id: "
				+ getFlight().getFlightId() + "\nFlight name: " + getFlight().getFlightName() + "\nFlight number: "
				+ getFlight().getFlightNumber() + "\n";
		return str;
	}

}
