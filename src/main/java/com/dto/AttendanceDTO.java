package com.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.modelmapper.ModelMapper;
import com.entity.Attendance;

public class AttendanceDTO {
    private Integer id;
    private Integer studentId;
    private Date day;
    private Boolean isActive;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    // Define a SimpleDateFormat for formatting dates
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedAtFormatted() {
        return dateFormat.format(createdAt);
    }

    public String getUpdatedAtFormatted() {
        return dateFormat.format(updatedAt);
    }
    
    public String getDayFormatted() {
        return dateFormat.format(day);
    }

    public AttendanceDTO() {
    }

    public AttendanceDTO(Integer id, Integer studentId, Date day, Boolean isActive, Date createdAt, Date updatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.day = new Date(getDayFormatted());
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AttendanceDTO toAttendanceDTO(Attendance attendance) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(attendance, AttendanceDTO.class);
    }

    public Attendance toAttendance(AttendanceDTO attendanceDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(attendanceDTO, Attendance.class);
    }
}
