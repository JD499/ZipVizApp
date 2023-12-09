package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

public class LorenzChartFactory implements ChartFactory {
    /**
     * Calculates the Lorenz data for a given DemographicData object.
     * The method takes the income distribution from the DemographicData object and calculates the cumulative
     * households and cumulative income percentages for each income range. The calculated data is returned as
     * a 2D array containing two rows:
     * Row 1: Cumulative share of households percentages
     * Row 2: Cumulative share of income percentages
     *
     * @param data The DemographicData object containing the income distribution data.
     * @return A 2D array containing the calculated lorenz data.
     */
    private static double[][] calculateLorenzData(DemographicData data) {
        Map<String, Integer> incomeDistribution = data.getIncomeDistribution();

        int totalHouseholds = incomeDistribution.values().stream().mapToInt(Integer::intValue).sum();
        double totalIncome = incomeDistribution.entrySet().stream()
                .mapToDouble(entry -> calculateMidpoint(entry.getKey()) * entry.getValue())
                .sum();
        double cumulativeHouseholds = 0;
        double cumulativeIncome = 0;
        List<Double> cumulativeHouseholdsPercentages = new ArrayList<>();
        List<Double> cumulativeIncomePercentages = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : incomeDistribution.entrySet()) {
            cumulativeHouseholds += entry.getValue();
            cumulativeIncome += calculateMidpoint(entry.getKey()) * entry.getValue();

            cumulativeHouseholdsPercentages.add((cumulativeHouseholds / (double) totalHouseholds) * 100);
            cumulativeIncomePercentages.add((cumulativeIncome / totalIncome) * 100);
        }
        double[][] lorenzData = new double[2][cumulativeHouseholdsPercentages.size()];
        for (int i = 0; i < cumulativeHouseholdsPercentages.size(); i++) {
            lorenzData[0][i] = cumulativeHouseholdsPercentages.get(i);
            lorenzData[1][i] = cumulativeIncomePercentages.get(i);
        }

        return lorenzData;
    }

    /**
     * Calculates the midpoint for a given income range.
     *
     * @param incomeRange The income range to calculate the midpoint for.
     * @return The midpoint value for the given income range.
     */
    private static double calculateMidpoint(String incomeRange) {
        return switch (incomeRange) {
            case "Less than $10,000" -> 5000;
            case "$10,000 to $14,999" -> (10000 + 14999) / 2.0;
            case "$15,000 to $19,999" -> (15000 + 19999) / 2.0;
            case "$20,000 to $24,999" -> (20000 + 24999) / 2.0;
            case "$25,000 to $29,999" -> (25000 + 29999) / 2.0;
            case "$30,000 to $34,999" -> (30000 + 34999) / 2.0;
            case "$35,000 to $39,999" -> (35000 + 39999) / 2.0;
            case "$40,000 to $44,999" -> (40000 + 44999) / 2.0;
            case "$45,000 to $49,999" -> (45000 + 49999) / 2.0;
            case "$50,000 to $59,999" -> (50000 + 59999) / 2.0;
            case "$60,000 to $74,999" -> (60000 + 74999) / 2.0;
            case "$75,000 to $99,999" -> (75000 + 99999) / 2.0;
            case "$100,000 to $124,999" -> (100000 + 124999) / 2.0;
            case "$125,000 to $149,999" -> (125000 + 149999) / 2.0;
            case "$150,000 to $199,999" -> (150000 + 199999) / 2.0;
            case "$200,000 or more" -> 250000;
            default -> 0;
        };
    }

    /**
     * Creates a JFreeChart object based on the provided DemographicData.
     *
     * @param data The DemographicData object containing the income distribution data.
     * @return A JFreeChart object representing the chart with the income distribution data.
     */
    @Override
    public JFreeChart createChart(DemographicData data) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] lorenzCurveData = calculateLorenzData(data);
        dataset.addSeries("Lorenz Curve", lorenzCurveData);
        double[][] equalityLine = {{0, 100}, {0, 100}};
        dataset.addSeries("Line of Equality", equalityLine);
        JFreeChart chart = org.jfree.chart.ChartFactory.createXYLineChart(
                "Lorenz Curve",
                "Cumulative Share of Households (%)",
                "Cumulative Share of Income (%)",
                dataset,
                PlotOrientation.VERTICAL,
                true,   // legend
                true,   // tooltips
                false   // URLs
        );
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);
        return chart;
    }
}
