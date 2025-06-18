package com.example.service;


import com.example.model.Booking;
import com.example.model.Flight;
import com.example.model.Seat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.dao.booking.BookingDaoImpl.bookingDao;
import static com.example.dao.flight.FlightDAOImpl.flightDAO;
import static com.example.dao.seat.SeatDaoImpl.seatDao;

public class FlightService {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	public static void createFlight(Flight flight) {
		flightDAO.create(flight);
	}

	public static void updateFlight(int id, Flight flight) {
		Flight updateFlight = flightDAO.findById(id);
		updateFlight.setFlightName(flight.getFlightName());
		updateFlight.setFlightNumber(flight.getFlightNumber());
        flightDAO.update(id,updateFlight);
	}
	public static void displayFlight() {
		for (Flight flight : flightDAO.getAll()) {
			System.out.println(flight);

		}
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

	public static int getFlightID() {
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

	public static int checkFlightID(int id) {
		int checkedId = 0;
		try {
			Flight checkFlight = flightDAO.findById(id);
			checkedId = checkFlight.getId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Flight found!Please enter valid ID\n");
			return checkFlightID(getFlightID());
		}
		return checkedId;
	}

}
