package org.example;

public class ZipVizApp {
  /**
   * The main entry point for the ZipVizApp - a Demographic Data Visualizer application.
   * This method initializes the application window and starts the Application.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(ZipVizApp::createAndShowGUI);
  }

  /**
   * Creates and shows the main application window.
   */
  private static void createAndShowGUI() {
    ApplicationWindow applicationWindow = new ApplicationWindow();
    applicationWindow.setVisible(true);
  }
}
