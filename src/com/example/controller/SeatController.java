package com.example.controller;


import java.io.IOException;
import com.example.service.FlightService;
import com.example.service.SeatService;


public class SeatController {
    private  static  FlightService flightService = new FlightService();
	private static  SeatService seatService = new SeatService();

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
		flightService.displayAll();
		int cId = flightService.findById();
		SeatService.displayByFlightId(cId);
	}

	private void viewAll() {
		seatService.displayAll();
	}

}
