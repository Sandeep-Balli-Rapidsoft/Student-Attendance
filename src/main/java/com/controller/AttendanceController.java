package com.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.AttendanceDTO;
import com.dto.DateDto;
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

	@GetMapping("/get/dates/list")
	public ResponseEntity<?> getDatesList(@RequestParam(defaultValue = "", required = false) String pattern,
			@RequestBody DateDto dateDto, HttpServletResponse response) throws ParseException, IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String currentDateTime = dateFormat.format(new Date());
		String filename = "report_" + currentDateTime + ".xlsx";

		String storagePath = "/home/rapidosft/Desktop/Java-Training/spring-excel/Student-Attendance/src/main/java/data/";

		if (pattern.equals("WEEKLY")) {
			LocalDate start = LocalDate.now();
			while (start.getDayOfWeek() != DayOfWeek.MONDAY) {
				start = start.minusDays(1);
			}
			LocalDate end = LocalDate.now();
			while (end.getDayOfWeek() != DayOfWeek.SUNDAY) {
				end = end.plusDays(1);
			}

			ByteArrayInputStream actualData = this.attendanceService.getAllDatesList(start, end);

			Path filePath = Paths.get(storagePath + filename);
			Files.copy(actualData, filePath, StandardCopyOption.REPLACE_EXISTING);

			InputStreamResource file = new InputStreamResource(new FileInputStream(filePath.toFile()));
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

			return ResponseEntity.ok().headers(headers)
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} else if (pattern.equals("MONTHLY")) {
			LocalDate monthBegin = LocalDate.now().withDayOfMonth(1);
			LocalDate monthEnd = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);

			ByteArrayInputStream actualData = this.attendanceService.getAllDatesList(monthBegin, monthEnd);

			Path filePath = Paths.get(storagePath + filename);
			Files.copy(actualData, filePath, StandardCopyOption.REPLACE_EXISTING);

			InputStreamResource file = new InputStreamResource(new FileInputStream(filePath.toFile()));
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

			return ResponseEntity.ok().headers(headers)
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} else {
			LocalDate fromDate = dateDto.getFromDate();
			LocalDate toDate = dateDto.getToDate();

			ByteArrayInputStream actualData = this.attendanceService.getAllDatesList(fromDate, toDate);

			Path filePath = Paths.get(storagePath + filename);
			Files.copy(actualData, filePath, StandardCopyOption.REPLACE_EXISTING);

			InputStreamResource file = new InputStreamResource(new FileInputStream(filePath.toFile()));
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

			return ResponseEntity.ok().headers(headers)
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		}
	}

}
