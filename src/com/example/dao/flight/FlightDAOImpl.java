package com.example.dao.flight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.model.Booking;
import com.example.model.Flight;
import com.example.model.Seat;
import com.example.util.FileUtil;
import static com.example.dao.booking.BookingDaoImpl.bookingDao;
import static com.example.dao.seat.SeatDaoImpl.seatDao;

public class FlightDAOImpl implements FlightDao {
    public static FlightDAOImpl flightDAO = new FlightDAOImpl();
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    static {
        String[] header = {"FlightId", "FlightName", "FlightNumber"};
        FileUtil.csvCreater(FILE_NAME, header);

    }







    public Flight toObj(String[] row) {
        return Flight.toObj(row);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
