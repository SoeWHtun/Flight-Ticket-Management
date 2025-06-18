package com.example.model;

import java.time.LocalDateTime;
import java.util.Arrays;



import static com.example.dao.flight.FlightDAOImpl.flightDAO;
import static com.example.dao.schedule.SchduleDaoImpl.schduleDao;

public class Schedule extends MasterData {

    private String title;
    private Flight flight;
    private LocalDateTime depatureDate;
    private LocalDateTime arrivalDate;
    private String depatureCity;
    private String arrivalCity;

    public Schedule(String title, Flight flight, LocalDateTime depatureDate, LocalDateTime arrivalDate,
                    String depatureCity, String arrivalCity) {
        super.setId(schduleDao.getCount() + 1);
        this.title = title;
        this.flight = flight;
        this.depatureDate = depatureDate;
        this.arrivalDate = arrivalDate;
        this.depatureCity = depatureCity;
        this.arrivalCity = arrivalCity;
    }

    public Schedule(int scheduleId, String title, Flight flight, LocalDateTime depatureDate, LocalDateTime arrivalDate,
                    String depatureCity, String arrivalCity) {
        super.setId(scheduleId);
        this.title = title;
        this.flight = flight;
        this.depatureDate = depatureDate;
        this.arrivalDate = arrivalDate;
        this.depatureCity = depatureCity;
        this.arrivalCity = arrivalCity;
    }

    public Schedule() {

    }

    public static Schedule toObj(String[] scheduleRow) {
        try {
            int scheduleId = Integer.parseInt(scheduleRow[0]);
            String title = scheduleRow[1];
            int flightId = Integer.parseInt(scheduleRow[2]);
            int deptDay = Integer.parseInt(scheduleRow[3]);
            int deptMonth = Integer.parseInt(scheduleRow[4]);
            int deptYear = Integer.parseInt(scheduleRow[5]);
            int deptHour = Integer.parseInt(scheduleRow[6]);
            int deptMinute = Integer.parseInt(scheduleRow[7]);
            int arrDay = Integer.parseInt(scheduleRow[8]);
            int arrMonth = Integer.parseInt(scheduleRow[9]);
            int arrYear = Integer.parseInt(scheduleRow[10]);
            int arrHour = Integer.parseInt(scheduleRow[11]);
            int arrMinute = Integer.parseInt(scheduleRow[12]);
            String deptCity = scheduleRow[13];
            String arrCity = scheduleRow[14];
            Flight nFlight = flightDAO.findById(flightId);
            LocalDateTime deptDate = LocalDateTime.of(deptYear, deptMonth, deptDay, deptHour, deptMinute);
            LocalDateTime arrDate = LocalDateTime.of(arrYear, arrMonth, arrDay, arrHour, arrMinute);
            return new Schedule(scheduleId, title, nFlight, deptDate, arrDate, deptCity, arrCity);
        } catch (NumberFormatException ex) {
            System.out.println(Arrays.toString(scheduleRow));
        }
        return null;
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

    public String[] toArray() {
        return new String[]{this.getId() + "", this.title + "", this.flight.getId() + "", this.depatureDate.getDayOfMonth() + "", this.depatureDate.getMonthValue() + "", this.depatureDate.getYear() + "", this.depatureDate.getHour() + "", this.depatureDate.getMinute() + "", this.arrivalDate.getDayOfMonth() + "", this.arrivalDate.getMonthValue() + "", this.arrivalDate.getYear() + "", this.arrivalDate.getHour() + "", this.arrivalDate.getMinute() + "", this.depatureCity, this.arrivalCity};
    }

    @Override
    public String toString() {
        String str = "\nSchedule ID: " + getId() + "\nSchedule Title: " + getTitle() + "\nFlight ID: "
                + getFlight().getId() + "\nFlight Name: " + getFlight().getFlightName() + "\nFlight Number: "
                + getFlight().getFlightNumber() + "\nDepature Date: " + getDepatureDate() + "\nArrival Date: "
                + getArrivalDate() + "\nDepature City: " + getDepatureCity() + "\nArrival City: " + getArrivalCity()
                + "\n";
        return str;
    }

}
