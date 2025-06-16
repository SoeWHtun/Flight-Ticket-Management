package com.example.model;

import com.example.dao.seat.SeatDaoImpl;

import static com.example.dao.customer.CustomerDaoImpl.customerDao;
import static com.example.dao.booking.BookingDaoImpl.bookingDao;
import static com.example.dao.flight.FlightDAOImpl.flightDAO;
import static com.example.dao.schedule.SchduleDaoImpl.schduleDao;

public class Booking extends MasterData{
    private Customer customer;
    private Flight flight;
    private Schedule schedule;
    private Seat seat;
    private Boolean isAvailable;
    static SeatDaoImpl seatDao = new SeatDaoImpl();
    public Booking(){

    }

    public Booking(Customer customer, Flight flight, Schedule schedule, Seat seat) {
        super.setId(bookingDao.getCount() + 1);
        this.customer = customer;
        this.flight = flight;
        this.schedule = schedule;
        this.seat = seat;
        this.isAvailable = false;
    }

    public Booking(int bookingId, Customer customer, Flight flight, Schedule schedule, Seat seat) {
        super.setId(bookingId);
        this.customer = customer;
        this.flight = flight;
        this.schedule = schedule;
        this.seat = seat;
        this.isAvailable = false;
    }


    public static Booking toObj(String[] bookingRow) {
        int bookingId = Integer.parseInt(bookingRow[0]);
        int customerId = Integer.parseInt(bookingRow[1]);
        int flightId = Integer.parseInt(bookingRow[2]);
        int scheduleId = Integer.parseInt(bookingRow[3]);
        int seatId = Integer.parseInt(bookingRow[4]);
        Customer nCustomer = customerDao.findById(customerId);
        Flight nFlight = flightDAO.findById(flightId);
        Schedule nSchedule = schduleDao.findById(scheduleId);
        Seat nSeat = seatDao.findById(seatId);
        return new Booking(bookingId, nCustomer, nFlight, nSchedule, nSeat);
    }


    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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

    public String[] toArray() {
        return new String[]{this.customer.getId() + "", this.flight.getId() + "", this.schedule.getScheduleId() + "", this.seat.getSeatId() + ""};
    }

    @Override
    public String toString() {
        String str = "\n[ "+this.getId() +"\n"+ getCustomer() + "\n" + getSchedule() + "\n"
                + getSeat();
        return str;
    }

}
