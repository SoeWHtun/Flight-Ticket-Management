package com.example.service;


import com.example.model.Booking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.dao.booking.BookingDaoImpl.bookingDao;

public class BookingService {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	public static void createBooking(Booking booking) {

		bookingDao.create(booking);
	}

	public static void updateBooking(int id, Booking booking) {
		Booking updateBooking = bookingDao.findById(id);
		updateBooking.setCustomer(booking.getCustomer());
		updateBooking.setFlight(booking.getFlight());
		updateBooking.setSchedule(booking.getSchedule());
		updateBooking.setSeat(booking.getSeat());
		updateBooking.setIsAvailable(booking.getIsAvailable());
        bookingDao.update(id,updateBooking);

	}

	public static Boolean checkAvailableById(int id) {
		for (Booking booking : bookingDao.getAll()) {

			if (booking.getSeat().getId() == id && booking.getIsAvailable() == false) {
				System.out.println("The seat is already booked.Please book another seat");
				return false;
			}
		}
		return true;
	}



	public static void displayBookingbyCustomer(int id) {
		for (Booking booking : bookingDao.getAll()) {
			if (booking.getCustomer().getId() == id) {
				System.out.println(booking);
			}
		}
	}

	public static int getBookingID() {
		int bookingID;
		try {
			System.out.print("Enter your booking ID: ");
			bookingID = Integer.parseInt(bufferedReader.readLine());
		} catch (IOException | NumberFormatException | NullPointerException ex) {
			System.out.println("Please enter valid value");
			return getBookingID();
		}
		return bookingID;
	}

	public static int checkBookingID(int id) {
		int checkedId = 0;
		try {
			Booking checkBooking = bookingDao.findById(id);
			checkedId = checkBooking.getId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Booking found!Please enter valid ID\n");
			return checkBookingID(getBookingID());
		}
		return checkedId;
	}
}
