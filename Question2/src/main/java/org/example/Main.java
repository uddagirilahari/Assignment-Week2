package org.example;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.example.ChartGenerator.generatePieChart;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        List<InterviewData> dataList = ExcelReader.readData("C:\\Users\\uddagiri.lahari\\IdeaProjects\\Question2\\src\\main\\resources\\Accolite Interview Data - Q4 2023 - Grad Program November 2023.xlsx");
        /*ExcelReader er = new ExcelReader();
        er.readData();*/
        /*for(InterviewData data : dataList){
            System.out.println(data);
        }*/
        try {
            DataBaseConnection.createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DataBaseConnection.insertData(dataList);
        DataBaseConnection.teamWithMaxNoOfInterviews();
        DataBaseConnection.teamWithMinNoOfInterviews();
        DataBaseConnection.topThreeSkills();
        DataBaseConnection.topThreePanels();
        DataBaseConnection.skillsInPeakTime();
        //JFreeChart chart = ChartGenerator.createChart(dataList);

        // Save chart as PDF

        PDFGenerator.generatePdf(dataList, "Documents.pdf");

        //JFreeChart chart1 =generatePieChart(dataList);
        PDFGenerator.generatePdf(dataList, "Piechart.pdf");

    }
}