package com.example.dao.seat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Seat;
import com.example.util.FileUtil;


public class SeatDaoImpl implements SeatDAO {
	private static Seat[] seatDB = new Seat[2000];
	private static int seatCount = 0;
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	static {
		String[] header = {"seatId","flightId","seatNumber"};
		FileUtil.csvCreater(FILE_NAME,header);
	}
	public static int getSeatCSVId(){
		List<Seat> seatList = getAllSeat();
		if(seatList.size()>0){
			seatList.sort((c1, c2)-> Integer.compare(c1.getSeatId(), c2.getSeatId()));
			return seatList.getLast().getSeatId() + 1;}
		else{
			return 1;
		}

	}
	public static List<Seat> getAllSeat(){
		List<String[]> seatData = FileUtil.csvReader(FILE_NAME);
		List<Seat> seatList = toSeats(seatData);
		return seatList;
	}
	private static List<Seat> toSeats(List<String[]> seatsData) {
		List<Seat> seatList = new ArrayList<>();
		Seat nSeat = new Seat();
		for(String[] seatRow : seatsData) {
			Seat seat = nSeat.toObj(seatRow);
			seatList.add(seat);
		}
		return seatList;
	}



	public static int getSeatCount() {
		return seatCount;
	}

	public static void addSeat(Seat seat) {
		seatCount++;
		seat.setSeatId(getSeatCSVId());
		FileUtil.csvWriter(FILE_NAME,seat.toArray());

	}

	@Override
	public Seat toObj(String[] row) {
		return Seat.toObj(row);
	}

	@Override
	public String getFileName() {
		return FILE_NAME;
	}

	public Seat findById(int id) {
		for (Seat seat : getAllSeat()) {
			if (seat.getSeatId() == id) {
				return seat;
			}
		}

		return null;
	}

	public static void displayByFlightId(int id) {
		for (Seat seat : getAllSeat()) {
			if (seat.getFlight().getId() == id) {
				System.out.println(seat);
			}
		}

	}

	public static void displayAllSeat() {
		for (Seat seat : getAllSeat()) {
			System.out.println(seat);
		}
	}

	public int getSeatID() {
		int seatID;
		try {
			System.out.print("Enter seat ID: ");
			seatID = Integer.parseInt(bufferedReader.readLine());
		} catch (IOException ex) {
			System.out.println("Please enter valid value");
			return getSeatID();
		} catch (NumberFormatException ex) {
			System.out.println("Please enter valid ID");
			return getSeatID();
		}
		return seatID;
	}

	public int checkSeatID(int id) {
		int checkedId = 0;
		try {
			Seat checkSeat = findById(id);
			checkedId = checkSeat.getSeatId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Seat found!Please enter valid ID\n");
			return checkSeatID(getSeatID());
		}
		return checkedId;
	}

}
