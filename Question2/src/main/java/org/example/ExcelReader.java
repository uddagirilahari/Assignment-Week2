package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    public static boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false; // If any cell is non-empty, the row is not empty
            }
        }
        return true; // All cells are empty
    }

    public static List<InterviewData> readData(String filePath) throws IOException {
        List<InterviewData> dataList = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
            int r=0;
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if(isRowEmpty(row)){
                    break;
                }
                InterviewData interviewData = new InterviewData();
                if(r==0){
                    r++;
                    continue;
                }


                Cell dateCell = row.getCell(0);
                try{
                Date dateValue = dateCell.getDateCellValue();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
                //String formattedDate = dateFormat.format(dateValue);
                interviewData.setDate(dateFormat.format(dateValue));
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                Cell monthCell = row.getCell(1); // Assuming the month is in the second column (index 1)

                if (monthCell != null) {
                    String monthValue;

                    if (monthCell.getCellType() == CellType.NUMERIC) {
                        // If the cell is numeric, convert it to a string (e.g., date to string)
                        monthValue = String.valueOf(monthCell.getNumericCellValue());
                    } else{
                        // If the cell is already a string, get the string value
                        monthValue = monthCell.getStringCellValue();
                    }
                    interviewData.setMonth(monthValue);
                }

                interviewData.setTeam(row.getCell(2).getStringCellValue());
                interviewData.setPanelName(row.getCell(3).getStringCellValue());
                interviewData.setRound(row.getCell(4).getStringCellValue());
                interviewData.setSkill(row.getCell(5).getStringCellValue());


                Cell timeCell = row.getCell(6); // Assuming the time is in the seventh column (index 6)

                if (timeCell != null) {
                    String timeValue;

                    if (timeCell.getCellType() == CellType.NUMERIC) {
                        // If the cell is numeric, assume it represents a time value
                        Date dateValue = timeCell.getDateCellValue();
                        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
                        timeValue = timeFormat.format(dateValue);
                    }
                    else{
                        // If the cell is already a string, get the string value
                        timeValue = timeCell.getStringCellValue();
                    }
                    interviewData.setTime(timeValue);
                }

                interviewData.setCandidateCurrentLocation(row.getCell(7).getStringCellValue());
                interviewData.setCandidatePrefferedLocation(row.getCell(8).getStringCellValue());
                interviewData.setCandidateName(row.getCell(9).getStringCellValue());

                dataList.add(interviewData);
            }

        }
        return dataList;
    }
}


