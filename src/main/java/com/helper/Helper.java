package com.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

    public static ByteArrayInputStream dataToExcel(Map<String, Map<String, Boolean>> attendanceDetails, String[] Headers) throws IOException {
    	 Workbook workbook = new XSSFWorkbook();
    	    ByteArrayOutputStream out = new ByteArrayOutputStream();

    	    try {
    	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    	        String sheetName = dateFormat.format(new Date());

    	        Sheet sheet = workbook.createSheet(sheetName);
    	        Row row = sheet.createRow(0);

    	        for (int i = 0; i < Headers.length; i++) {
    	            Cell cell = row.createCell(i);
    	            cell.setCellValue(Headers[i]);
    	        }

    	        int rowIndex = 1;

    	        for (Map.Entry<String, Map<String, Boolean>> entry : attendanceDetails.entrySet()) {
    	            String studentName = entry.getKey();
    	            Map<String, Boolean> attendanceMap = entry.getValue();

    	            Row dataRow = sheet.createRow(rowIndex);
    	            int cellIndex = 0;

    	            Cell nameCell = dataRow.createCell(cellIndex++);
    	            nameCell.setCellValue(studentName);

    	            LocalDate currentDate = LocalDate.now();
    	            for (int dayOffset = 0; dayOffset < Headers.length - 2; dayOffset++) {
    	                currentDate = currentDate.minusDays(1);
    	                Date currentDateAsDate = java.sql.Date.valueOf(currentDate);

    	                String dateString = dateFormat.format(currentDateAsDate);

    	                Boolean status = attendanceMap.get(dateString);

    	                Cell statusCell = dataRow.createCell(cellIndex++);
    	                if (status != null) {
    	                    statusCell.setCellValue(status ? "Present" : "Absent");
    	                } else {
    	                    statusCell.setCellValue("N/A");
    	                }
    	            }

    	            rowIndex++;
    	        }

    	        workbook.write(out);
    	        return new ByteArrayInputStream(out.toByteArray());
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        System.out.println("Failed to export data");
    	    } finally {
    	        workbook.close();
    	    }
    	    return null;
    }
}
