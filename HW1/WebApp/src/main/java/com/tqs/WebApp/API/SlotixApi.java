package com.tqs.WebApp.API;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SlotixApi extends Api {

    @Override
    public String getURI(String country) {
        String URI = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/total?country=";
        log.info("Getting URI for country: " + country + " on SlotixApi API");
        return URI.concat(country);
    }

    @Override
    public String getHost() {
        log.info("Getting Host on SlotixApi API");
        return "covid-19-coronavirus-statistics.p.rapidapi.com";
    }

    @Override
    public SlotixResponse parseResponse(String response) {
        response = response.replace("\"data\":{","")
                .replace("}}","}");
        log.info("Parsed SlotixResponse");
        return new Gson().fromJson(response, SlotixResponse.class);
    }

    @Override
    public String toString() {
        return "SlotixApi";
    }
}
