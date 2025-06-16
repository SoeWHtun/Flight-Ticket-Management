package com.example.model;


import static com.example.dao.flight.FlightDAOImpl.flightDAO;

public class Flight extends MasterData{
	private int flightId;
	private String flightName;
	private String flightNumber;

	public Flight(String flightName, String flightNumber) {
		this.flightId = flightDAO.getCount() + 1;
		this.flightName = flightName;
		this.flightNumber = flightNumber;
	}
	public Flight(int flightId,String flightName, String flightNumber) {
		this.flightId = flightId;
		this.flightName = flightName;
		this.flightNumber = flightNumber;
	}

	public Flight() {

	}

	@Override
	public  Flight toObj(String[] flightRow){
		int flightId = Integer.parseInt(flightRow[0]);
		String flightName = flightRow[1];
		String flightNumber = flightRow[2];
		return new Flight(flightId,flightName,flightNumber);
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

	public String[] toArray(){return new String[]{this.flightId+"",this.flightName,this.flightNumber};}

	@Override
	public String toString() {
		String str = "Flight Id: " + getFlightId() + "\nFlight name: " + getFlightName() + "\nFlight number: "
				+ getFlightNumber() + "\n";
		return str;
	}
}
