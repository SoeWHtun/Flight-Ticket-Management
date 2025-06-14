package com.example.service;

import com.example.dao.flight.FlightDAOImpl;
import com.example.model.Flight;

import static com.example.dao.flight.FlightDAOImpl.flightDAO;

public class FlightService {

	public static void createFlight(Flight flight) {
		flightDAO.create(flight);
	}

	public static void updateFlight(int id, Flight flight) {
		Flight updateFlight = flightDAO.findById(id);
		updateFlight.setFlightName(flight.getFlightName());
		updateFlight.setFlightNumber(flight.getFlightNumber());
        flightDAO.update(id,updateFlight);
	}

}
