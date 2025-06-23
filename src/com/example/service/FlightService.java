package com.example.service;


import com.example.dao.flight.FlightDAOImpl;
import com.example.dao.flight.FlightDao;
import com.example.model.Flight;
import static com.example.dao.flight.FlightDAOImpl.flightDAO;

public class FlightService extends CommonService<Flight> {
    public static void createFlight(Flight flight) {
        flightDAO.create(flight);
    }

    private static final FlightDao flightDaoImpl = new FlightDAOImpl();

    public FlightService() {
        super(flightDaoImpl, "Flight");
    }

    public static void displayFlightwithSeat() {

    }


    @Override
    public void prepareUpdateFields(Flight updateObject, Flight object) {
        updateObject.setFlightName(object.getFlightName());
        updateObject.setFlightNumber(object.getFlightNumber());
    }
}
