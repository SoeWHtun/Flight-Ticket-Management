package com.example.dao.flight;

import com.example.dao.AbstractDao;
import com.example.model.Flight;

public interface FlightDao extends AbstractDao<Flight> {
    public static final String FLIGHT_FILE= "flight.csv";
}
