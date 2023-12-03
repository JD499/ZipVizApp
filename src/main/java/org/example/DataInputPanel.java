package org.example;

import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
                // Use fake data to create a chart
                DemographicData fakeData = new DemographicData(100000, 50000); // Example population and median income
                JFreeChart chart = ChartFactory.createChart(fakeData);
                demographicChartPanel.setChart(chart);
            }
        });

        add(zipCodeField);
        add(submitButton);
    }
}
