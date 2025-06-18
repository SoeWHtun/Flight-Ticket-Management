package com.example.model;

import com.example.dao.seat.SeatDAO;
import com.example.dao.seat.SeatDaoImpl;

import static com.example.dao.flight.FlightDAOImpl.flightDAO;
import static com.example.dao.seat.SeatDaoImpl.seatDao;

public class Seat extends MasterData {

	private String seatNumber;
	private Flight flight;

	public Seat(){

	}

	public Seat(Flight flight, String seatNumber) {
		super.setId(seatDao.getCount()+1);
		this.flight = flight;
		this.seatNumber = seatNumber;
	}
	public Seat(int seatId,Flight flight, String seatNumber) {
		super.setId(seatId);
		this.flight = flight;
		this.seatNumber = seatNumber;
	}

	public static Seat toObj(String[] seatRow){
		int seatId = Integer.parseInt(seatRow[0]);
		int flightId = Integer.parseInt(seatRow[1]);
		String seatNumber = seatRow[2];
		Flight nflight = flightDAO.findById(flightId);
		return new Seat(seatId,nflight,seatNumber);
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
			this.getId()+"",
			this.flight.getId()+"",
				this.seatNumber
		};
	}
	@Override
	public String toString() {
		String str = "Seat Id: " + getId() + "\nSeat number: " + getSeatNumber() + "\nFlight Id: "
				+ getFlight().getId() + "\nFlight name: " + getFlight().getFlightName() + "\nFlight number: "
				+ getFlight().getFlightNumber() + "\n";
		return str;
	}

}
