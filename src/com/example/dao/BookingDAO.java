package com.example.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Booking;
import com.example.model.Flight;
import com.example.util.FileUtil;

public class BookingDAO {
	private static Booking[] bookingDB = new Booking[1000];
	private static int bookingCount = 0;
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	static {
		String[] header = {"Id", "customerId", "flightId", "scheduleId","seatId"};
		FileUtil.csvCreater("booking.csv",header);
	}
	public static int getBookingCount() {
		return bookingCount;
	}

	public static int getBookingCSVId(){
		List<Booking> bookingList = getAllBooking();
		if(bookingList.size()>0){
			bookingList.sort((c1, c2)-> Integer.compare(c1.getBookingId(), c2.getBookingId()));
			return bookingList.getLast().getBookingId() + 1;}
		else{
			return 1;
		}

	}
	public static List<Booking> getAllBooking(){
		List<String[]> bookingData = FileUtil.csvReader("booking.csv");
		List<Booking> bookingList = toBookings(bookingData);
		return bookingList;
	}
	private static List<Booking> toBookings(List<String[]> bookingsData) {
		List<Booking> bookingList = new ArrayList<>();
		for(String[] bookingRow : bookingsData) {
			Booking booking = Booking.toObj(bookingRow);
			bookingList.add(booking);
		}
		return bookingList;
	}

	public static void addBooking(Booking booking) {
		bookingCount++;
		booking.setBookingId(getBookingCSVId());
		FileUtil.csvWriter("booking.csv", booking.toArray());

	}

	public static Booking findById(int id) {
		for (Booking booking : getAllBooking()) {
			if (booking.getBookingId() == id) {
				return booking;
			}
		}

		return null;
	}

	public static Boolean checkAvailableById(int id) {
		for (Booking booking : getAllBooking()) {

			if (booking.getSeat().getSeatId() == id && booking.getIsAvailable() == false) {
				System.out.println("The seat is already booked.Please book another seat");
				return false;
			}
		}
		return true;

	}

	public static void displayBooking() {
		for (Booking booking : getAllBooking()) {
			System.out.println(booking);
		}
	}

	public static void displayBookingbyCustomer(int id) {
		for (Booking booking : getAllBooking()) {
			if (booking.getCustomer().getCustomerId() == id) {
				System.out.println(booking);
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

	public static int getBookingID() {
		int bookingID;
		try {
			System.out.print("Enter your booking ID: ");
			bookingID = Integer.parseInt(bufferedReader.readLine());
		} catch (IOException ex) {
			System.out.println("Please enter valid value");
			return getBookingID();
		} catch (NumberFormatException ex) {
			System.out.println("Please enter valid ID");
			return getBookingID();
		} catch (NullPointerException ex) {
			System.out.println("Please enter valid ID");
			return getBookingID();
		}
		return bookingID;
	}

	public static int checkBookingID(int id) {
		int checkedId = 0;
		try {
			Booking checkBooking = findById(id);
			checkedId = checkBooking.getBookingId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Booking found!Please enter valid ID\n");
			return checkBookingID(getBookingID());
		}
		return checkedId;
	}
}
