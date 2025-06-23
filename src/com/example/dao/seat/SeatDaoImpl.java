package com.example.dao.seat;

import com.example.model.Seat;
import com.example.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

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


	public Integer getSeatsCountByFlightId(int id){
		return getSeatsByFlightId(id).size();
	}


    public List<Seat> getSeatsByFlightId(int id) {
		List<Seat> seatList = new ArrayList<>();
		for(Seat seat : this.getAll()){
			if(seat.getFlight().getId() == id){
				seatList.add(seat);
			}
		}
		return seatList;
    }
}
