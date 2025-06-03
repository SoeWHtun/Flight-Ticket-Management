package com.example.dao;

import com.example.model.Flight;
import com.example.model.Seat;

public class SeatDAO {
	private static Seat[] seatDB = new Seat[2000];
	private static int seatCount = 0;

	static {
		for (Flight flight : FlightDAO.getAllFlight()) {
			for (int j = 1; j <= 100; j++) {
				addSeat(new Seat(flight, "SEAT" + j));
			}
		}
	}

	public static Seat[] getSeatDB() {
		return seatDB;
	}

	public static int getSeatCount() {
		return seatCount;
	}

	public static void addSeat(Seat seat) {
		seatCount++;
		seatDB[seatCount - 1] = seat;

	}

	public static Seat findById(int id) {
		for (int i = 0; i < seatCount; i++) {
			if (seatDB[i].getSeatId() == id) {
				return seatDB[i];
			}
		}

		return null;
	}

	public static void displayByFlightId(int id) {
		for (int i = 0; i < seatCount; i++) {
			if (seatDB[i].getFlight().getFlightId() == id) {
				System.out.println(seatDB[i]);
			}
		}

	}

	public static void displayAllSeat() {
		for (int i = 0; i < seatCount; i++) {
			System.out.println(seatDB[i]);
		}
	}
}
