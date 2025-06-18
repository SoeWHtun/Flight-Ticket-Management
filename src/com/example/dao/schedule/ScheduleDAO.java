package com.example.dao.schedule;

import com.example.dao.AbstractDao;
import com.example.model.Schedule;

public abstract class ScheduleDAO extends AbstractDao<Schedule> {
    public static final String FILE_NAME = "schedule.csv";
}
