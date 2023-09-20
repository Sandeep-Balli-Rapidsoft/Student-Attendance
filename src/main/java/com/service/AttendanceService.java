package com.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dao.AttendanceDao;
import com.dto.AttendanceDTO;
import com.dto.ExportAttendanceDto;
import com.entity.Attendance;
import com.entity.Student;
import com.helper.Helper;
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

	public List<Date> getDatesList(Date date) {
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

	public List<LocalDate> getAllDatesList1(LocalDate start, LocalDate end) {

		long numOfDays = ChronoUnit.DAYS.between(start, end) + 1;
		List<LocalDate> listOfDates = Stream.iterate(start, date -> date.plusDays(1)).limit(numOfDays)
				.collect(Collectors.toList());

		return listOfDates;
	}

	public ByteArrayInputStream getAllDatesList(LocalDate start, LocalDate end) throws ParseException, IOException {
		   List<LocalDate> listOfDates = getAllDatesList1(start, end);

		    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		    Date startDate = convertLocalDateToDate(start);
		    Date endDate = convertLocalDateToDate(end);

		    List<Attendance> list = this.attendanceDao.getWeekData(startDate, endDate);

		    Map<String, Map<String, Boolean>> attendanceDetails = new HashMap<>();
			List<Attendance> getDataDateToDate = this.attendanceDao.getWeekData(startDate, endDate);

			for (Attendance obj : getDataDateToDate) {
			    String studentName = obj.getStudent().getName();

			    if (!attendanceDetails.containsKey(studentName)) {
			        
			        Map<String, Boolean> addDetails = new HashMap<>();
			        attendanceDetails.put(studentName, addDetails);
			    }

			    Map<String, Boolean> innerMap = attendanceDetails.get(studentName);
			    String format = sdf.format(obj.getDay());
			    innerMap.put(format, obj.getIsActive());
			}
			
			String[] Headers = new String[listOfDates.size() + 1];
			Headers[0] = "name";
			for(int i = 1; i < listOfDates.size(); i++) {
				Headers[i] = listOfDates.get(i - 1).toString();
			}
			
			for(int i = 1; i < Headers.length; i++) {
				System.out.println(Headers[i]);
			}
			
			ByteArrayInputStream res = Helper.dataToExcel(attendanceDetails, Headers);
			
		    return res;
	}

	public static Date convertLocalDateToDate(LocalDate localDate) {
		// Step 1: Convert Loca lDate to LocalDateTime (add midnight time)
		LocalDateTime localDateTime = localDate.atStartOfDay();

		// Step 2: Convert LocalDateTime to ZonedDateTime (specify a time zone)
		ZoneId zoneId = ZoneId.systemDefault(); // You can change this to your desired time zone
		ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);

		// Step 3: Convert ZonedDateTime to java.util.Date
		return Date.from(zonedDateTime.toInstant());
	}

}
