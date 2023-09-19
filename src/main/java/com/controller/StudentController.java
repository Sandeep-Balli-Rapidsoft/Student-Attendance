package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.StudentDTO;
import com.service.StudentService;
import com.util.Response;

@RestController
@RequestMapping("/student")
public class StudentController {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	@Autowired
	private StudentService studentService;

	@PostMapping("save")
	public Response<?> save(@RequestBody StudentDTO studentDto) throws NumberFormatException, IOException, ParseException {
		Response<?> response = this.studentService.save(studentDto);
		return response;
	}

	@GetMapping("/all")
	public Response<?> getAll() throws NumberFormatException, IOException, ParseException {

		Response<?> response = this.studentService.getAll();
		return response;

	}
}
