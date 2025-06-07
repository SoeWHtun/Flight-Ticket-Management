package com.example.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Booking;
import com.example.model.Flight;
import com.example.model.Schedule;
import com.example.util.FileUtil;

public class SchduleDAO {
	private static Schedule[] scheduleDB = new Schedule[1000];
	private static int scheduleCount = 0;
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	static {

		String[] header = {"scheduleId","scheduleTitle","flightId","depatureDay","depatureMonth","depatureYear","depatureHour","depatureMinute","arrivalDay","arrivalMonth","arrivalYear","arrivalHour","arrivalMinute","depatureCity","arrivalCity"};
		FileUtil.csvCreater("schedule.csv",header);}

	public static int getScheduleCount() {
		return scheduleCount;
	}
	public static int getScheduleCSVId(){
		List<Schedule> scheduleList = getAllSchedule();
		if(scheduleList.size()>0){
			scheduleList.sort((c1, c2)-> Integer.compare(c1.getScheduleId(), c2.getScheduleId()));
			return scheduleList.getLast().getScheduleId() + 1;}
		else{
			return 1;
		}

	}
	public static List<Schedule> getAllSchedule(){
		List<String[]> scheduleData = FileUtil.csvReader("schedule.csv");
		List<Schedule> scheduleList = toSchedules(scheduleData);
		return scheduleList;
	}
	private static List<Schedule> toSchedules(List<String[]> schedulesData) {
		List<Schedule> scheduleList = new ArrayList<>();
		for(String[] scheduleRow : schedulesData) {
			Schedule schedule = Schedule.toObj(scheduleRow);
			scheduleList.add(schedule);
		}
		return scheduleList;
	}

	public static void addSchedule(Schedule schedule) {
		scheduleCount++;
		schedule.setScheduleId(getScheduleCSVId());
		FileUtil.csvWriter("schedule.csv",schedule.toArray());

	}

	public static Schedule findById(int id) {
		for (Schedule schedule : getAllSchedule()) {
			if (schedule.getScheduleId() == id) {
				return schedule;
			}
		}

		return null;
	}

	public static Schedule findByRoute(String dept, String arrival) {
		for (Schedule schedule : getAllSchedule()) {
			if (schedule.getDepatureCity().equalsIgnoreCase(dept)
					&& schedule.getArrivalCity().equalsIgnoreCase(arrival)) {
				return schedule;
			}
		}

		return null;
	}

	public static void displaySchedule() {
		for (Schedule schedule : getAllSchedule()) {
			System.out.println(schedule);

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
