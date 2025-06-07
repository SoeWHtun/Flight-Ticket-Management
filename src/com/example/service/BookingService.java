package com.example.service;

import com.example.dao.BookingDAO;
import com.example.model.Booking;
import com.example.util.FileUtil;

public class BookingService {
	public static void createBooking(Booking booking) {

		BookingDAO.addBooking(booking);
	}

	public static void updateBooking(int id, Booking booking) {
		Booking updateBooking = BookingDAO.findById(id);
		updateBooking.setCustomer(booking.getCustomer());
		updateBooking.setFlight(booking.getFlight());
		updateBooking.setSchedule(booking.getSchedule());
		updateBooking.setSeat(booking.getSeat());
		updateBooking.setIsAvailable(booking.getIsAvailable());
//		FileUtil.csvUpdater("booking.csv",updateBooking.toArray());

	}
}
