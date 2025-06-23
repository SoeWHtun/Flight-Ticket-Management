package com.example.service;

import com.example.dao.booking.BookingDao;
import com.example.dao.booking.BookingDaoImpl;
import com.example.model.Booking;

import static com.example.dao.booking.BookingDaoImpl.bookingDao;

public class BookingService extends CommonService<Booking>{
	private static final BookingDao bookingDaoImpl = new BookingDaoImpl();

	public BookingService() {
		super(bookingDaoImpl, "Booking");
	}

	@Override
	public void prepareUpdateFields(Booking updateObject, Booking booking ) {
		updateObject.setCustomer(booking.getCustomer());
		updateObject.setFlight(booking.getFlight());
		updateObject.setSchedule(booking.getSchedule());
		updateObject.setSeat(booking.getSeat());
		updateObject.setIsAvailable(booking.getIsAvailable());
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

}
