package org.example;

public interface DataInputObserver {

  /**
   * Observes the user's zip code input.
   *
   * @param zipCode the zip code entered by the user
   */
  void zipCodeSubmit(String zipCode);

  /**
   * Observes the user's combo box selection.
   *
   * @param selectedChartType the selected chart type from the combo box
   */
  void comboBoxSelectSubmit(String selectedChartType);

  /** Observes the user's submit button. */
  void submitButtonPressed();

  /** Observes the user's export button as an image. */
  void exportAsImage();

  /** Observes the user's export button as a CSV file. */
  void exportAsCSV();
}
