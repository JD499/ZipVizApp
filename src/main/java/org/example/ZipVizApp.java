package org.example;

public class ZipVizApp {
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(ZipVizApp::createAndShowGUI);
  }

  private static void createAndShowGUI() {
    ApplicationWindow applicationWindow = new ApplicationWindow();
    applicationWindow.setVisible(true);
  }
}
