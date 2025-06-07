package com.example.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Booking;
import com.example.model.Customer;
import com.example.model.Flight;
import com.example.model.Seat;
import com.example.util.FileUtil;

public class FlightDAO {
	private static Flight[] flightDB = new Flight[1000];
	private static int flightCount = 0;
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	static {
		String[] header = {"FlightId","FlightName","FlightNumber"};
		FileUtil.csvCreater("flight.csv",header);

	}
	public static int getFlightCSVId(){
		List<Flight> flightList = getAllFlight();
		if(flightList.size()>0){
		flightList.sort((c1, c2)-> Integer.compare(c1.getFlightId(), c2.getFlightId()));
		return flightList.getLast().getFlightId() + 1;}
		else{
			return 1;
		}

	}
	public static List<Flight> getAllFlight(){
		List<String[]> flightData = FileUtil.csvReader("flight.csv");
		List<Flight> flightList = toFlights(flightData);
		return flightList;
	}
	private static List<Flight> toFlights(List<String[]> flightsData) {
		List<Flight> flightList = new ArrayList<>();
		for(String[] flightRow : flightsData) {
			Flight flight = Flight.toObj(flightRow);
			flightList.add(flight);
		}
		return flightList;
	}

	public static int getFlightCount() {
		return flightCount;
	}

	public static void addFlight(Flight flight) {
		flightCount++;
		flight.setFlightId(getFlightCSVId());
		FileUtil.csvWriter("flight.csv",flight.toArray());

	}

	public static Flight findById(int id) {
		for (Flight flight : getAllFlight()) {
			if (flight.getFlightId() == id) {
				return flight;
			}
		}

		return null;
	}



	public static void displayFlight() {
		for (Flight flight : getAllFlight()) {
			System.out.println(flight);

		}
	}

	public static void deleteFlight(int id) {
		for (int i = 0; i < flightCount; i++) {
			if (flightDB[i].getFlightId() == id) {
				int j = i;
				while (flightCount > j) {
					flightDB[j] = flightDB[j + 1];
					j++;
				}
				flightCount--;
				return;

			}
		}
	}

	public static void displayFlightwithSeat() {
		boolean found = false;
		for (Flight flights : getAllFlight()) {
			Flight flight = flights;
			boolean hasAvailableSeat = false;
			for (Seat seat : SeatDAO.getAllSeat()) {
				if (seat.getFlight().getFlightId() == flight.getFlightId()) {
					boolean isBooked = false;
					for (Booking booking : BookingDAO.getAllBooking()) {
						if (booking.getSeat().getSeatId() == seat.getSeatId()
								&& booking.getIsAvailable() == false) {
							isBooked = true;
							break;
						}
					}
					if (!isBooked) {
						hasAvailableSeat = true;
						break;
					}

				}

			}
			if (hasAvailableSeat) {
				System.out.println(flight);
				found = true;
			}

		}
		if (!found) {
			System.out.println("No flights with available seats found.");
		}

	}

	public static int getFlightID() {
		int flightID;
		try {
			System.out.print("Enter flight ID: ");
			flightID = Integer.parseInt(bufferedReader.readLine());
		} catch (IOException ex) {
			System.out.println("Please enter valid value");
			return getFlightID();
		} catch (NumberFormatException ex) {
			System.out.println("Please enter valid ID");
			return getFlightID();
		}
		return flightID;
	}

	public static int checkFlightID(int id) {
		int checkedId = 0;
		try {
			Flight checkFlight = findById(id);
			checkedId = checkFlight.getFlightId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Flight found!Please enter valid ID\n");
			return checkFlightID(getFlightID());
		}
		return checkedId;
	}
}
