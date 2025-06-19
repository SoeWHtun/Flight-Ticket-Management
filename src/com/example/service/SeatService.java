package com.example.service;


import com.example.dao.seat.SeatDAO;
import com.example.dao.seat.SeatDaoImpl;
import com.example.model.Flight;
import com.example.model.Seat;

import static com.example.dao.seat.SeatDaoImpl.seatDao;


public class SeatService extends CommonService<Seat> {

    private static final SeatDAO seatDaoImpl = new SeatDaoImpl();

    public SeatService() {
        super(seatDaoImpl, "SEAT");
    }

    public static void createSeat(Flight flight) {
        for (int j = 1; j <= 50; j++) {
            seatDao.create(new Seat(flight, "SEAT" + j));
        }
    }

    public static void displayByFlightId(int id) {
        for (Seat seat : seatDao.getAll()) {
            if (seat.getFlight().getId() == id) {
                System.out.println(seat);
            }
        }

    }

    @Override
    public void prepareUpdateFields(Seat updateObject, Seat object) {
        updateObject.setFlight(object.getFlight());
        updateObject.setSeatNumber(object.getSeatNumber());
    }
}
