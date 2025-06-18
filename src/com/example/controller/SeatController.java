package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.dao.seat.SeatDaoImpl;
import com.example.service.FlightService;
import com.example.service.SeatService;

import static com.example.dao.flight.FlightDAOImpl.flightDAO;

public class SeatController {


	public void call() throws NumberFormatException, IOException {
		int choice;
		do {

			System.out.println("\n**Seat Management**");

			System.out.println("1. View all seats in all flight");
			System.out.println("2. View all seats by flight ID");
			System.out.println("3. Exit");
			choice = FlightTicketManagement.getChoice();

			switch (choice) {
			case 1 -> viewAll();
			case 2 -> viewByFlight();
			case 3 -> System.out.println("Exited");
			default -> System.out.println("Invalid choice! Please select a valid option.");
			}

		} while (choice != 3);
		System.out.println();
	}

	private void viewByFlight() throws IOException {
		FlightService.displayFlight();
		int id = FlightService.getFlightID();
		int cId = FlightService.checkFlightID(id);
		SeatService.displayByFlightId(cId);
	}

	private void viewAll() {
		SeatService.displayAllSeat();
	}

}
