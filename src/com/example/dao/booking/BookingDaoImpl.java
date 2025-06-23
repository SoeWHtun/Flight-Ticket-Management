package com.example.dao.booking;

import com.example.model.Booking;
import com.example.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

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

    public List<Booking> getBookingByFlightIdAndSeatId(int flightId, int seatId) {
		List<Booking> bookingList = new ArrayList<>();
		for(Booking booking : getAll()){
			if(booking.getSeat().getId() == seatId && booking.getFlight().getId()== flightId){
				bookingList.add(booking);
			}
		}
		return bookingList;
    }
}
