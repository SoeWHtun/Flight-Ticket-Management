package com.example.dao;

import com.example.model.Flight;

public class FlightDAO {
	private static Flight[] flightDB = new Flight[1000];
	private static int flightCount = 0;

	static {
		addFlight(new Flight("MAI", "FM0034"));
		addFlight(new Flight("Air Asia", "M0073"));
		addFlight(new Flight("Thai Air", "TR8873"));
		addFlight(new Flight("Myanmar Airways", "MM001"));
		addFlight(new Flight("Singapore Airlines", "SQ223"));
		addFlight(new Flight("Emirates", "EK393"));
		addFlight(new Flight("Qatar Airways", "QR845"));
		addFlight(new Flight("Lufthansa", "LH789"));
		addFlight(new Flight("ANA", "NH867"));
		addFlight(new Flight("Vietnam Airlines", "VN123"));

	}

	public static int getFlightCount() {
		return flightCount;
	}

	public static void addFlight(Flight flight) {
		flightCount++;
		flightDB[flightCount - 1] = flight;

	}

	public static Flight findById(int id) {
		for (int i = 0; i < flightCount; i++) {
			if (flightDB[i].getFlightId() == id) {
				return flightDB[i];
			}
		}

		return null;
	}

	public static Flight[] getAllFlight() {
		Flight[] flights = new Flight[flightCount];
		for (int i = 0; i < flightCount; i++) {
			flights[i] = flightDB[i];
		}
		return flights;
	}

	public static void displayFlight() {
		for (int i = 0; i < flightCount; i++) {
			System.out.println(flightDB[i]);

		}
	}

	public static void deleteFlight(int id) {
		for (int i = 0; i < flightCount; i++) {
			if (flightDB[i].getFlightId() == id) {
				int j = i;
				while (flightCount > j) {
					flightDB[j] = flightDB[j + 1];
					j++;
				}
				flightCount--;
				return;

			}
		}
	}

	public static void displayFlightwithSeat() {
		boolean found = false;
		for (int i = 0; i < flightCount; i++) {
			Flight flight = flightDB[i];
			boolean hasAvailableSeat = false;
			for (int j = 0; j < SeatDAO.getSeatCount(); j++) {
				if (SeatDAO.getSeatDB()[j].getFlight().getFlightId() == flight.getFlightId()) {
					boolean isBooked = false;
					for (int k = 0; k < BookingDAO.getBookingCount(); k++) {
						if (BookingDAO.getBookingDB()[k].getSeat().getSeatId() == SeatDAO.getSeatDB()[j].getSeatId()
								&& BookingDAO.getBookingDB()[k].getIsAvailable() == false) {
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
}
