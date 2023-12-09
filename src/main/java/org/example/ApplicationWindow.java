package org.example;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationWindow extends JFrame implements DataInputObserver {
    private String zipCode;
    private String selectedChartType;
    private final ChartFactoryProvider chartFactoryProvider;
    private DemographicChartPanel demographicChartPanel;
    private static final Logger LOGGER = Logger.getLogger(ApplicationWindow.class.getName());

    public ApplicationWindow() {
        chartFactoryProvider = new ChartFactoryProvider();
        initializeComponents();
        setTitle("ZipViz - Demographic Data Visualizer");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {
        demographicChartPanel = new DemographicChartPanel();
        DataInputPanel dataInputPanel = new DataInputPanel();
        dataInputPanel.addDataInputListener(this);

        // Add components to the frame
        add(dataInputPanel, BorderLayout.NORTH);
        add(demographicChartPanel, BorderLayout.CENTER);
    }


    @Override
    public void zipCodeSubmit(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public void comboBoxSelectSubmit(String selectedChartType) {
        this.selectedChartType = selectedChartType;
    }

    @Override
    public void submitButtonPressed() {
        createChart(zipCode, selectedChartType);
    }

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
            for (Map.Entry<String, Integer> entry : data.getIncomeDistribution().entrySet()) {
                writer.println("\"" + entry.getKey() + "\"," + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }


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
