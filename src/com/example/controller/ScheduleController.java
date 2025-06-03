package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.dao.FlightDAO;
import com.example.dao.SchduleDAO;

import com.example.model.Flight;
import com.example.model.Schedule;
import com.example.service.FlightService;
import com.example.service.ScheduleService;

public class ScheduleController {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	public static ScheduleService scheduleService = new ScheduleService();
	public static FlightService flightService = new FlightService();

	public void call() throws NumberFormatException, IOException {
		int choice;
		do {

			System.out.println("\n**Schedule Management**");

			System.out.println("1. Schedule Registration ");
			System.out.println("2. Update schedule detail ");
			System.out.println("3. Find schedule by Id");
			System.out.println("4. View all schedule");
			System.out.println("5. Delete schedule");

			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			choice = Integer.parseInt(bufferedReader.readLine());

			switch (choice) {
			case 1 -> create();
			case 2 -> edit();
			case 3 -> findById();
			case 4 -> viewAll();
			case 5 -> delete();
			case 6 -> System.out.println("Exited");
			default -> System.out.println("Invalid choice! Please select a valid option.");
			}

		} while (choice != 6);
		System.out.println();
	}

	private void delete() throws IOException {
		System.out.print("Enter schedule ID: ");
		int id = Integer.parseInt(bufferedReader.readLine());
		SchduleDAO.deleteSchedule(id);
		SchduleDAO.displaySchedule();
	}

	private void viewAll() {
		SchduleDAO.displaySchedule();
	}

	private void findById() throws IOException {
		System.out.print("Enter schedule ID: ");
		int id = Integer.parseInt(bufferedReader.readLine());
		Schedule foundSchedule = SchduleDAO.findById(id);
		System.out.println(foundSchedule);
	}

	private void edit() throws IOException {
		System.out.print("Enter schedule ID: ");
		int id = Integer.parseInt(bufferedReader.readLine());
		System.out.print("Enter schedule title: ");
		String title = bufferedReader.readLine();
		FlightDAO.displayFlight();
		System.out.print("Enter flight Id: ");
		int fid = Integer.parseInt(bufferedReader.readLine());
		Flight foundFlight = FlightDAO.findById(fid);
		System.out.print("Enter departure date and time (yyyy-MM-dd HH:mm): ");
		String deptDateTimeStr = bufferedReader.readLine();
		LocalDateTime deptDate = LocalDateTime.parse(deptDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		System.out.print("Enter arrival date and time (yyyy-MM-dd HH:mm): ");
		String arrivalDateTimeStr = bufferedReader.readLine();
		LocalDateTime arrivalDate = LocalDateTime.parse(arrivalDateTimeStr,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		System.out.print("Enter departure city: ");
		String deptCity = bufferedReader.readLine();
		System.out.print("Enter arrival city: ");
		String arrivalCity = bufferedReader.readLine();

		Schedule updateSchedule = new Schedule(title, foundFlight, deptDate, arrivalDate, deptCity, arrivalCity);
		ScheduleService.updateSchedule(id, updateSchedule);
		System.out.println("Schedule Details Updated\n");
		Schedule foundSchedule = SchduleDAO.findById(id);
		System.out.println(foundSchedule);
	}

	private void create() throws IOException {
		System.out.print("Enter schedule title: ");
		String title = bufferedReader.readLine();
		FlightDAO.displayFlight();
		System.out.print("Enter flight Id: ");
		int id = Integer.parseInt(bufferedReader.readLine());
		Flight foundFlight = FlightDAO.findById(id);
		System.out.print("Enter departure date and time (yyyy-MM-dd HH:mm): ");
		String deptDateTimeStr = bufferedReader.readLine();
		LocalDateTime deptDate = LocalDateTime.parse(deptDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		System.out.print("Enter arrival date and time (yyyy-MM-dd HH:mm): ");
		String arrivalDateTimeStr = bufferedReader.readLine();
		LocalDateTime arrivalDate = LocalDateTime.parse(arrivalDateTimeStr,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		System.out.print("Enter departure city: ");
		String deptCity = bufferedReader.readLine();
		System.out.print("Enter arrival city: ");
		String arrivalCity = bufferedReader.readLine();

		Schedule newSchedule = new Schedule(title, foundFlight, deptDate, arrivalDate, deptCity, arrivalCity);
		ScheduleService.createSchedule(newSchedule);
		System.out.println("New Schedule Created\n");
		System.out.println(newSchedule);

	}

}
