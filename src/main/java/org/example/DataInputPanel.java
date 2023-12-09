package org.example;

import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataInputPanel extends JPanel {
    private JTextField zipCodeField;
    private JButton submitButton;
    private JComboBox<String> chartTypeComboBox;
    private DemographicChartPanel demographicChartPanel;
    private ChartFactory chartFactory;

    public DataInputPanel(DemographicChartPanel demographicChartPanel) {
        this.demographicChartPanel = demographicChartPanel;
        setupUI();
    }

    private void setupUI() {
        zipCodeField = new JTextField(10);
        submitButton = new JButton("Submit");

        // Combo box for selecting chart type
        chartTypeComboBox = new JComboBox<>(new String[]{"Bar Chart", "Lorenz Curve", "Pie Chart", "Line Chart"});
        add(chartTypeComboBox);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        DataFetcher dataFetcher = DataFetcher.getInstance();
                        DemographicData data = dataFetcher.fetchData(zipCodeField.getText());

                        String selectedChartType = (String) chartTypeComboBox.getSelectedItem();
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

                        JFreeChart chart = chartFactory.createChart(data);

                        SwingUtilities.invokeLater(() -> demographicChartPanel.setChart(chart));
                    } catch (Exception ex) {
                        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(DataInputPanel.this,
                                "Error fetching data: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE));
                    }
                }).start();
            }
        });

        add(zipCodeField);
        add(submitButton);
    }
}