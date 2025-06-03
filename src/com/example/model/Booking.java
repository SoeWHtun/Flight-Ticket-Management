package com.example.model;

import com.example.dao.BookingDAO;

public class Booking {
	private int bookingId;
	private Customer customer;
	private Flight flight;
	private Schedule schedule;
	private Seat seat;
	private Boolean isAvailable;

	public Booking(Customer customer, Flight flight, Schedule schedule, Seat seat) {
		this.bookingId = BookingDAO.getBookingCount() + 1;
		this.customer = customer;
		this.flight = flight;
		this.schedule = schedule;
		this.seat = seat;
		this.isAvailable = false;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		String str = "\n[ Booking ID: " + getBookingId() + " ]" + "\n" + getCustomer() + "\n" + getSchedule() + "\n"
				+ getSeat();
		return str;
	}

}
