package com.example.model;

import java.time.LocalDateTime;

import com.example.dao.SchduleDAO;

public class Schedule {
	private int scheduleId;
	private String title;
	private Flight flight;
	private LocalDateTime depatureDate;
	private LocalDateTime arrivalDate;
	private String depatureCity;
	private String arrivalCity;

	public Schedule(String title, Flight flight, LocalDateTime depatureDate, LocalDateTime arrivalDate,
			String depatureCity, String arrivalCity) {
		this.scheduleId = SchduleDAO.getScheduleCount() + 1;
		this.title = title;
		this.flight = flight;
		this.depatureDate = depatureDate;
		this.arrivalDate = arrivalDate;
		this.depatureCity = depatureCity;
		this.arrivalCity = arrivalCity;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public LocalDateTime getDepatureDate() {
		return depatureDate;
	}

	public void setDepatureDate(LocalDateTime depatureDate) {
		this.depatureDate = depatureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDepatureCity() {
		return depatureCity;
	}

	public void setDepatureCity(String depatureCity) {
		this.depatureCity = depatureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	@Override
	public String toString() {
		String str = "\nSchedule ID: " + getScheduleId() + "\nSchedule Title: " + getTitle() + "\nFlight ID: "
				+ getFlight().getFlightId() + "\nFlight Name: " + getFlight().getFlightName() + "\nFlight Number: "
				+ getFlight().getFlightNumber() + "\nDepature Date: " + getDepatureDate() + "\nArrival Date: "
				+ getArrivalDate() + "\nDepature City: " + getDepatureCity() + "\nArrival City: " + getArrivalCity()
				+ "\n";
		return str;
	}

}
