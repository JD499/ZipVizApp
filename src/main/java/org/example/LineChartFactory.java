// LineChartFactory.java
package org.example;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartFactory implements ChartFactory {
    @Override
    public JFreeChart createChart(DemographicData data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Iterate through the incomeDistribution map to add data to the dataset
        data.getIncomeDistribution().forEach((incomeRange, count) -> {
            dataset.addValue(count, "Households", incomeRange);
        });

        // Create a line chart
        JFreeChart chart = org.jfree.chart.ChartFactory.createLineChart(
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