package com.example.dao.booking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.AbstractDao;
import com.example.model.Booking;
import com.example.util.FileUtil;

import static com.example.dao.booking.BookingDao.FILE_NAME;

public class BookingDaoImpl implements AbstractDao<Booking> {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	public static BookingDaoImpl bookingDao = new BookingDaoImpl();
	static {
		String[] header = {"Id", "customerId", "flightId", "scheduleId","seatId"};
		FileUtil.csvCreater(FILE_NAME,header);
	}
    @Override
	public List<Booking> toObjects(List<String[]> bookingsData) {
		List<Booking> bookingList = new ArrayList<>();
		Booking nBooking = new Booking();
		for(String[] bookingRow : bookingsData) {
			Booking booking = nBooking.toObj(bookingRow);
			bookingList.add(booking);
		}
		return bookingList;
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

	public void displayBooking() {
		for (Booking booking : getAll()) {
			System.out.println(booking);
		}
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
	public void create(Booking booking) {

		booking.setId(getCSVId());
		FileUtil.csvWriter(FILE_NAME, booking.toArray());
	}

	@Override
	public Booking findById(int id) {
		for (Booking booking : getAll()) {
			if (booking.getId() == id) {
				return booking;
			}
		}

		return null;
	}

	@Override
	public void update(int id, Booking booking) {
		FileUtil.updateRecordById(FILE_NAME,id+"",booking.toArray());
	}

	@Override
	public void delete(int id) {
		FileUtil.deleteRecordById(FILE_NAME,id+"");
	}

	@Override
	public List<Booking> getAll() {
		List<String[]> bookingData = FileUtil.csvReader(FILE_NAME);
		List<Booking> bookingList = toObjects(bookingData);
		return bookingList;
	}
}
