package org.example;

import org.example.data.CountryCode;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class CreateQuery {
    static String root = "https://app.ticketmaster.com/";
    static String key = "apikey=l8BeAMovCVvCUbz0SSYQXHVGXrSapEMM";
    static String query = "";
    static CountryCode countryCode_ = new CountryCode();

    static URL getEvents(HashMap<String, String> params){
        query = String.format("%sdiscovery/v2/events.json?", root);
        for(String key : params.keySet()){
            query = addParam(key, params.get(key));
        }
        try {
            return authenticate();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String addParam(String param, String value){
        return String.format("%s%s=%s&",query, param, value);
    }

    static URL authenticate() throws MalformedURLException {
        query = String.format("%s%s", query, key);
        return new URL(query);
    }
}
