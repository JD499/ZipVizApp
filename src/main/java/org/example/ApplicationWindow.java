package org.example;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

public class ApplicationWindow extends JFrame implements DataInputObserver {
  private static final Logger LOGGER = Logger.getLogger(ApplicationWindow.class.getName());
  private final ChartFactoryProvider chartFactoryProvider;
  private String zipCode;
  private String selectedChartType;
  private DemographicChartPanel demographicChartPanel;

  /**
   * The ApplicationWindow class represents the main application window for the ZipViz - Demographic
   * Data Visualizer application. It extends the JFrame class.
   */
  public ApplicationWindow() {
    chartFactoryProvider = new ChartFactoryProvider();
    initializeComponents();
    setTitle("ZipViz - Demographic Data Visualizer");
    setSize(1366, 768);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Initializes the components of the ApplicationWindow class. This method creates and sets up the
   * necessary UI components, including the DataInputPanel and DemographicChartPanel. It also adds
   * event listeners to handle user interactions and updates.
   */
  private void initializeComponents() {
    demographicChartPanel = new DemographicChartPanel();
    DataInputPanel dataInputPanel = new DataInputPanel();
    dataInputPanel.addDataInputListener(this);

    // Add components to the frame
    add(dataInputPanel, BorderLayout.NORTH);
    add(demographicChartPanel, BorderLayout.CENTER);
  }

  /**
   * Updates the zip code field in the DataInputPanel with the given zip code value.
   *
   * @param zipCode The zip code value to be submitted.
   */
  @Override
  public void zipCodeSubmit(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Sets the selected chart type for the comboBox.
   *
   * @param selectedChartType The selected chart type to be set.
   */
  @Override
  public void comboBoxSelectSubmit(String selectedChartType) {
    this.selectedChartType = selectedChartType;
  }

  /**
   * The submitButtonPressed method is called when the submit button is pressed. It creates a chart
   * using the provided zip code and selected chart type.
   */
  @Override
  public void submitButtonPressed() {
    createChart(zipCode, selectedChartType);
  }

  /**
   * Export the current chart as an image.
   *
   * <p>This method allows the user to export the current chart as an image file (.png). It prompts
   * the user to choose a file location and saves the chart as a .png file with the specified
   * dimensions. If the chosen file path does not end with .png, the .png extension will be
   * automatically added. If any errors occur during the export process, they will be logged using
   * the provided LOGGER.
   */
  @Override
  public void exportAsImage() {
    JFreeChart chart = demographicChartPanel.getChart();
    JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getPath();

      if (!filePath.toLowerCase().endsWith(".png")) {
        filePath += ".png";
      }
      File pngFile = new File(filePath);
      try {
        ChartUtils.saveChartAsPNG(pngFile, chart, 600, 400);
      } catch (Exception ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
  }

  /**
   * Exports the current chart as a CSV file.
   *
   * <p>This method allows the user to export the current chart as a CSV file. It prompts the user
   * to choose a file location and saves the chart data as a CSV file with the specified format. If
   * any errors occur during the export process, they will be logged using the provided LOGGER.
   */
  @Override
  public void exportAsCSV() {
    DemographicData data = demographicChartPanel.getData();
    JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        saveDataAsCSV(file, data);
      } catch (Exception ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
  }

  /**
   * Saves the demographic data as a CSV file.
   *
   * @param file The file to save the data to.
   * @param data The demographic data to be saved.
   */
  private void saveDataAsCSV(File file, DemographicData data) {
    String filePath = file.getPath();
    // Append .csv if not already present
    if (!filePath.toLowerCase().endsWith(".csv")) {
      filePath += ".csv";
    }
    File csvFile = new File(filePath);

    try (PrintWriter writer = new PrintWriter(csvFile)) {
      // Write the header
      writer.println("\"Income Range\",Number of Households");

      // Write the data
      for (Map.Entry<String, Integer> entry : data.incomeDistribution().entrySet()) {
        writer.println("\"" + entry.getKey() + "\"," + entry.getValue());
      }
    } catch (FileNotFoundException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  /**
   * Creates a chart based on the provided zip code and selected chart type.
   *
   * @param zipCode The zip code to fetch data for.
   * @param selectedChartType The selected chart type.
   */
  private void createChart(String zipCode, String selectedChartType) {
    DataFetcher dataFetcher = DataFetcher.getInstance();
    DemographicData data = dataFetcher.fetchData(zipCode);
    JFreeChart chart;

    if (selectedChartType != null) {
      chart = chartFactoryProvider.getChartFactory(selectedChartType).createChart(data);
    } else {
      chart = null;
    }
    SwingUtilities.invokeLater(() -> demographicChartPanel.setChart(chart, data));
  }
}
