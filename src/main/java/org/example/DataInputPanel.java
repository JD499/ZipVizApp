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

    public DataInputPanel(DemographicChartPanel demographicChartPanel) {
        this.demographicChartPanel = demographicChartPanel;
        setupUI();
    }

    private void setupUI() {
        zipCodeField = new JTextField(10);
        submitButton = new JButton("Submit");

        // Combo box for selecting chart type
        chartTypeComboBox = new JComboBox<>(new String[]{"Bar Chart", "Lorenz Curve"});
        add(chartTypeComboBox);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        DataFetcher dataFetcher = DataFetcher.getInstance();
                        DemographicData data = dataFetcher.fetchData(zipCodeField.getText());

                        JFreeChart chart;
                        String selectedChartType = (String) chartTypeComboBox.getSelectedItem();
                        if ("Bar Chart".equals(selectedChartType)) {
                            chart = ChartFactory.createChart(data);
                        } else { // "Lorenz Curve"
                            chart = ChartFactory.createLorenzChart(data);
                        }

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
