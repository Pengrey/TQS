package com.tqs.WebApp.API;

import com.tqs.WebApp.Data.Cache;
import com.tqs.WebApp.Exceptions.NoApisException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;

@Slf4j
public class Client {
    private final String apiKey = "422282fb07msh7d426d35972d527p17359bjsnf4da7ac31769";

    public ApiResponse getStats(Cache cache, String country, Api slotixapi, Api countrystatapi) throws IOException, InterruptedException, NoApisException, ParseException {
        Api apiToUse;

        // Check Cached version
        if(cache.containsRecord(country)){
            log.info("Using cached results of country: " + country);
            return cache.getStats(country);
        }

        // Ask Apis for data
        if(apiIsUp(slotixapi)){
            log.info("Using slotix API");
            apiToUse = slotixapi;
        }else if(apiIsUp(countrystatapi)){
            log.warn("Slotix API DOWN, using Country API instead");
            apiToUse = countrystatapi;
        }else{
            log.error("All APIs DOWN");
            throw new NoApisException();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiToUse.getURI(country)))
                .header("X-RapidAPI-Host", apiToUse.getHost())
                .header("X-RapidAPI-Key", this.apiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        ApiResponse parsedResponse = apiToUse.parseResponse(HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body());
        log.info("Request stats from API");

        // Add record to cache
        cache.addStats(country, parsedResponse);
        log.info("Request added to cache");

        return parsedResponse;
    }

    public Boolean apiIsUp(Api api) throws IOException, InterruptedException {
        log.info("Testing " + api.toString() + "connectivity.");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api.getURI("DoesntExist")))
                .header("X-RapidAPI-Host", api.getHost())
                .header("X-RapidAPI-Key", this.apiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return true;
        }catch(java.net.ConnectException e){
            return false;
        }
    }
}
