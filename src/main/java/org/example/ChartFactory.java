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
        // Create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Assuming DemographicData has methods getCount() and getValue()
        dataset.addValue(data.getPopulation(), "Series1", "Category" + data.getMedianIncome());

        // Create a bar chart
        JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(
                "Demographic Chart", // Chart title
                "Category",          // Domain axis label
                "Value",             // Range axis label
                dataset,             // Data
                PlotOrientation.VERTICAL,
                true,                // Include legend
                true,                // Tooltips
                false                // URLs
        );

        return chart;
    }
}
