package com.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Attendance;
import com.entity.Student;

@Component
public class Helper<T> {

	public static Boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	public static String[] fields(Object obj) {
		Class<?> demoClass = obj.getClass();
		Field[] fields = demoClass.getDeclaredFields();

		String strFields[] = new String[fields.length];
		for (int i = 0; i < strFields.length; i++) {
			strFields[i] = fields[i].getName();
			System.out.println(strFields[i]);
		}
		return strFields;
	}

	public static String SHEET_NAME = "product_data";

	public static ByteArrayInputStream dataToExcelInThroughHashMap(List<Attendance> attendanceList) {
		try {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("AttendanceData");

			// Create headers with dynamic dates
			Row headerRow = sheet.createRow(0);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date currentDate = new Date();
			for (int i = 0; i < 7; i++) {
				Cell cell = headerRow.createCell(i + 1);
				cell.setCellValue(dateFormat.format(currentDate));
				currentDate = new Date(currentDate.getTime() - 24 * 60 * 60 * 1000);
			}
			headerRow.createCell(0).setCellValue("Name");

			int rowIndex = 1;
			for (Attendance attendance : attendanceList) {
				Row dataRow = sheet.createRow(rowIndex++);
				dataRow.createCell(0).setCellValue(attendance.getStudent().getName());

				currentDate = new Date();
				for (int i = 0; i < 7; i++) {
					Cell cell = dataRow.createCell(i + 1);
					Date attendanceDate = new Date(currentDate.getTime() - i * 24 * 60 * 60 * 1000);
					Boolean isActive = attendance.getIsActive();
					if (isActive != null) {
						cell.setCellValue(isActive ? "p" : "a");
					} else {
						cell.setCellValue("NA");
					}
				}
			}

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			workbook.close();

			return new ByteArrayInputStream(outputStream.toByteArray());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void setEntityField(T entity, String fieldName, Cell cell) {

	}
}
