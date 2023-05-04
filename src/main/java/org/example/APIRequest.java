package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class APIRequest {
    static String response = "";
    static void checkConnection() throws IOException {
        URL url = CreateQuery.getEvents(new HashMap<String, String>());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if(responseCode == 401) {
                throw new RuntimeException(String.format("HttpResponseCode: %s\nAPI key is invalid", responseCode));
            }
            else {
                System.out.println("Connection established");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String makeRequest(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if(responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }
        else {
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());
            while(scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            scanner.close();
            return inline;
        }
    }

    public static void start(String[] args) throws IOException {
        checkConnection();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("countryCode", "PL");
        URL url = CreateQuery.getEvents(params);
        System.out.println(url);
        response = makeRequest(url);
    }
}
