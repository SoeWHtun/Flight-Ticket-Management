package com.example.service;



import com.example.model.Schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.dao.schedule.SchduleDaoImpl.schduleDao;

public class ScheduleService {
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	public static void createSchedule(Schedule schedule) {
		schduleDao.create(schedule);
	}

	public static void updateSchedule(int id, Schedule schedule) {
		Schedule updateSchedule = schduleDao.findById(id);
		updateSchedule.setTitle(schedule.getTitle());
		updateSchedule.setDepatureDate(schedule.getDepatureDate());
		updateSchedule.setArrivalDate(schedule.getArrivalDate());
		updateSchedule.setDepatureCity(schedule.getDepatureCity());
		updateSchedule.setArrivalCity(schedule.getArrivalCity());
        schduleDao.update(id,updateSchedule);
	}
	public static Schedule findByRoute(String dept, String arrival) {
		for (Schedule schedule : schduleDao.getAll()) {
			if (schedule.getDepatureCity().equalsIgnoreCase(dept)
					&& schedule.getArrivalCity().equalsIgnoreCase(arrival)) {
				return schedule;
			}
		}

		return null;
	}

	public static void displaySchedule() {
		for (Schedule schedule : schduleDao.getAll()) {
			System.out.println(schedule);

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
			Schedule checkSchedule = schduleDao.findById(id);
			checkedId = checkSchedule.getId();
		} catch (NullPointerException ex) {
			System.out.print("\nNo Schedule found!Please enter valid ID\n");
			return checkScheduleID(getScheduleID());
		}
		return checkedId;
	}

}
