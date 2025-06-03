package com.example.dao;

import com.example.model.Booking;

public class BookingDAO {
	private static Booking[] bookingDB = new Booking[1000];
	private static int bookingCount = 0;

	public static int getBookingCount() {
		return bookingCount;
	}

	public static void addBooking(Booking booking) {
		bookingCount++;
		bookingDB[bookingCount - 1] = booking;

	}

	public static Booking findById(int id) {
		for (int i = 0; i < bookingCount; i++) {
			if (bookingDB[i].getBookingId() == id) {
				return bookingDB[i];
			}
		}

		return null;
	}

	public static Boolean checkAvailableById(int id) {
		for (int j = 0; j < BookingDAO.getBookingCount(); j++) {

			if (bookingDB[j].getSeat().getSeatId() == id && bookingDB[j].getIsAvailable() == false) {
				System.out.println("The seat is already booked.Please book another seat");
				return false;
			}
		}
		return true;

	}

	public static void displayBooking() {
		for (int i = 0; i < bookingCount; i++) {
			System.out.println(bookingDB[i]);

		}
	}

	public static void displayBookingbyCustomer(int id) {
		for (int i = 0; i < bookingCount; i++) {
			if (bookingDB[i].getCustomer().getCustomerId() == id) {
				System.out.println(bookingDB[i]);
			}
		}
	}

	public static void deleteBooking(int bookingId) {
		for (int i = 0; i < bookingCount; i++) {
			if (bookingDB[i].getBookingId() == bookingId) {
				int j = i;
				while (bookingCount > j) {
					bookingDB[j] = bookingDB[j + 1];
					j++;
				}
				bookingCount--;
				return;

			}
		}
	}

	public static Booking[] getBookingDB() {
		return bookingDB;
	}

}
