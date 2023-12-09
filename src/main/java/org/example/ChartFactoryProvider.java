package org.example;

public class ChartFactoryProvider {
    public ChartFactory getChartFactory(String chartType) {
        return switch (chartType) {
            case "Bar Chart" -> new BarChartFactory();
            case "Lorenz Curve" -> new LorenzChartFactory();
            case "Pie Chart" -> new PieChartFactory();
            case "Line Chart" -> new LineChartFactory();
            default -> throw new IllegalArgumentException("Invalid chart type: " + chartType);
        };
    }
}