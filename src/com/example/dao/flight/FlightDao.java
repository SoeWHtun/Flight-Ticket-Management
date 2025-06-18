package com.example.dao.flight;

import com.example.dao.AbstractDao;
import com.example.model.Flight;

public abstract class FlightDao extends AbstractDao<Flight> {
    public static final String FILE_NAME= "flight.csv";
}
