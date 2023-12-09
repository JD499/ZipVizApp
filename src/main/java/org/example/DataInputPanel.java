package org.example;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class DataInputPanel extends JPanel {

  private final List<DataInputObserver> dataInputObservers = new ArrayList<>();
  private JTextField zipCodeField;
  private JComboBox<String> chartTypeComboBox;

  /**
   * The DataInputPanel class represents a panel for entering data input. It extends the JPanel class.
   */
  public DataInputPanel() {
    setupUI();
  }

  /**
   * Adds a DataInputObserver to the list of observers.
   *
   * @param observer the observer to add
   */
  public void addDataInputListener(DataInputObserver observer) {
    dataInputObservers.add(observer);
  }

  /**
   * Sets up the UI components for the DataInputPanel. - Creates a text field for entering a zip
   * code. - Creates a combo box for selecting a chart type. - Creates a submit button. - Creates an
   * export button. - Adds event listeners to the submit and export buttons to handle user
   * interactions. - Adds the zip code field, submit button, and export button to the panel.
   */
  private void setupUI() {
    zipCodeField = new JTextField(10);
    JButton submitButton = new JButton("Submit");

    // Combo box for selecting chart type
    chartTypeComboBox =
        new JComboBox<>(new String[] {"Bar Chart", "Lorenz Curve", "Pie Chart", "Line Chart"});
    add(chartTypeComboBox);

    submitButton.addActionListener(
        e -> {
          for (DataInputObserver observer : dataInputObservers) {
            observer.comboBoxSelectSubmit((String) chartTypeComboBox.getSelectedItem());
            observer.zipCodeSubmit(zipCodeField.getText());
            observer.submitButtonPressed();
          }
        });

    // Export button
    JButton exportButton = createExportButton();

    add(zipCodeField);
    add(submitButton);
    add(exportButton);
  }

  /**
   * Creates an export button and adds an event listener to handle user interactions.
   *
   * @return the export button
   */
  private JButton createExportButton() {
    JButton exportButton = new JButton("Export");
    exportButton.addActionListener(
        e -> {
          String[] options = {"Export Chart as Image", "Export Data as CSV"};
          int response =
              JOptionPane.showOptionDialog(
                  null,
                  "Choose an export option",
                  "Export",
                  JOptionPane.DEFAULT_OPTION,
                  JOptionPane.PLAIN_MESSAGE,
                  null,
                  options,
                  options[0]);

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
