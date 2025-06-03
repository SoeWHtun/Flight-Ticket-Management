package com.example.service;

import com.example.dao.SeatDAO;
import com.example.model.Flight;
import com.example.model.Seat;

public class SeatService {
	public static void createSeat(Flight flight) {
		for (int j = 1; j <= 100; j++) {
			SeatDAO.addSeat(new Seat(flight, "SEAT" + j));
		}
	}

}
