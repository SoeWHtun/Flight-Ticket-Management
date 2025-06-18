package com.example.dao.seat;

import com.example.model.Seat;
import com.example.util.FileUtil;

public class SeatDaoImpl extends SeatDAO {

	public static SeatDaoImpl seatDao = new SeatDaoImpl();

	static {
		String[] header = {"seatId","flightId","seatNumber"};
		FileUtil.csvCreater(FILE_NAME,header);
	}

	@Override
	public Seat toObj(String[] row) {
		return Seat.toObj(row);
	}

	@Override
	public String getFileName() {
		return FILE_NAME;
	}




}
