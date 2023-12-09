package org.example;

import java.util.Map;

/**
 * The DemographicData class represents demographic data, specifically the income distribution data, for a given ZIP code.
 */
public record DemographicData(Map<String, Integer> incomeDistribution) {}
