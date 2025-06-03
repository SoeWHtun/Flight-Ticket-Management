package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.dao.BookingDAO;
import com.example.dao.CustomerDAO;
import com.example.dao.SchduleDAO;
import com.example.dao.SeatDAO;
import com.example.model.Booking;
import com.example.model.Customer;
import com.example.model.Schedule;
import com.example.model.Seat;
import com.example.service.BookingService;

public class BookingController {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	public void call() throws NumberFormatException, IOException {
		int choice;
		do {

			System.out.println("\n**Booking Management**");

			System.out.println("1. Create new booking by schdeule");
			System.out.println("2. Create new booking by route  ");
			System.out.println("3. View bookings");
			System.out.println("4. Update bookings");
			System.out.println("5. Cancel booking");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			choice = Integer.parseInt(bufferedReader.readLine());

			switch (choice) {
			case 1 -> createBySchedule();
			case 2 -> createByRoute();
			case 3 -> viewBooking();
			case 4 -> updateBooking();
			case 5 -> cancel();
			case 6 -> System.out.println("Exited");
			default -> System.out.println("Invalid choice! Please select a valid option.");
			}

		} while (choice != 6);
		System.out.println();
	}

	private void updateBooking() throws IOException {
		System.out.print("Enter your customer ID: ");
		int customerId = Integer.parseInt(bufferedReader.readLine());
		BookingDAO.displayBookingbyCustomer(customerId);
		System.out.print("Enter booking ID to update: ");
		int bookingId = Integer.parseInt(bufferedReader.readLine());
		SchduleDAO.displaySchedule();
		System.out.print("Enter schedule ID to book: ");
		int id = Integer.parseInt(bufferedReader.readLine());
		SeatDAO.displayByFlightId(id);
		int seatId;
		boolean seatAvailable;
		do {
			System.out.print("Enter seat ID to book: ");
			seatId = Integer.parseInt(bufferedReader.readLine());
			seatAvailable = BookingDAO.checkAvailableById(seatId);
		} while (!seatAvailable);
		Customer customer = CustomerDAO.findById(customerId);
		Schedule schedule = SchduleDAO.findById(id);
		Seat seat = SeatDAO.findById(seatId);
		Booking updateBooking = new Booking(customer, schedule.getFlight(), schedule, seat);
		BookingService.updateBooking(bookingId, updateBooking);
		System.out.println("\nBooking details updated\n");
		Booking foundBooking = BookingDAO.findById(bookingId);
		System.out.println(foundBooking);

	}

	private void viewBooking() throws IOException {
		System.out.print("Enter your customer ID: ");
		int customerId = Integer.parseInt(bufferedReader.readLine());
		BookingDAO.displayBookingbyCustomer(customerId);
	}

	private void cancel() throws IOException {
		System.out.print("Enter your customer ID: ");
		int customerId = Integer.parseInt(bufferedReader.readLine());
		BookingDAO.displayBookingbyCustomer(customerId);
		System.out.print("Enter booking ID to cancel: ");
		int id = Integer.parseInt(bufferedReader.readLine());
		BookingDAO.deleteBooking(id);
		System.out.print("Booking ID: " + id + " (Cancelled)");
		BookingDAO.displayBookingbyCustomer(customerId);
	}

	private void createByRoute() throws IOException {
		SchduleDAO.displaySchedule();
		System.out.print("Enter depature city: ");
		String dept = bufferedReader.readLine();
		System.out.print("Enter arrival city: ");
		String arrival = bufferedReader.readLine();
		Schedule foundSchedule = SchduleDAO.findByRoute(dept, arrival);
		if (foundSchedule == null) {
			System.out.println("No schedule found for the given route.");
			return;
		}
		System.out.print("Enter your customer ID: ");
		int customerId = Integer.parseInt(bufferedReader.readLine());
		SeatDAO.displayByFlightId(foundSchedule.getScheduleId());
		int seatId;
		boolean seatAvailable;
		do {
			System.out.print("Enter seat ID to book: ");
			seatId = Integer.parseInt(bufferedReader.readLine());
			seatAvailable = BookingDAO.checkAvailableById(seatId);
		} while (!seatAvailable);
		Customer customer = CustomerDAO.findById(customerId);
		Seat seat = SeatDAO.findById(seatId);
		Booking newBooking = new Booking(customer, foundSchedule.getFlight(), foundSchedule, seat);
		BookingService.createBooking(newBooking);
		System.out.println("\nNew Booking Created\n");
		System.out.println(newBooking);

	}

	private void createBySchedule() throws IOException {
		SchduleDAO.displaySchedule();
		System.out.print("Enter schedule ID to book: ");
		int id = Integer.parseInt(bufferedReader.readLine());
		System.out.print("Enter your customer ID: ");
		int customerId = Integer.parseInt(bufferedReader.readLine());
		SeatDAO.displayByFlightId(id);
		int seatId;
		boolean seatAvailable;
		do {
			System.out.print("Enter seat ID to book: ");
			seatId = Integer.parseInt(bufferedReader.readLine());
			seatAvailable = BookingDAO.checkAvailableById(seatId);
		} while (!seatAvailable);
		Customer customer = CustomerDAO.findById(customerId);
		Schedule schedule = SchduleDAO.findById(id);
		Seat seat = SeatDAO.findById(seatId);
		Booking newBooking = new Booking(customer, schedule.getFlight(), schedule, seat);
		BookingService.createBooking(newBooking);
		System.out.println("\nNew Booking Created\n");
		System.out.println(newBooking);

	}
}
