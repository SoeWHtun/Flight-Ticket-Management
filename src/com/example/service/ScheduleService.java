package com.example.service;


import com.example.dao.schedule.SchduleDaoImpl;
import com.example.dao.schedule.ScheduleDAO;
import com.example.model.Schedule;

import static com.example.dao.schedule.SchduleDaoImpl.schduleDao;

public class ScheduleService extends CommonService<Schedule> {
    private static final ScheduleDAO scheduleDaoImpl = new SchduleDaoImpl();

    public ScheduleService() {
        super(scheduleDaoImpl, "Schedule");
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

    @Override
    public void prepareUpdateFields(Schedule updateSchedule, Schedule schedule) {
        updateSchedule.setTitle(schedule.getTitle());
        updateSchedule.setDepatureDate(schedule.getDepatureDate());
        updateSchedule.setArrivalDate(schedule.getArrivalDate());
        updateSchedule.setDepatureCity(schedule.getDepatureCity());
        updateSchedule.setArrivalCity(schedule.getArrivalCity());
    }
}
