package com.example.service;

import com.example.dao.booking.BookingDaoImpl;
import com.example.model.Booking;

import static com.example.dao.booking.BookingDaoImpl.bookingDao;

public class BookingService {
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
}
