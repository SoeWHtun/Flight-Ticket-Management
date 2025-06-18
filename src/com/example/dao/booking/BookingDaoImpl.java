package com.example.dao.booking;

import com.example.model.Booking;
import com.example.util.FileUtil;

public class BookingDaoImpl extends BookingDao {

	public static BookingDaoImpl bookingDao = new BookingDaoImpl();
	static {
		String[] header = {"Id", "customerId", "flightId", "scheduleId","seatId"};
		FileUtil.csvCreater(FILE_NAME,header);
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
