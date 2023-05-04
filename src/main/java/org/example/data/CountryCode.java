package org.example.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class CountryCode {
    static HashMap<String, String> countryCodes = new HashMap<String, String>();

    public CountryCode(){
        loadCodes();
    }

    private void loadCodes(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\countrycodes.csv"));
            String line = reader.readLine();
            while(line != null){
                String[] split = line.split(",");
                countryCodes.put(split[1].toLowerCase(), split[2]);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public String getCountryCode(String country){
        return countryCodes.get(country.toLowerCase());
    }

}
