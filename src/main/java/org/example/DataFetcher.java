package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataFetcher {
    public DemographicData fetchData(String zipCode) throws Exception {
        String apiKey = "8977b93cfc95c370f7992f833db12a96bb8a18f9";
        String baseUrl = "https://api.census.gov/data/2021/acs/acs5";
        String query = "?get=group(B19001)&for=zip%20code%20tabulation%20area:" + zipCode + "&key=" + apiKey;

        URL url = new URL(baseUrl + query);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } finally {
            conn.disconnect();
        }


        Gson gson = new Gson();
        JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();

        JsonArray data = jsonArray.get(1).getAsJsonArray();


        Map<String, Integer> incomeData = new LinkedHashMap<>();


        incomeData.put("Less than $10,000", gson.fromJson(data.get(4), Integer.class));
        incomeData.put("$10,000 to $14,999", gson.fromJson(data.get(8), Integer.class));
        incomeData.put("$15,000 to $19,999", gson.fromJson(data.get(12), Integer.class));
        incomeData.put("$20,000 to $24,999", gson.fromJson(data.get(16), Integer.class));
        incomeData.put("$25,000 to $29,999", gson.fromJson(data.get(20), Integer.class));
        incomeData.put("$30,000 to $34,999", gson.fromJson(data.get(24), Integer.class));
        incomeData.put("$35,000 to $39,999", gson.fromJson(data.get(28), Integer.class));
        incomeData.put("$40,000 to $44,999", gson.fromJson(data.get(32), Integer.class));
        incomeData.put("$45,000 to $49,999", gson.fromJson(data.get(36), Integer.class));
        incomeData.put("$50,000 to $59,999", gson.fromJson(data.get(40), Integer.class));
        incomeData.put("$60,000 to $74,999", gson.fromJson(data.get(44), Integer.class));
        incomeData.put("$75,000 to $99,999", gson.fromJson(data.get(48), Integer.class));
        incomeData.put("$100,000 to $124,999", gson.fromJson(data.get(52), Integer.class));
        incomeData.put("$125,000 to $149,999", gson.fromJson(data.get(56), Integer.class));
        incomeData.put("$150,000 to $199,999", gson.fromJson(data.get(60), Integer.class));
        incomeData.put("$200,000 or more", gson.fromJson(data.get(64), Integer.class));


        // Return the demographic data
        return new DemographicData(incomeData);
    }
}
