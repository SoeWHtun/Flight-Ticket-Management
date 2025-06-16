package com.example.model;

import com.example.dao.flight.FlightDAOImpl;
import com.example.dao.seat.SeatDaoImpl;

import static com.example.dao.flight.FlightDAOImpl.flightDAO;

public class Seat extends MasterData {
	private int seatId;
	private String seatNumber;
	private Flight flight;
	public Seat(){

	}

	public Seat(Flight flight, String seatNumber) {
		this.seatId = SeatDaoImpl.getSeatCount() + 1;
		this.flight = flight;
		this.seatNumber = seatNumber;
	}
	public Seat(int seatId,Flight flight, String seatNumber) {
		this.seatId = seatId;
		this.flight = flight;
		this.seatNumber = seatNumber;
	}
	@Override
	public Seat toObj(String[] seatRow){
		int seatId = Integer.parseInt(seatRow[0]);
		int flightId = Integer.parseInt(seatRow[1]);
		String seatNumber = seatRow[2];
		Flight nflight = flightDAO.findById(flightId);
		return new Seat(seatId,nflight,seatNumber);
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
    public String[] toArray(){
		return new String[]{
			this.seatId+"",
			this.flight.getFlightId()+"",
				this.seatNumber
		};
	}
	@Override
	public String toString() {
		String str = "Seat Id: " + getSeatId() + "\nSeat number: " + getSeatNumber() + "\nFlight Id: "
				+ getFlight().getFlightId() + "\nFlight name: " + getFlight().getFlightName() + "\nFlight number: "
				+ getFlight().getFlightNumber() + "\n";
		return str;
	}

}
