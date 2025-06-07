package com.example.service;

import com.example.dao.SchduleDAO;

import com.example.model.Schedule;
import com.example.util.FileUtil;

public class ScheduleService {

	public static void createSchedule(Schedule schedule) {
		SchduleDAO.addSchedule(schedule);
	}

	public static void updateSchedule(int id, Schedule schedule) {
		Schedule updateSchedule = SchduleDAO.findById(id);
		updateSchedule.setTitle(schedule.getTitle());
		updateSchedule.setDepatureDate(schedule.getDepatureDate());
		updateSchedule.setArrivalDate(schedule.getArrivalDate());
		updateSchedule.setDepatureCity(schedule.getDepatureCity());
		updateSchedule.setArrivalCity(schedule.getArrivalCity());
        SchduleDAO.updateSchedule(id,updateSchedule);
	}

}
