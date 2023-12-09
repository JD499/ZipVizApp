package org.example;


import java.util.ArrayList;
import java.util.List;

import javax.swing.*;



public class DataInputPanel extends JPanel {

    private JTextField zipCodeField;
    private JComboBox<String> chartTypeComboBox;


    private final List<DataInputObserver> dataInputObservers = new ArrayList<>();

    public void addDataInputListener(DataInputObserver observer) {
        dataInputObservers.add(observer);
    }

    public DataInputPanel() {

        setupUI();
    }
    private void setupUI() {
        zipCodeField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        // Combo box for selecting chart type
        chartTypeComboBox = new JComboBox<>(new String[]{"Bar Chart", "Lorenz Curve", "Pie Chart", "Line Chart"});
        add(chartTypeComboBox);

        submitButton.addActionListener(e -> new Thread(() -> {
            for (DataInputObserver observer : dataInputObservers) {
                observer.comboBoxSelectSubmit((String) chartTypeComboBox.getSelectedItem());
                observer.zipCodeSubmit(zipCodeField.getText());
                observer.submitButtonPressed();
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
                for (DataInputObserver observer : dataInputObservers) {
                    observer.exportAsImage();
                }


            } else if (response == 1) {
                for (DataInputObserver observer : dataInputObservers) {
                    observer.exportAsCSV();
                }
            }
        });
        return exportButton;
    }


}