package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dao.StudentDao;
import com.dto.StudentDTO;
import com.entity.Student;
import com.util.Response;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	public Response<?> save(StudentDTO studentDTO) {

		Student student = this.studentDao.save(studentDTO.toStudent(studentDTO));

		Response<?> response = new Response<>("success", studentDTO.toStudentDto(student), HttpStatus.OK.value());

		return response;
	}

	public Response<?> getAll() {
		List<Student> list = this.studentDao.getAll();
		
		try {
			if (!list.isEmpty()) {
				return new Response<>("success", list, HttpStatus.OK.value());
			} else {
				return new Response<>("NO Data Found", list, HttpStatus.OK.value());
			}
		} catch (Exception e) {
			return new Response<>("Bad Request", e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}
	}
}
