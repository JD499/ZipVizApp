package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import javax.swing.*;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

public class DataInputPanel extends JPanel {
    private JTextField zipCodeField;
    private JComboBox<String> chartTypeComboBox;
    private final DemographicChartPanel demographicChartPanel;
    private ChartFactory chartFactory;

    public DataInputPanel(DemographicChartPanel demographicChartPanel) {
        this.demographicChartPanel = demographicChartPanel;
        setupUI();
    }

    private void setupUI() {
        zipCodeField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        // Combo box for selecting chart type
        chartTypeComboBox = new JComboBox<>(new String[]{"Bar Chart", "Lorenz Curve", "Pie Chart", "Line Chart"});
        add(chartTypeComboBox);

        submitButton.addActionListener(e -> new Thread(() -> {
            try {
                DataFetcher dataFetcher = DataFetcher.getInstance();
                DemographicData data = dataFetcher.fetchData(zipCodeField.getText());

                String selectedChartType = (String) chartTypeComboBox.getSelectedItem();
                if (selectedChartType != null) {
                    switch (selectedChartType) {
                        case "Bar Chart":
                            chartFactory = new BarChartFactory();
                            break;
                        case "Lorenz Curve":
                            chartFactory = new LorenzChartFactory();
                            break;
                        case "Pie Chart":
                            chartFactory = new PieChartFactory();
                            break;
                        case "Line Chart":
                            chartFactory = new LineChartFactory();
                            break;
                    }
                }

                JFreeChart chart = chartFactory.createChart(data);

                SwingUtilities.invokeLater(() -> demographicChartPanel.setChart(chart, data));
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(DataInputPanel.this,
                        "Error fetching data: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE));
            }
        }).start());

        // Export button
        JButton exportButton = createExportButton();

        add(zipCodeField);
        add(submitButton);
        add(exportButton);
    }

    private JButton createExportButton() {
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(e -> {
            String[] options = {"Export Chart as Image", "Export Data as CSV"};
            int response = JOptionPane.showOptionDialog(null, "Choose an export option", "Export",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);

            if (response == 0) {

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
                        ex.printStackTrace();
                    }
                }
            } else if (response == 1) {

                DemographicData data = demographicChartPanel.getData();
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        saveDataAsCSV(file, data);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        return exportButton;
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
            e.printStackTrace();
        }
    }
}