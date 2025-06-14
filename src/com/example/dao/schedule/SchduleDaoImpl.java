package com.example.dao.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.AbstractDao;
import com.example.model.Schedule;
import com.example.util.FileUtil;
import static com.example.dao.schedule.ScheduleDAO.SCHEDULE_FILE;

public class SchduleDaoImpl implements AbstractDao<Schedule> {
    public static SchduleDaoImpl schduleDao = new SchduleDaoImpl();
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    static {
        String[] header = {"scheduleId", "scheduleTitle", "flightId", "depatureDay", "depatureMonth", "depatureYear", "depatureHour", "depatureMinute", "arrivalDay", "arrivalMonth", "arrivalYear", "arrivalHour", "arrivalMinute", "depatureCity", "arrivalCity"};
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

    private static List<Schedule> toSchedules(List<String[]> schedulesData) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (String[] scheduleRow : schedulesData) {
            Schedule schedule = Schedule.toObj(scheduleRow);
            scheduleList.add(schedule);
        }
        return scheduleList;
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

    public static void updateSchedule(int id, Schedule updateSchedule) {
        FileUtil.updateRecordById("schedule.csv", id + "", updateSchedule.toArray());
    }

    @Override
    public void create(Schedule schedule) {

        schedule.setScheduleId(getScheduleCSVId());
        FileUtil.csvWriter(SCHEDULE_FILE, schedule.toArray());
    }

    @Override
    public Schedule findById(int id) {
        for (Schedule schedule : getAll()) {
            if (schedule.getScheduleId() == id) {
                return schedule;
            }
        }

        return null;
    }

    @Override
    public void update(int id, Schedule schedule) {
        FileUtil.updateRecordById(SCHEDULE_FILE, id + "", schedule.toArray());
    }

    @Override
    public void delete(int id) {
        FileUtil.deleteRecordById(SCHEDULE_FILE, id + "");
    }

    @Override
    public List<Schedule> getAll() {
        List<String[]> scheduleData = FileUtil.csvReader(SCHEDULE_FILE);
        List<Schedule> scheduleList = toSchedules(scheduleData);
        return scheduleList;
    }
}
