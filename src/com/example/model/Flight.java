package com.example.model;


import static com.example.dao.flight.FlightDAOImpl.flightDAO;

public class Flight extends MasterData{
	private String flightName;
	private String flightNumber;

	public Flight(String flightName, String flightNumber) {
		super.setId(flightDAO.getCount()+1);
		this.flightName = flightName;
		this.flightNumber = flightNumber;
	}
	public Flight(int flightId,String flightName, String flightNumber) {
		super.setId(flightId);
		this.flightName = flightName;
		this.flightNumber = flightNumber;
	}

	public Flight() {

	}

	public static Flight toObj(String[] flightRow){
		int flightId = Integer.parseInt(flightRow[0]);
		String flightName = flightRow[1];
		String flightNumber = flightRow[2];
		return new Flight(flightId,flightName,flightNumber);
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

	public String[] toArray(){return new String[]{this.getId()+"",this.flightName,this.flightNumber};}

	@Override
	public String toString() {
		String str = "Flight Id: " + getId() + "\nFlight name: " + getFlightName() + "\nFlight number: "
				+ getFlightNumber() + "\n";
		return str;
	}
}
