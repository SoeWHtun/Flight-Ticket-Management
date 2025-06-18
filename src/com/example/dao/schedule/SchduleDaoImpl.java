package com.example.dao.schedule;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.example.model.Schedule;
import com.example.util.FileUtil;

public class SchduleDaoImpl extends ScheduleDAO {
    public static SchduleDaoImpl schduleDao = new SchduleDaoImpl();
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    static {
        String[] header = {"scheduleId", "scheduleTitle", "flightId", "depatureDay", "depatureMonth", "depatureYear", "depatureHour",
                "depatureMinute", "arrivalDay", "arrivalMonth", "arrivalYear", "arrivalHour", "arrivalMinute", "depatureCity", "arrivalCity"};
        FileUtil.csvCreater(FILE_NAME, header);
    }

    @Override
    public Schedule toObj(String[] row) {
        return Schedule.toObj(row);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
