package org.example;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import org.jfree.data.category.CategoryDataset;

import java.awt.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartGenerator {

    public static JFreeChart generateDatabasePieChart(List<InterviewData> dataList) {
        // Fetch data from the database (replace with your database connection details)
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/interviewdata", "root", "Lahari@db1")) {
            String sql = "SELECT skill, COUNT(*) as count FROM accolite_data GROUP BY skill";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Create a dataset for the pie chart
                DefaultPieDataset dataset = new DefaultPieDataset();

                // Populate dataset with database results
                while (resultSet.next()) {
                    String skill = resultSet.getString("skill");
                    int count = resultSet.getInt("count");
                    dataset.setValue(skill, count);
                }

                // Create and return the pie chart
                return ChartFactory.createPieChart(
                        "Skills Distribution",  // chart title
                        dataset,
                        true,   // include legend
                        true,
                        false
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return null; // Return null in case of errors
    }


    public static JFreeChart createChart(List<InterviewData> data) {
        // Example: Create a bar chart with team names and the count of interviews
        CategoryDataset dataset = createDataset(data);
        JFreeChart chart = ChartFactory.createBarChart(
                "Interviews by Team",
                "Team",
                "Number of Interviews",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        return chart;
    }

    private static CategoryDataset createDataset(List<InterviewData> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Example: Count interviews by team
        Map<String, Long> teamInterviewCounts = data.stream()
                .collect(Collectors.groupingBy(InterviewData::getTeam, Collectors.counting()));

        teamInterviewCounts.forEach((team, count) ->{
            dataset.addValue(count, "Interviews", team);
        });

        return dataset;
    }



    private static ChartPanel createChartPanel(JFreeChart chart) {
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        return chartPanel;
    }

    public static void saveChartAsImage(JFreeChart chart, String fileName) throws IOException {
        int width = 800;
        int height = 600;

        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        FileOutputStream fos = new FileOutputStream(fileName);

        try {
            EncoderUtil.writeBufferedImage(bufferedImage, ImageFormat.PNG, fos);
        } finally {
            fos.close();
        }
    }

    public static JFreeChart generateMonthlyBarChart() {
        // Fetch data from the database (replace with your database connection details)
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/interviewdata", "root", "Lahari@db1")) {
            //String sql = "SELECT DATE_FORMAT(STR_TO_DATE(SUBSTRING(date, 1, 6), '%b-%y'), '%b-%y') AS month, COUNT(*) AS count FROM accolite_data WHERE date LIKE ? GROUP BY month";

            String sql = "SELECT DATE_FORMAT(STR_TO_DATE(SUBSTRING(date, 1, 6), '%b-%y'), '%b-%y') AS month, COUNT(*) AS count FROM accolite_data WHERE date LIKE ? GROUP BY DATE_FORMAT(STR_TO_DATE(SUBSTRING(date, 1, 6), '%b-%y'), '%b-%y')";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                // Set the parameter for the SQL query to filter by month
                preparedStatement.setString(1, "%-23");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    // Create a dataset for the bar chart
                    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                    // Populate dataset with database results
                    while (resultSet.next()) {
                        int month = resultSet.getInt("month");
                        int count = resultSet.getInt("count");
                        dataset.addValue(count, "Interviews", getMonthName(month));
                    }

                    // Create and return the bar chart
                    return ChartFactory.createBarChart(
                            "Number of Interviews by Month", // chart title
                            "Month",
                            "Number of Interviews",
                            dataset
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return null; // Return null in case of errors
    }

    private static String getMonthName(int month) {
        // Convert month number to month name (e.g., 1 -> "January")
        return new DateFormatSymbols().getMonths()[month];
    }

}
