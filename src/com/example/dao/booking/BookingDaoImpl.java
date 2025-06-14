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

	private static Booking[] bookingDB = new Booking[1000];
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	public static BookingDaoImpl bookingDao = new BookingDaoImpl();
	static {
		String[] header = {"Id", "customerId", "flightId", "scheduleId","seatId"};
		FileUtil.csvCreater(FILE_NAME,header);
	}
    public int getBookingCount(){
		int count=0;
		for(Booking booking : getAll()){
			count++;
		}
		return count;
	}
	public int getBookingCSVId(){
		List<Booking> bookingList = getAll();
		if(bookingList.size()>0){
			bookingList.sort((c1, c2)-> Integer.compare(c1.getBookingId(), c2.getBookingId()));
			return bookingList.getLast().getBookingId() + 1;}
		else{
			return 1;
		}
	}

	private static List<Booking> toBookings(List<String[]> bookingsData) {
		List<Booking> bookingList = new ArrayList<>();
		for(String[] bookingRow : bookingsData) {
			Booking booking = Booking.toObj(bookingRow);
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
			if (booking.getCustomer().getCustomerId() == id) {
				System.out.println(booking);
			}
		}
	}

	public static Booking[] getBookingDB() {
		return bookingDB;
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
			checkedId = checkBooking.getBookingId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Booking found!Please enter valid ID\n");
			return checkBookingID(getBookingID());
		}
		return checkedId;
	}

	@Override
	public void create(Booking booking) {

		booking.setBookingId(getBookingCSVId());
		FileUtil.csvWriter(FILE_NAME, booking.toArray());
	}

	@Override
	public Booking findById(int id) {
		for (Booking booking : getAll()) {
			if (booking.getBookingId() == id) {
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
		List<Booking> bookingList = toBookings(bookingData);
		return bookingList;
	}
}
