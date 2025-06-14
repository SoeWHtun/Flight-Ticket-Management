package com.example.dao.flight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.AbstractDao;
import com.example.dao.seat.SeatDaoImpl;
import com.example.model.Booking;
import com.example.model.Flight;
import com.example.model.Seat;
import com.example.util.FileUtil;

import static com.example.dao.booking.BookingDaoImpl.bookingDao;
import static com.example.dao.flight.FlightDao.FLIGHT_FILE;

public class FlightDAOImpl implements AbstractDao<Flight> {
    public static FlightDAOImpl flightDAO = new FlightDAOImpl();
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    static {
        String[] header = {"FlightId", "FlightName", "FlightNumber"};
        FileUtil.csvCreater(FLIGHT_FILE, header);

    }

    public int getFlightCSVId() {
        List<Flight> flightList = getAll();
        if (flightList.size() > 0) {
            flightList.sort((c1, c2) -> Integer.compare(c1.getFlightId(), c2.getFlightId()));
            return flightList.getLast().getFlightId() + 1;
        } else {
            return 1;
        }

    }

    private static List<Flight> toFlights(List<String[]> flightsData) {
        List<Flight> flightList = new ArrayList<>();
        for (String[] flightRow : flightsData) {
            Flight flight = Flight.toObj(flightRow);
            flightList.add(flight);
        }
        return flightList;
    }


    public void displayFlight() {
        for (Flight flight : getAll()) {
            System.out.println(flight);

        }
    }


    public void displayFlightwithSeat() {
        boolean found = false;
        for (Flight flights : getAll()) {
            Flight flight = flights;
            boolean hasAvailableSeat = false;
            for (Seat seat : SeatDaoImpl.getAllSeat()) {
                if (seat.getFlight().getFlightId() == flight.getFlightId()) {
                    boolean isBooked = false;
                    for (Booking booking : bookingDao.getAll()) {
                        if (booking.getSeat().getSeatId() == seat.getSeatId()
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

    public int getFlightID() {
        int flightID;
        try {
            System.out.print("Enter flight ID: ");
            flightID = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException ex) {
            System.out.println("Please enter valid value");
            return getFlightID();
        } catch (NumberFormatException ex) {
            System.out.println("Please enter valid ID");
            return getFlightID();
        }
        return flightID;
    }

    public int checkFlightID(int id) {
        int checkedId = 0;
        try {
            Flight checkFlight = findById(id);
            checkedId = checkFlight.getFlightId();
        } catch (NullPointerException ex) {
            System.out.print("\nNo Flight found!Please enter valid ID\n");
            return checkFlightID(getFlightID());
        }
        return checkedId;
    }


    @Override
    public void create(Flight flight) {

        flight.setFlightId(getFlightCSVId());
        FileUtil.csvWriter(FLIGHT_FILE, flight.toArray());
    }

    @Override
    public Flight findById(int id) {
        for (Flight flight : getAll()) {
            if (flight.getFlightId() == id) {
                return flight;
            }
        }

        return null;
    }

    @Override
    public void update(int id, Flight flight) {
        FileUtil.updateRecordById(FLIGHT_FILE, id + "", flight.toArray());
    }

    @Override
    public void delete(int id) {
        FileUtil.deleteRecordById(FLIGHT_FILE, id + "");
    }

    @Override
    public List<Flight> getAll() {
        List<String[]> flightData = FileUtil.csvReader(FLIGHT_FILE);
        List<Flight> flightList = toFlights(flightData);
        return flightList;
    }
}
