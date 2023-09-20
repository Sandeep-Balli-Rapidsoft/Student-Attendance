package com.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.entity.Student;

@Component
public class ExportAttendanceDto {

	private String name;

	private Date date;

	private String isActive;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public ExportAttendanceDto(String name, Date date, String isActive) {
		super();
		this.name = name;
		this.date = date;
		this.isActive = isActive;
	}

	public ExportAttendanceDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
