package com.example.service;


import com.example.model.Flight;
import com.example.model.Seat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.dao.seat.SeatDaoImpl.seatDao;


public class SeatService {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
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

	public static void displayAllSeat() {
		for (Seat seat : seatDao.getAll()) {
			System.out.println(seat);
		}
	}

	public static int getSeatID()throws IOException {
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

	public static int checkSeatID(int id)throws IOException {
		int checkedId = 0;
		try {
			Seat checkSeat = seatDao.findById(id);
			checkedId = checkSeat.getId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Seat found!Please enter valid ID\n");
			return checkSeatID(getSeatID());
		}
		return checkedId;
	}


}
