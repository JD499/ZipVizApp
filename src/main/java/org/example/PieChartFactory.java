// PieChartFactory.java
package org.example;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartFactory implements ChartFactory {
    @Override
    public JFreeChart createChart(DemographicData data) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Iterate through the incomeDistribution map to add data to the dataset
        data.getIncomeDistribution().forEach(dataset::setValue);

        // Create a pie chart
        JFreeChart chart = org.jfree.chart.ChartFactory.createPieChart(
                "Income Distribution", // Chart title
                dataset,               // Data
                true,                  // Include legend
                true,                  // Tooltips
                false                  // URLs
        );

        return chart;
    }
}