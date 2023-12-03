package org.example;

public class DemographicData {
    private int population;
    private double medianIncome;
    // ... other demographic fields

    // Constructor
    public DemographicData(int population, double medianIncome /*, other parameters */) {
        this.population = population;
        this.medianIncome = medianIncome;
        // ... initialize other fields
    }

    // Getters and Setters
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getMedianIncome() {
        return medianIncome;
    }

    public void setMedianIncome(double medianIncome) {
        this.medianIncome = medianIncome;
    }

    // ... other getters and setters
}

