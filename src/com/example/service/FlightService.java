package com.example.service;

import com.example.dao.FlightDAO;
import com.example.model.Flight;

public class FlightService {

	public static void createFlight(Flight flight) {
		FlightDAO.addFlight(flight);
	}

	public static void updateFlight(int id, Flight flight) {
		Flight updateFlight = FlightDAO.findById(id);
		updateFlight.setFlightName(flight.getFlightName());
		updateFlight.setFlightNumber(flight.getFlightNumber());
	}

}
