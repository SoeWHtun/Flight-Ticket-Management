package com.example.service;


import com.example.dao.flight.FlightDAOImpl;
import com.example.dao.flight.FlightDao;
import com.example.model.Booking;
import com.example.model.Flight;
import com.example.model.Seat;


import static com.example.dao.booking.BookingDaoImpl.bookingDao;
import static com.example.dao.flight.FlightDAOImpl.flightDAO;
import static com.example.dao.seat.SeatDaoImpl.seatDao;

public class FlightService extends CommonService<Flight> {
    public static void createFlight(Flight flight) {
        flightDAO.create(flight);
    }

    private static final FlightDao flightDaoImpl = new FlightDAOImpl();

    public FlightService() {
        super(flightDaoImpl, "Flight");
    }

    public static void displayFlightwithSeat() {
        boolean found = false;
        for (Flight flights : flightDAO.getAll()) {
            Flight flight = flights;
            boolean hasAvailableSeat = false;
            for (Seat seat : seatDao.getAll()) {
                if (seat.getFlight().getId() == flight.getId()) {
                    boolean isBooked = false;
                    for (Booking booking : bookingDao.getAll()) {
                        if (booking.getSeat().getId() == seat.getId()
                                && booking.getIsAvailable() == false) {
                            isBooked = true;
                            break;
                        }
                    }
                    if (!isBooked) {
                        hasAvailableSeat = true;
                        break;
                    }

                }

            }
            if (hasAvailableSeat) {
                System.out.println(flight);
                found = true;
            }

        }
        if (!found) {
            System.out.println("No flights with available seats found.");
        }

    }


    @Override
    public void prepareUpdateFields(Flight updateObject, Flight object) {
        updateObject.setFlightName(object.getFlightName());
        updateObject.setFlightNumber(object.getFlightNumber());
    }
}
