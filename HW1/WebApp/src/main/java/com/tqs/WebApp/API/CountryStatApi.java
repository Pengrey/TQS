package com.tqs.WebApp.API;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountryStatApi extends Api {

    @Override
    public String getURI(String country) {
        String URI = "https://countrystat.p.rapidapi.com/coronavirus/who_latest_stat_by_country.php?country=";
        log.info("Getting URI for country: " + country + " on CountryStatApi API");
        return URI.concat(country);
    }

    @Override
    public String getHost() {
        log.info("Getting Host on CountryStatApi API");
        return "countrystat.p.rapidapi.com";
    }

    @Override
    public CountryStatResponse parseResponse(String response) {
        Gson gson = new Gson();
        log.info("Parsed CountryStatResponse");
        return gson.fromJson(response, CountryStatResponse.class);
    }

    @Override
    public String toString() {
        return "CountryStatApi";
    }
}
