package org.example;

public class ChartFactoryProvider {
    public ChartFactory getChartFactory(String chartType) {
        return switch (chartType) {
            case "Bar Chart" -> new BarChartFactory();
            case "Lorenz Curve" -> new LorenzChartFactory();
            case "Pie Chart" -> new PieChartFactory();
            case "Line Chart" -> new LineChartFactory();
            case "Initial Chart" -> new InitialChartFactory();
            default -> throw new IllegalArgumentException("Invalid chart type: " + chartType);
        };
    }


    public ChartFactory getBarChartFactory() {
        return new BarChartFactory();
    }

    public ChartFactory getLorenzChartFactory() {
        return new LorenzChartFactory();
    }

    public ChartFactory getPieChartFactory() {
        return new PieChartFactory();
    }

    public ChartFactory getLineChartFactory() {
        return new LineChartFactory();
    }
    public ChartFactory getInitialChartFactory() {
        return new InitialChartFactory();
    }

}