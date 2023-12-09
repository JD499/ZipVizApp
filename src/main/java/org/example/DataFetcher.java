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
import com.google.gson.JsonParseException;
import java.io.IOException;


public class DataFetcher {
    private static final String API_KEY = "8977b93cfc95c370f7992f833db12a96bb8a18f9";
    private static final String BASE_URL = "https://api.census.gov/data/2021/acs/acs5";
    private static DataFetcher instance;

    private DataFetcher() {}

    public static synchronized DataFetcher getInstance() {
        if (instance == null) {
            instance = new DataFetcher();
        }
        return instance;
    }

    public DemographicData fetchData(String zipCode) {
        try {
            StringBuilder response = request(zipCode);

            Gson gson = new Gson();
            JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();


            if(jsonArray.size() < 2) {
                throw new RuntimeException("Invalid API data.");
            }

            JsonArray data = jsonArray.get(1).getAsJsonArray();

            Map<String, Integer> incomeData = new LinkedHashMap<>();
            for(int i = 4; i <= 64; i += 4) {
                if(data.size() < i + 1) {
                    throw new RuntimeException("Data array does not contain required number of elements.");
                }
                String range = getIncomeRange(i);
                incomeData.put(range, gson.fromJson(data.get(i), Integer.class));
            }

            return new DemographicData(incomeData);
        } catch(IOException | JsonParseException e) {
            throw new RuntimeException("Error fetching data: " + e.getMessage(), e);
        }
    }

    private static StringBuilder request(String zipCode) throws IOException {
        String query = "?get=group(B19001)&for=zip%20code%20tabulation%20area:" +
                zipCode + "&key=" + API_KEY;
        URL url = new URL(BASE_URL + query);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if(responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP request failed with response code: " + responseCode);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } finally {
            conn.disconnect();
        }
        return response;
    }

    private String getIncomeRange(int index) {
        return switch (index) {
            case 4 -> "Less than $10,000";
            case 8 -> "$10,000 to $14,999";
            case 12 -> "$15,000 to $19,999";
            case 16 -> "$20,000 to $24,999";
            case 20 -> "$25,000 to $29,999";
            case 24 -> "$30,000 to $34,999";
            case 28 -> "$35,000 to $39,999";
            case 32 -> "$40,000 to $44,999";
            case 36 -> "$45,000 to $49,999";
            case 40 -> "$50,000 to $59,999";
            case 44 -> "$60,000 to $74,999";
            case 48 -> "$75,000 to $99,999";
            case 52 -> "$100,000 to $124,999";
            case 56 -> "$125,000 to $149,999";
            case 60 -> "$150,000 to $199,999";
            case 64 -> "$200,000 or more";
            default -> throw new IllegalArgumentException("Unexpected index: " + index);
        };
    }
}