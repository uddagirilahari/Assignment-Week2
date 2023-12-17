package org.example;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.apache.commons.dbcp2.BasicDataSource;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartGenerator {
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

    public static JFreeChart generatePieChart(List<InterviewData> dataList) {
        Map<String, Integer> skillCountMap = new HashMap<>();
        for(InterviewData data : dataList) {
            String skill = data.getSkill();
            if(skill != null && !skill.trim().isEmpty()){
                skillCountMap.put(skill, skillCountMap.getOrDefault(skill,0) + 1);
            } else {
                skillCountMap.put("Unknown", skillCountMap.getOrDefault("Unknown", 0) + 1);
            }
        }
        DefaultPieDataset dataset = new DefaultPieDataset<>();
        skillCountMap.forEach(dataset::setValue);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Number of skills", // chart title
                dataset,
                true, // including legend
                true,
                false
        );

        return pieChart;
    }

}
