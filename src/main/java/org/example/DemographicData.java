package org.example;

import java.util.Map;

public class DemographicData {
    private Map<String, Integer> incomeDistribution;

    // Constructor
    public DemographicData(Map<String, Integer> incomeDistribution) {
        this.incomeDistribution = incomeDistribution;
    }

    // Getter and Setter for incomeDistribution
    public Map<String, Integer> getIncomeDistribution() {
        return incomeDistribution;
    }

    public void setIncomeDistribution(Map<String, Integer> incomeDistribution) {
        this.incomeDistribution = incomeDistribution;
    }
}

