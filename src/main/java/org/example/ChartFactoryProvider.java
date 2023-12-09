package org.example;

public class ChartFactoryProvider {
  /**
   * Retrieves a ChartFactory object based on the specified chart type.
   *
   * @param chartType The type of chart to be created. Must be one of the following values: - "Bar
   *     Chart" - "Lorenz Curve" - "Pie Chart" - "Line Chart" - "Initial Chart"
   * @return A ChartFactory object corresponding to the specified chart type.
   * @throws IllegalArgumentException If an invalid chart type is provided.
   */
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

  /**
   * Retrieves the initial chart factory.
   *
   * @return The initial chart factory that creates an empty chart.
   */
  public ChartFactory getInitialChartFactory() {
    return new InitialChartFactory();
  }
}
