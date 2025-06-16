package com.example.dao.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import com.example.model.Schedule;
import com.example.util.FileUtil;

public class SchduleDaoImpl implements ScheduleDAO {
    public static SchduleDaoImpl schduleDao = new SchduleDaoImpl();
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    static {
        String[] header = {"scheduleId", "scheduleTitle", "flightId", "depatureDay", "depatureMonth", "depatureYear", "depatureHour",
                "depatureMinute", "arrivalDay", "arrivalMonth", "arrivalYear", "arrivalHour", "arrivalMinute", "depatureCity", "arrivalCity"};
        FileUtil.csvCreater(SCHEDULE_FILE, header);
    }

    public int getScheduleCount() {
        int count = 0;
        for (Schedule schedule : getAll()) {
            count++;
        }
        return count;
    }

    public int getScheduleCSVId() {
        List<Schedule> scheduleList = getAll();
        if (scheduleList.size() > 0) {
            scheduleList.sort((c1, c2) -> Integer.compare(c1.getScheduleId(), c2.getScheduleId()));
            return scheduleList.getLast().getScheduleId() + 1;
        } else {
            return 1;
        }

    }

    public Schedule findByRoute(String dept, String arrival) {
        for (Schedule schedule : getAll()) {
            if (schedule.getDepatureCity().equalsIgnoreCase(dept)
                    && schedule.getArrivalCity().equalsIgnoreCase(arrival)) {
                return schedule;
            }
        }

        return null;
    }

    public void displaySchedule() {
        for (Schedule schedule : getAll()) {
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

    public int checkScheduleID(int id) {
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

    @Override
    public Schedule toObj(String[] row) {
        return Schedule.toObj(row);
    }

    @Override
    public String getFileName() {
        return getFileName();
    }
}
