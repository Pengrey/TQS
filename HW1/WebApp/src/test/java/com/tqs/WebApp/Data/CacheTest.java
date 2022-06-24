package com.tqs.WebApp.Data;

import com.tqs.WebApp.API.SlotixApi;
import com.tqs.WebApp.API.SlotixResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CacheTest {
    public Cache cache;

    @BeforeEach
    public void setUp() {
        cache = new Cache();
    }

    @AfterEach
    public void tearDown() {
        cache = null;
    }

    @Test
    @DisplayName("If its added response to cache, it should save it")
    void cacheAddStats() throws ParseException {
        String country = "Portugal";
        // Create Api and generate static response
        SlotixApi api = new SlotixApi();
        SlotixResponse response = api.parseResponse("{\"error\":false,\"statusCode\":200,\"message\":\"OK\",\"data\":{\"recovered\":null,\"deaths\":38416,\"confirmed\":3647860,\"lastChecked\":\"2022-04-19T19:58:07+00:00\",\"lastReported\":\"2022-04-19T04:21:00+00:00\",\"location\":\"Canada\"}}");

        // Add response to cache
        cache.addStats(country, response);

        // Check if saved result is the same that was passed in
        assertThat(cache.getStats(country), is(response));

        // Check Stats of cache
        assertThat(cache.getSize(), is(1));
        assertThat(cache.getRequests(), is(0L));
        assertThat(cache.getUpdates(), is(1L));
    }

    @Test
    @DisplayName("If cache is checked for an unkown country it should say record isn't present.")
    void cacheCheckNonExistentStats() throws ParseException {
        String country = "Doesnt exist";
        assertThat(cache.containsRecord(country), is(false));

        // Check Stats of cache
        assertThat(cache.getSize(), is(0));
        assertThat(cache.getRequests(), is(1L));
        assertThat(cache.getUpdates(), is(0L));
    }

    @Test
    @DisplayName("If cache is checked for a country that was added in the same day it should say record is present.")
    void cacheCheckTodayExistentStats() throws ParseException {
        String country = "Exists";

        // Create Api and generate static response
        SlotixApi api = new SlotixApi();
        String dateNow = java.time.LocalDate.now().toString();
        SlotixResponse response = api.parseResponse("{\"error\":false,\"statusCode\":200,\"message\":\"OK\",\"data\":{\"recovered\":null,\"deaths\":38416,\"confirmed\":3647860,\"lastChecked\":\"" + dateNow + "T19:58:07+00:00\",\"lastReported\":\"2022-04-19T04:21:00+00:00\",\"location\":\"Canada\"}}");

        // Add response to cache
        cache.addStats(country, response);

        // Check cache and assert that the response is saved
        assertThat(cache.containsRecord(country), is(true));

        // Check Stats of cache
        assertThat(cache.getSize(), is(1));
        assertThat(cache.getRequests(), is(1L));
        assertThat(cache.getUpdates(), is(1L));
    }

    @Test
    @DisplayName("If cache is checked for a country that was added not on the same day it should say record is not present.")
    void cacheCheckAnotherDayExistentStats() throws ParseException {
        String country = "Shouldnt Exist";

        // Create Api and generate static response
        SlotixApi api = new SlotixApi();
        String dateYesterday = java.time.LocalDate.now().minusDays(1).toString();
        SlotixResponse response = api.parseResponse("{\"error\":false,\"statusCode\":200,\"message\":\"OK\",\"data\":{\"recovered\":null,\"deaths\":38416,\"confirmed\":3647860,\"lastChecked\":\"" + dateYesterday + "T19:58:07+00:00\",\"lastReported\":\"2022-04-19T04:21:00+00:00\",\"location\":\"Canada\"}}");

        // Add response to cache
        cache.addStats(country, response);

        // Check cache and assert that the response is saved
        assertThat(cache.containsRecord(country), is(false));

        // Check Stats of cache
        assertThat(cache.getSize(), is(1));
        assertThat(cache.getRequests(), is(1L));
        assertThat(cache.getUpdates(), is(1L));
    }
}