package com.example.service;

import com.example.dao.seat.SeatDaoImpl;
import com.example.model.Flight;
import com.example.model.Seat;

public class SeatService {
	public static void createSeat(Flight flight) {
		for (int j = 1; j <= 50; j++) {
			SeatDaoImpl.addSeat(new Seat(flight, "SEAT" + j));
		}
	}

}
