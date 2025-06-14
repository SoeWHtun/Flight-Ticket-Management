package com.example.dao.seat;

import com.example.dao.AbstractDao;
import com.example.model.Seat;

public interface SeatDAO extends AbstractDao<Seat> {
    public static final String SEAT_FILE = "seat.csv";

}
