package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.dao.FlightDAO;
import com.example.dao.SeatDAO;

public class SeatController {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

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
		FlightDAO.displayFlight();
		int id = FlightDAO.getFlightID();
		int cId = FlightDAO.checkFlightID(id);
		SeatDAO.displayByFlightId(cId);
	}

	private void viewAll() {
		SeatDAO.displayAllSeat();
	}

}
