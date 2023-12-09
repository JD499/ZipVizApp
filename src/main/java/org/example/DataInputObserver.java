package org.example;

public interface DataInputObserver {

        void zipCodeSubmit(String zipCode);

        void comboBoxSelectSubmit(String selectedChartType);
        void submitButtonPressed();

        void exportAsImage();
        void exportAsCSV();

}
