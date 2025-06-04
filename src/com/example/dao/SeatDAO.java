package com.example.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.model.Flight;

import com.example.model.Seat;

public class SeatDAO {
	private static Seat[] seatDB = new Seat[2000];
	private static int seatCount = 0;
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

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

	public static int getSeatID() {
		int seatID;
		try {
			System.out.print("Enter seat ID: ");
			seatID = Integer.parseInt(bufferedReader.readLine());
		} catch (IOException ex) {
			System.out.println("Please enter valid value");
			return getSeatID();
		} catch (NumberFormatException ex) {
			System.out.println("Please enter valid ID");
			return getSeatID();
		}
		return seatID;
	}

	public static int checkSeatID(int id) {
		int checkedId = 0;
		try {
			Seat checkSeat = findById(id);
			checkedId = checkSeat.getSeatId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Seat found!Please enter valid ID\n");
			return checkSeatID(getSeatID());
		}
		return checkedId;
	}

}
