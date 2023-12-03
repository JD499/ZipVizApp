package org.example;

public class ZipVizApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
