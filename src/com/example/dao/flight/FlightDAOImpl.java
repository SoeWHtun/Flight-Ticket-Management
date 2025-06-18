package com.example.dao.flight;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.example.model.Flight;
import com.example.util.FileUtil;

public class FlightDAOImpl extends FlightDao {
    public static FlightDAOImpl flightDAO = new FlightDAOImpl();
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    static {
        String[] header = {"FlightId", "FlightName", "FlightNumber"};
        FileUtil.csvCreater(FILE_NAME, header);

    }







    public Flight toObj(String[] row) {
        return Flight.toObj(row);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
