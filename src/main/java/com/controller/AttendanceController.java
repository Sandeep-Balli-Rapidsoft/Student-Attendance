package com.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.AttendanceDTO;
import com.service.AttendanceService;
import com.util.Response;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@PostMapping("/new")
	public Response<?> save(@RequestBody AttendanceDTO attendanceDTO)
			throws NumberFormatException, IOException, ParseException {
		return new Response<>("Success", this.attendanceService.save(attendanceDTO), HttpStatus.OK.value());
	}

	@GetMapping("/all")
	public Response<?> getAll() {
		return new Response<>("Success", this.attendanceService.getAll(), HttpStatus.OK.value());
	}

//	@GetMapping("/week")
//	public Response<?> getWeekData() throws ParseException {
//		return new Response<>("Success", this.attendanceService.getWeekData(), HttpStatus.OK.value());
//	}

	@GetMapping("/export")
	public ResponseEntity<?> exportData() throws ParseException {
		String filename = "attendance.xlsx";

		ByteArrayInputStream actualData = this.attendanceService.exportToExcel();
		InputStreamResource file = new InputStreamResource(actualData);
		ResponseEntity<InputStreamResource> body = ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);

		return body;
	}
}
