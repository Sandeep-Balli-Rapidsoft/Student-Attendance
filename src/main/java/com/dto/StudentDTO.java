package com.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.entity.Student;

@Component
public class StudentDTO {

	private Integer id;

	private String name;

	private String email;

	private Date createdAt = new Date();

	private Date updatedAt = new Date();

	private Boolean isActive = true;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentDTO(Integer id, String name, String email, Date createdAt, Date updatedAt, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isActive = isActive;
	}
	
	public StudentDTO toStudentDto(Student student) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(student, StudentDTO.class);
	}
	
	public Student toStudent(StudentDTO studentDto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(studentDto, Student.class);
	}

}
