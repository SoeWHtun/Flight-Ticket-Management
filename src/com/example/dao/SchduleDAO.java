package com.example.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import com.example.model.Flight;
import com.example.model.Schedule;

public class SchduleDAO {
	private static Schedule[] scheduleDB = new Schedule[1000];
	private static int scheduleCount = 0;
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	static {

		Flight f1 = FlightDAO.findById(1);
		addSchedule(new Schedule("MDY to BKK Route", f1, LocalDateTime.of(2025, 7, 11, 12, 30),
				LocalDateTime.of(2025, 7, 11, 13, 30), "MDY Myanmar", "BKK Thailand"));

		Flight f2 = FlightDAO.findById(2);
		addSchedule(new Schedule("BKK to YGN Route", f2, LocalDateTime.of(2025, 6, 2, 8, 30),
				LocalDateTime.of(2025, 6, 2, 9, 30), "BKK Thailand", "Yangon Myanmar"));

		Flight f3 = FlightDAO.findById(3);
		addSchedule(new Schedule("YGN to SIN Route", f3, LocalDateTime.of(2025, 6, 5, 10, 0),
				LocalDateTime.of(2025, 6, 5, 13, 0), "Yangon Myanmar", "Singapore"));

		Flight f4 = FlightDAO.findById(4);
		addSchedule(new Schedule("SIN to TPE Route", f4, LocalDateTime.of(2025, 6, 6, 9, 0),
				LocalDateTime.of(2025, 6, 6, 14, 0), "Singapore", "Taipei Taiwan"));

		Flight f5 = FlightDAO.findById(5);
		addSchedule(new Schedule("TPE to MDY Route", f5, LocalDateTime.of(2025, 6, 7, 13, 30),
				LocalDateTime.of(2025, 6, 7, 17, 30), "Taipei Taiwan", "MDY Myanmar"));

		Flight f6 = FlightDAO.findById(6);
		addSchedule(new Schedule("MDY to NPT Route", f6, LocalDateTime.of(2025, 6, 8, 7, 0),
				LocalDateTime.of(2025, 6, 8, 8, 0), "MDY Myanmar", "NPT Myanmar"));

		Flight f7 = FlightDAO.findById(7);
		addSchedule(new Schedule("NPT to BKK Route", f7, LocalDateTime.of(2025, 6, 9, 11, 0),
				LocalDateTime.of(2025, 6, 9, 12, 0), "NPT Myanmar", "BKK Thailand"));

		Flight f8 = FlightDAO.findById(8);
		addSchedule(new Schedule("BKK to TPE Route", f8, LocalDateTime.of(2025, 6, 10, 14, 0),
				LocalDateTime.of(2025, 6, 10, 18, 0), "BKK Thailand", "Taipei Taiwan"));

		Flight f9 = FlightDAO.findById(9);
		addSchedule(new Schedule("TPE to YGN Route", f9, LocalDateTime.of(2025, 6, 11, 6, 30),
				LocalDateTime.of(2025, 6, 11, 10, 0), "Taipei Taiwan", "Yangon Myanmar"));

		Flight f10 = FlightDAO.findById(10);
		addSchedule(new Schedule("YGN to MDY Route", f10, LocalDateTime.of(2025, 6, 12, 16, 0),
				LocalDateTime.of(2025, 6, 12, 17, 30), "Yangon Myanmar", "MDY Myanmar"));
	}

	public static int getScheduleCount() {
		return scheduleCount;
	}

	public static void addSchedule(Schedule schedule) {
		scheduleCount++;
		scheduleDB[scheduleCount - 1] = schedule;

	}

	public static Schedule findById(int id) {
		for (int i = 0; i < scheduleCount; i++) {
			if (scheduleDB[i].getScheduleId() == id) {
				return scheduleDB[i];
			}
		}

		return null;
	}

	public static Schedule findByRoute(String dept, String arrival) {
		for (int i = 0; i < scheduleCount; i++) {
			if (scheduleDB[i].getDepatureCity().equalsIgnoreCase(dept)
					&& scheduleDB[i].getArrivalCity().equalsIgnoreCase(arrival)) {
				return scheduleDB[i];
			}
		}

		return null;
	}

	public static void displaySchedule() {
		for (int i = 0; i < scheduleCount; i++) {
			System.out.println(scheduleDB[i]);

		}
	}

	public static void deleteSchedule(int id) {
		for (int i = 0; i < scheduleCount; i++) {
			if (scheduleDB[i].getScheduleId() == id) {
				int j = i;
				while (scheduleCount > j) {
					scheduleDB[j] = scheduleDB[j + 1];
					j++;
				}
				scheduleCount--;
				return;

			}
		}
	}

	public static int getScheduleID() {
		int scheduleID;
		try {
			System.out.print("Enter schedule ID: ");
			scheduleID = Integer.parseInt(bufferedReader.readLine());
		} catch (IOException ex) {
			System.out.println("Please enter valid value");
			return getScheduleID();
		} catch (NumberFormatException ex) {
			System.out.println("Please enter valid ID");
			return getScheduleID();
		}
		return scheduleID;
	}

	public static int checkScheduleID(int id) {
		int checkedId = 0;
		try {
			Schedule checkSchedule = findById(id);
			checkedId = checkSchedule.getScheduleId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Schedule found!Please enter valid ID\n");
			return checkScheduleID(getScheduleID());
		}
		return checkedId;
	}

}
