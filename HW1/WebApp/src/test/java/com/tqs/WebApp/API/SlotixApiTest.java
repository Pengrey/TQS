package com.tqs.WebApp.API;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SlotixApiTest {
    public SlotixApi api;

    @BeforeEach
    public void setUp() {
        api = new SlotixApi();
    }

    @AfterEach
    public void tearDown() {
        api = null;
    }

    @Test
    @DisplayName("Get right URI for country")
    void apiGetURIForCountry() {
        String result = api.getURI("Portugal");
        assertThat(result, is("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/total?country=Portugal"));
    }

    @Test
    @DisplayName("Get right Host for API")
    void apiGetApiHost() {
        assertThat(api.getHost(), is("covid-19-coronavirus-statistics.p.rapidapi.com"));
    }

    @Test
    @DisplayName("Parse result from API to object")
    void apiParseResult() {
        SlotixResponse parsedResponse = api.parseResponse("{\"error\":false,\"statusCode\":200,\"message\":\"OK\",\"data\":{\"recovered\":null,\"deaths\":38416,\"confirmed\":3647860,\"lastChecked\":\"2022-04-19T19:58:07+00:00\",\"lastReported\":\"2022-04-19T04:21:00+00:00\",\"location\":\"Canada\"}}");
        assertThat(parsedResponse.toString(), is("SlotixResponse{Time='2022-04-19', NewCases='-', NewDeaths='-', TotalCases='3647860', TotalDeaths='38416'}"));
    }
}
