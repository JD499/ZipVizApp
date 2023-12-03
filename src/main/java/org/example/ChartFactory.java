package org.example;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartFactory {

    public static JFreeChart createEmptyChart() {
        // Create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Create an empty chart
        JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(
                "No Data Available", // Chart title
                "",                  // Domain axis label
                "",                  // Range axis label
                dataset,             // Data
                PlotOrientation.VERTICAL,
                false,               // Include legend
                true,                // Tooltips
                false                // URLs
        );

        return chart;
    }


    public static JFreeChart createChart(DemographicData data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Iterate through the incomeDistribution map to add data to the dataset
        data.getIncomeDistribution().forEach((incomeRange, count) -> {
            dataset.addValue(count, "Households", incomeRange);
        });

        // Create a bar chart
        JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(
                "Income Distribution", // Chart title
                "Income Range",        // Domain axis label
                "Number of Households",// Range axis label
                dataset,               // Data
                PlotOrientation.VERTICAL,
                true,                  // Include legend
                true,                  // Tooltips
                false                  // URLs
        );

        return chart;
    }
}
