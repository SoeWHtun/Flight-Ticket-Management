package com.example.service;

import com.example.dao.schedule.SchduleDaoImpl;

import com.example.model.Schedule;

import static com.example.dao.schedule.SchduleDaoImpl.schduleDao;

public class ScheduleService {

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

}
