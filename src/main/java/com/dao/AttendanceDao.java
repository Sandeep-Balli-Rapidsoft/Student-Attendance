package com.dao;

import java.util.Date;
import java.util.List;

import com.entity.Attendance;

public interface AttendanceDao {
	
	public Attendance save(Attendance attendance);
	
	public List<Attendance> getAll();
	
	public List<Attendance> getWeekData(Date fromDate, Date toDate);
}
