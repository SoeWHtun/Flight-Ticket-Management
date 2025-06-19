package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.model.Booking;
import com.example.model.Customer;
import com.example.model.Schedule;
import com.example.model.Seat;
import com.example.service.BookingService;
import com.example.service.CustomerService;
import com.example.service.ScheduleService;
import com.example.service.SeatService;

import static com.example.dao.customer.CustomerDaoImpl.customerDao;
import static com.example.dao.booking.BookingDaoImpl.bookingDao;
import static com.example.dao.schedule.SchduleDaoImpl.schduleDao;
import static com.example.dao.seat.SeatDaoImpl.seatDao;

public class BookingController {
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    private static BookingService bookingService = new BookingService();
    private static CustomerService customerService = new CustomerService();
    private static ScheduleService scheduleService = new ScheduleService();
    private static  SeatService seatService = new SeatService();

    public void call() throws NumberFormatException, IOException {
        int choice;
        do {

            System.out.println("\n**Booking Management**");

            System.out.println("1. Create new booking by schdeule");
            System.out.println("2. Create new booking by route  ");
            System.out.println("3. View bookings");
            System.out.println("4. Update bookings");
            System.out.println("5. Cancel booking");
            System.out.println("6. Exit");
            choice = FlightTicketManagement.getChoice();

            switch (choice) {
                case 1 -> createBySchedule();
                case 2 -> createByRoute();
                case 3 -> viewBooking();
                case 4 -> updateBooking();
                case 5 -> cancel();
                case 6 -> System.out.println("Exited");
                default -> System.out.println("Invalid choice! Please select a valid option.");
            }

        } while (choice != 6);
        System.out.println();
    }

    private void updateBooking() throws IOException {
        int cCustomerId = customerService.findById();
        BookingService.displayBookingbyCustomer(cCustomerId);
        int bookingId = bookingService.findById();
        scheduleService.displayAll();
        int cId = scheduleService.findById();
        SeatService.displayByFlightId(cId);
        int seatId;
        int cSeatId;
        boolean seatAvailable;
        do {
            cSeatId = seatService.findById();
            seatAvailable = BookingService.checkAvailableById(cSeatId);
        } while (!seatAvailable);
        Customer customer = customerDao.findById(cCustomerId);
        Schedule schedule = schduleDao.findById(cId);
        Seat seat = seatDao.findById(cSeatId);
        Booking updateBooking = new Booking(customer, schedule.getFlight(), schedule, seat);
        bookingService.update(bookingId, updateBooking);
        System.out.println("\nBooking details updated\n");
        Booking foundBooking = bookingDao.findById(bookingId);
        System.out.println(foundBooking);
    }

    private void viewBooking() throws IOException {
        int cCustomerId = customerService.findById();
        BookingService.displayBookingbyCustomer(cCustomerId);
    }

    private void cancel() throws IOException {


        int cCustomerId = customerService.findById();
        BookingService.displayBookingbyCustomer(cCustomerId);
        int bookingId = bookingService.findById();
        bookingDao.delete(bookingId);
        System.out.println("\nBooking ID: " + bookingId + " (Cancelled)");
        BookingService.displayBookingbyCustomer(cCustomerId);
    }

    private void createByRoute() throws IOException {
        scheduleService.displayAll();
        System.out.print("Enter depature city: ");
        String dept = bufferedReader.readLine();
        System.out.print("Enter arrival city: ");
        String arrival = bufferedReader.readLine();
        Schedule foundSchedule = ScheduleService.findByRoute(dept, arrival);
        if (foundSchedule == null) {
            System.out.println("No schedule found for the given route.");
            return;
        }


        int cCustomerId = customerService.findById();
        SeatService.displayByFlightId(foundSchedule.getId());
        int seatId;
        int cSeatId;
        boolean seatAvailable;
        do {
            cSeatId = seatService.findById();
            seatAvailable = BookingService.checkAvailableById(cSeatId);
        } while (!seatAvailable);
        Customer customer = customerDao.findById(cCustomerId);
        Seat seat = seatDao.findById(cSeatId);
        Booking newBooking = new Booking(customer, foundSchedule.getFlight(), foundSchedule, seat);
        bookingService.create(newBooking);
        System.out.println("\nNew Booking Created\n");
        System.out.println(newBooking);

    }

    private void createBySchedule() throws IOException {
        scheduleService.displayAll();
        int cId = scheduleService.findById();

        int cCustomerId = customerService.findById();
        SeatService.displayByFlightId(cId);
        int cSeatId;
        boolean seatAvailable;
        do {
            cSeatId = seatService.findById();
            seatAvailable = BookingService.checkAvailableById(cSeatId);
        } while (!seatAvailable);
        Customer customer = customerDao.findById(cCustomerId);
        Schedule schedule = schduleDao.findById(cId);
        Seat seat = seatDao.findById(cSeatId);
        Booking newBooking = new Booking(customer, schedule.getFlight(), schedule, seat);
        bookingService.create(newBooking);
        System.out.println("\nNew Booking Created\n");
        System.out.println(newBooking);

    }
}
