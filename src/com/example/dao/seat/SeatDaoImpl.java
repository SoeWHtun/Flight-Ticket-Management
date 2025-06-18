package com.example.dao.seat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.example.model.Seat;
import com.example.util.FileUtil;


public class SeatDaoImpl implements SeatDAO {
	private static Seat[] seatDB = new Seat[2000];
	private static int seatCount = 0;
	public static  SeatDaoImpl seatDao = new SeatDaoImpl();
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

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
