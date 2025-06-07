package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.dao.FlightDAO;

import com.example.model.Flight;

import com.example.service.FlightService;
import com.example.service.SeatService;

public class FlightController {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	public static FlightService flightService = new FlightService();

	public void call() throws NumberFormatException, IOException {
		int choice;
		do {

			System.out.println("\n**Flight Management**");

			System.out.println("1. Flight Registration ");
			System.out.println("2. Update flight deatail ");
			System.out.println("3. Find flight by Id");
			System.out.println("4. View all flight");
			System.out.println("5. View all flight with available seats");
			System.out.println("6. Delete flight");
			System.out.println("7. Exit");
			choice = FlightTicketManagement.getChoice();

			switch (choice) {
			case 1 -> createFlight();
			case 2 -> editFlight();
			case 3 -> findById();
			case 4 -> viewAllFlight();
			case 5 -> viewAvailable();
			case 6 -> deleteFlight();
			case 7 -> System.out.println("Exited");
			default -> System.out.println("Invalid choice! Please select a valid option.");
			}

		} while (choice != 7);
		System.out.println();
	}

	private void viewAvailable() {
		FlightDAO.displayFlightwithSeat();
	}

	private void deleteFlight() throws IOException {
		int id = FlightDAO.getFlightID();
		int cId = FlightDAO.checkFlightID(id);
		FlightDAO.deleteFlight(cId);
		FlightDAO.displayFlight();
	}

	private void viewAllFlight() {
		FlightDAO.displayFlight();

	}

	private void findById() throws IOException {

		int id = FlightDAO.getFlightID();
		int cId = FlightDAO.checkFlightID(id);
		Flight foundFlight = FlightDAO.findById(cId);
		System.out.println(foundFlight);
	}

	private void editFlight() throws IOException {

		int id = FlightDAO.getFlightID();
		int cId = FlightDAO.checkFlightID(id);
		System.out.print("Enter flight name: ");
		String name = bufferedReader.readLine();
		System.out.print("Enter flight number: ");
		String number = bufferedReader.readLine();
		Flight updateFlight = new Flight(name, number);
		FlightService.updateFlight(cId, updateFlight);
		System.out.println("Flight Details Updated\n");
		Flight foundFlight = FlightDAO.findById(cId);
		System.out.println(foundFlight);

	}

	private void createFlight() throws IOException {
		System.out.print("Enter flight name: ");
		String name = bufferedReader.readLine();
		System.out.print("Enter flight number: ");
		String number = bufferedReader.readLine();
		Flight newFlight = new Flight(name, number);
		FlightService.createFlight(newFlight);
		SeatService.createSeat(newFlight);
		System.out.println("New Flight Created\n");
		System.out.println(newFlight);
	}
}
