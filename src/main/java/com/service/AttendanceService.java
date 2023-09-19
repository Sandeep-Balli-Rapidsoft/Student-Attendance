package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dao.AttendanceDao;
import com.dto.AttendanceDTO;
import com.entity.Attendance;
import com.entity.Student;
import com.util.Response;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private StudentService studentService;

	public Response<?> save(AttendanceDTO attendanceDTO) throws NumberFormatException, IOException, ParseException {

		List<Student> list = (List<Student>) this.studentService.getAll().getResponse();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 1, to Insert Student Record");
		System.out.println("Enter 2, to Export Data");
		System.out.println("Enter 3, to ");

		Integer optionEntered = Integer.parseInt(br.readLine());

		List<Attendance> attendanceList = new ArrayList();

		if (optionEntered == 1) {

			System.out.println("Enter the day of the attendance you want to enter");

			String dateString = br.readLine();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			Date date = dateFormat.parse(dateString);

			System.out.println("You entered, " + dateFormat.format(date));

			int idx = 1;
			for (Student student : list) {
				System.out.println("Enter attendance status for, " + student.getName());
				System.out.println("Enter 1, for present");
				System.out.println("Enter 2, for absent");
				System.out.println("Enter 3, to pass");
				Integer option = Integer.parseInt(br.readLine());
				if (option == 1) {
					attendanceList.add(new Attendance(idx, student, date, true, new Date(), new Date()));
				} else if (option == 2) {
					attendanceList.add(new Attendance(idx, student, date, false, new Date(), new Date()));
				} else {
					attendanceList.add(new Attendance(idx, student, date, null, new Date(), new Date()));
				}
				idx++;
			}
			for (Attendance attendance : attendanceList) {
				System.out.println(attendance.getStudent().getName());
				System.out.println(dateFormat.format(attendance.getDay()));
				System.out.println(attendance.getIsActive());
				this.attendanceDao.save(new Attendance(01, attendance.getStudent(), date, attendance.getIsActive(),
						new Date(), new Date()));
			}
		}

		Response<?> response = new Response<>("success", null, HttpStatus.OK.value());
		return response;
	}

	public Response<?> getAll() {
		List<Attendance> list = this.attendanceDao.getAll();
		try {
			if (!list.isEmpty()) {
				return new Response<>("Success", list, HttpStatus.OK.value());
			} else {
				return new Response<>("No Data", list, HttpStatus.OK.value());
			}
		} catch (Exception e) {
			return new Response<>("Bad Request", e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}
	}

	public Response<?> getWeekData() throws ParseException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Date> weekList = getWeekDates(date);
		
		Map<Student, HashMap<Date, Boolean>> attendanceMap = new HashMap<>();
		
		for(Date dateObj : weekList) {
			System.out.println(sdf.format(dateObj));
		}
		
		String fromDate = "15-09-2023";
		String toDate = "18-09-2023";
		
		List<Attendance> list = this.attendanceDao.getWeekData(sdf.parse(fromDate), sdf.parse(toDate));
		System.out.println("start");
		for(Attendance attendance : list) {
			
			System.out.println("before map");
			
			if(attendanceMap.containsKey(attendance.getStudent())) {
				Map<Date, Boolean> map = new HashMap<>();
				map.put(attendance.getDay(), attendance.getIsActive());
				attendanceMap.put(attendance.getStudent(), (HashMap<Date, Boolean>) map);
			}
			
			
		}
		
		for(Entry<Student, HashMap<Date, Boolean>> mp : attendanceMap.entrySet()) {
			System.out.println("Student: " + mp.getKey().getName());
			Map<Date, Boolean> map = mp.getValue();
			for(Entry<Date, Boolean> obj : map.entrySet()) {
				System.out.println("Date: " + obj.getKey());
				System.out.println("Status: " + obj.getValue());
			}
		}
		System.out.println("after map");
		return new Response<>(null, null, null);
	}

	public List<Date> getWeekDates(Date date) {
		List<Date> datesList = new ArrayList();
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DAY_OF_YEAR, -6);

		for (int i = 0; i < 7; i++) {
			Date dateObj = calender.getTime();
			datesList.add(dateObj);
			calender.add(Calendar.DAY_OF_YEAR, 1);
		}
		return datesList;
	}
}
