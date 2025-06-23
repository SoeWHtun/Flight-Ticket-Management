package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FlightTicketManagement {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	public static CustomerController customerController = new CustomerController();
	public static ScheduleController scheduleController = new ScheduleController();
	public static FlightController flightController = new FlightController();
	public static SeatController seatController = new SeatController();
	public static BookingController bookingController = new BookingController();

	public static void main(String[] args) throws IOException {
		int choice;
		do {

			System.out.println("**Flight Ticket Management System**");

			System.out.println("1. Customer Management");
			System.out.println("2. Schedule Management");
			System.out.println("3. Flight Management");
			System.out.println("4. Booking Management");
			System.out.println("5. Seat Management");

			System.out.println("6. Exit");
			choice = getChoice();

			switch (choice) {
			case 1 -> customerController.call();
			case 2 -> scheduleController.call();
			case 3 -> flightController.call();
			case 4 -> bookingController.call();
			case 5 -> seatController.call();
			case 6 -> System.out.println("Exited");
			default -> System.out.println("Invalid choice! Please select a valid option.");
			}

		} while (choice != 6);
		System.out.println();

	}

	public static int getChoice() {
		int choice;
		try {
			System.out.print("Enter your choice: ");
			choice = Integer.parseInt(bufferedReader.readLine());
		} catch (IOException | NumberFormatException ex) {
			System.out.println("Please enter valid value");
			return getChoice();
		}
		return choice;
	}

}
