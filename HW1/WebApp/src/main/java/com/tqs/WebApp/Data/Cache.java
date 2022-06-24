package com.tqs.WebApp.Data;

import com.tqs.WebApp.API.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.HashMap;

@Slf4j
public class Cache {
    private HashMap<String, ApiResponse> memory;
    private long requests;
    private long updates;

    public Cache() {
        memory = new HashMap<String, ApiResponse>();
        this.requests = 0;
        this.updates = 0;
        log.info("Created Cache");
    }

    public Boolean containsRecord(String country) throws ParseException {
        log.info("Cache requested for country: " + country);
        this.requests = this.requests + 1;
        if (memory.containsKey(country)){
            String dateNow = java.time.LocalDate.now().toString();
            return memory.get(country).getTime().equals(dateNow);
        }
        return false;
    }

    public void addStats(String country, ApiResponse response){
        log.info("Added stats to cache for country: " + country);
        this.updates = this.updates + 1;
        this.memory.put(country, response);
    }

    public ApiResponse getStats(String country){
        return this.memory.get(country);
    }

    public int getSize() {
        return this.memory.size();
    }

    public long getRequests() {
        return this.requests;
    }

    public long getUpdates() {
        return this.updates;
    }
}
