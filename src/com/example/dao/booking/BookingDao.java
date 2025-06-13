package com.example.dao.booking;

import com.example.dao.AbstractDao;
import com.example.model.Booking;

public interface BookingDao extends AbstractDao<Booking> {
    public static final String FILE_NAME = "booking.csv";

}
