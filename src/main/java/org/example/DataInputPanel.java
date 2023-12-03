package org.example;

import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataInputPanel extends JPanel {
    private JTextField zipCodeField;
    private JButton submitButton;
    private DemographicChartPanel demographicChartPanel;

    public DataInputPanel(DemographicChartPanel demographicChartPanel) {
        this.demographicChartPanel = demographicChartPanel;
        setupUI();
    }

    private void setupUI() {
        zipCodeField = new JTextField(10);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        DataFetcher dataFetcher = new DataFetcher();
                        DemographicData data = dataFetcher.fetchData(zipCodeField.getText());

                        //JFreeChart chart = ChartFactory.createChart(data);
                        JFreeChart chart = ChartFactory.createLorenzChart(data);
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
