package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.dao.schedule.SchduleDaoImpl;
import com.example.dao.seat.SeatDaoImpl;
import com.example.model.Booking;
import com.example.model.Customer;
import com.example.model.Schedule;
import com.example.model.Seat;
import com.example.service.BookingService;

import static com.example.dao.customer.CustomerDaoImpl.customerDao;
import static com.example.dao.booking.BookingDaoImpl.bookingDao;
import static com.example.dao.schedule.SchduleDaoImpl.schduleDao;

public class BookingController {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    static SeatDaoImpl seatDao = new SeatDaoImpl();
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
		int customerId = customerDao.getCustomerID();
		int cCustomerId = customerDao.checkCustomerID(customerId);
		bookingDao.displayBookingbyCustomer(cCustomerId);
		int bookingId = bookingDao.getBookingID();
		int cBookingId = bookingDao.checkBookingID(bookingId);
		schduleDao.displaySchedule();
		int id = schduleDao.getScheduleID();
		int cId = schduleDao.checkScheduleID(id);
		SeatDaoImpl.displayByFlightId(cId);
		int seatId;
		int cSeatId;
		boolean seatAvailable;
		do {
			seatId = seatDao.getSeatID();
			cSeatId = seatDao.checkSeatID(seatId);
			seatAvailable = bookingDao.checkAvailableById(cSeatId);
		} while (!seatAvailable);
		Customer customer = customerDao.findById(cCustomerId);
		Schedule schedule = schduleDao.findById(cId);
		Seat seat = seatDao.findById(cSeatId);
		Booking updateBooking = new Booking(customer, schedule.getFlight(), schedule, seat);
		BookingService.updateBooking(cBookingId, updateBooking);
		System.out.println("\nBooking details updated\n");
		Booking foundBooking = bookingDao.findById(cBookingId);
		System.out.println(foundBooking);
	}

	private void viewBooking() throws IOException {

		int customerId = customerDao.getCustomerID();
		int cCustomerId = customerDao.checkCustomerID(customerId);
		bookingDao.displayBookingbyCustomer(cCustomerId);
	}

	private void cancel() throws IOException {

		int customerId = customerDao.getCustomerID();
		int cCustomerId = customerDao.checkCustomerID(customerId);
		bookingDao.displayBookingbyCustomer(cCustomerId);
		int id = bookingDao.getBookingID();
		int cId = bookingDao.checkBookingID(id);
		bookingDao.delete(cId);
		System.out.println("\nBooking ID: " + cId + " (Cancelled)");
		bookingDao.displayBookingbyCustomer(cCustomerId);
	}

	private void createByRoute() throws IOException {
		schduleDao.displaySchedule();
		System.out.print("Enter depature city: ");
		String dept = bufferedReader.readLine();
		System.out.print("Enter arrival city: ");
		String arrival = bufferedReader.readLine();
		Schedule foundSchedule = schduleDao.findByRoute(dept, arrival);
		if (foundSchedule == null) {
			System.out.println("No schedule found for the given route.");
			return;
		}

		int customerId = customerDao.getCustomerID();
		int cCustomerId = customerDao.checkCustomerID(customerId);
		SeatDaoImpl.displayByFlightId(foundSchedule.getScheduleId());
		int seatId;
		int cSeatId;
		boolean seatAvailable;
		do {

			seatId = seatDao.getSeatID();
			cSeatId = seatDao.checkSeatID(seatId);
			seatAvailable = bookingDao.checkAvailableById(cSeatId);
		} while (!seatAvailable);
		Customer customer = customerDao.findById(cCustomerId);
		Seat seat = seatDao.findById(cSeatId);
		Booking newBooking = new Booking(customer, foundSchedule.getFlight(), foundSchedule, seat);
		BookingService.createBooking(newBooking);
		System.out.println("\nNew Booking Created\n");
		System.out.println(newBooking);

	}

	private void createBySchedule() throws IOException {
		schduleDao.displaySchedule();
		int id = schduleDao.getScheduleID();
		int cId = schduleDao.checkScheduleID(id);
		int customerId = customerDao.getCustomerID();
		int cCustomerId = customerDao.checkCustomerID(customerId);
		SeatDaoImpl.displayByFlightId(cId);
		int seatId;
		int cSeatId;
		boolean seatAvailable;
		do {

			seatId = seatDao.getSeatID();
			cSeatId = seatDao.checkSeatID(seatId);
			seatAvailable = bookingDao.checkAvailableById(cSeatId);
		} while (!seatAvailable);
		Customer customer = customerDao.findById(cCustomerId);
		Schedule schedule = schduleDao.findById(cId);
		Seat seat = seatDao.findById(cSeatId);
		Booking newBooking = new Booking(customer, schedule.getFlight(), schedule, seat);
		BookingService.createBooking(newBooking);
		System.out.println("\nNew Booking Created\n");
		System.out.println(newBooking);

	}
}
