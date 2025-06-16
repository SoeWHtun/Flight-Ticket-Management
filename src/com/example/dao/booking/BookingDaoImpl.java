package com.example.dao.booking;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Booking;
import com.example.util.FileUtil;

public class BookingDaoImpl implements BookingDao {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	public static BookingDaoImpl bookingDao = new BookingDaoImpl();
	static {
		String[] header = {"Id", "customerId", "flightId", "scheduleId","seatId"};
		FileUtil.csvCreater(FILE_NAME,header);
	}



	public Boolean checkAvailableById(int id) {
		for (Booking booking : getAll()) {

			if (booking.getSeat().getSeatId() == id && booking.getIsAvailable() == false) {
				System.out.println("The seat is already booked.Please book another seat");
				return false;
			}
		}
		return true;
	}



	public void displayBookingbyCustomer(int id) {
		for (Booking booking : getAll()) {
			if (booking.getCustomer().getId() == id) {
				System.out.println(booking);
			}
		}
	}

	public int getBookingID() {
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

	public int checkBookingID(int id) {
		int checkedId = 0;
		try {
			Booking checkBooking = findById(id);
			checkedId = checkBooking.getId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Booking found!Please enter valid ID\n");
			return checkBookingID(getBookingID());
		}
		return checkedId;
	}

	@Override
	public Booking toObj(String[] row) {
		return Booking.toObj(row);
	}

	@Override
	public String getFileName() {
		return FILE_NAME;
	}
}
