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
			choice = FlightTicketManagement.getChoice();

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
		int customerId = CustomerDAO.getCustomerID();
		int cCustomerId = CustomerDAO.checkCustomerID(customerId);
		BookingDAO.displayBookingbyCustomer(cCustomerId);
		int bookingId = BookingDAO.getBookingID();
		int cBookingId = BookingDAO.checkBookingID(bookingId);
		SchduleDAO.displaySchedule();
		int id = SchduleDAO.getScheduleID();
		int cId = SchduleDAO.checkScheduleID(id);
		SeatDAO.displayByFlightId(cId);
		int seatId;
		int cSeatId;
		boolean seatAvailable;
		do {
			seatId = SeatDAO.getSeatID();
			cSeatId = SeatDAO.checkSeatID(seatId);
			seatAvailable = BookingDAO.checkAvailableById(cSeatId);
		} while (!seatAvailable);
		Customer customer = CustomerDAO.findById(cCustomerId);
		Schedule schedule = SchduleDAO.findById(cId);
		Seat seat = SeatDAO.findById(cSeatId);
		Booking updateBooking = new Booking(customer, schedule.getFlight(), schedule, seat);
		BookingService.updateBooking(cBookingId, updateBooking);
		System.out.println("\nBooking details updated\n");
		Booking foundBooking = BookingDAO.findById(cBookingId);
		System.out.println(foundBooking);
	}

	private void viewBooking() throws IOException {

		int customerId = CustomerDAO.getCustomerID();
		int cCustomerId = CustomerDAO.checkCustomerID(customerId);
		BookingDAO.displayBookingbyCustomer(cCustomerId);
	}

	private void cancel() throws IOException {

		int customerId = CustomerDAO.getCustomerID();
		int cCustomerId = CustomerDAO.checkCustomerID(customerId);
		BookingDAO.displayBookingbyCustomer(cCustomerId);
		int id = BookingDAO.getBookingID();
		int cId = BookingDAO.checkBookingID(id);
		BookingDAO.deleteBooking(cId);
		System.out.print("Booking ID: " + cId + " (Cancelled)");
		BookingDAO.displayBookingbyCustomer(cCustomerId);
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

		int customerId = CustomerDAO.getCustomerID();
		int cCustomerId = CustomerDAO.checkCustomerID(customerId);
		SeatDAO.displayByFlightId(foundSchedule.getScheduleId());
		int seatId;
		int cSeatId;
		boolean seatAvailable;
		do {

			seatId = SeatDAO.getSeatID();
			cSeatId = SeatDAO.checkSeatID(seatId);
			seatAvailable = BookingDAO.checkAvailableById(cSeatId);
		} while (!seatAvailable);
		Customer customer = CustomerDAO.findById(cCustomerId);
		Seat seat = SeatDAO.findById(cSeatId);
		Booking newBooking = new Booking(customer, foundSchedule.getFlight(), foundSchedule, seat);
		BookingService.createBooking(newBooking);
		System.out.println("\nNew Booking Created\n");
		System.out.println(newBooking);

	}

	private void createBySchedule() throws IOException {
		SchduleDAO.displaySchedule();
		int id = SchduleDAO.getScheduleID();
		int cId = SchduleDAO.checkScheduleID(id);
		int customerId = CustomerDAO.getCustomerID();
		int cCustomerId = CustomerDAO.checkCustomerID(customerId);
		SeatDAO.displayByFlightId(cId);
		int seatId;
		int cSeatId;
		boolean seatAvailable;
		do {

			seatId = SeatDAO.getSeatID();
			cSeatId = SeatDAO.checkSeatID(seatId);
			seatAvailable = BookingDAO.checkAvailableById(cSeatId);
		} while (!seatAvailable);
		Customer customer = CustomerDAO.findById(cCustomerId);
		Schedule schedule = SchduleDAO.findById(cId);
		Seat seat = SeatDAO.findById(cSeatId);
		Booking newBooking = new Booking(customer, schedule.getFlight(), schedule, seat);
		BookingService.createBooking(newBooking);
		System.out.println("\nNew Booking Created\n");
		System.out.println(newBooking);

	}
}
