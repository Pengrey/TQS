package com.tqs.WebApp.API;

import com.tqs.WebApp.Data.Cache;
import com.tqs.WebApp.Exceptions.NoApisException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientTest {
    public Client client;
    public Cache cache;

    @BeforeEach
    public void setUp() {
        client = new Client();
        cache = new Cache();
    }

    @AfterEach
    public void tearDown() {
        client = null;
        cache = null;
    }

    @Test
    @DisplayName("Client returns false if Api is down")
    void clientReturnsFalseWhenApiDown() throws IOException, InterruptedException {
        // Country
        String country = "DoesntExist";

        // 1 - instantiate the mock substitute
        SlotixApi slotixapi = Mockito.mock( SlotixApi.class );
        CountryStatApi countrystatapi = Mockito.mock( CountryStatApi.class );

        // 3 - "teach" the required expectations (prepare the mock)
        Mockito.when( slotixapi.getURI( country )).thenReturn( "https://doesntexistatalllllllll.com" );
        Mockito.when( slotixapi.getHost()).thenReturn( "covid-19-tracking.p.rapidapi.com" );
        Mockito.when( countrystatapi.getURI( country )).thenReturn( "https://doesntexistatalllllllll.com" );
        Mockito.when( countrystatapi.getHost()).thenReturn( "covid-193.p.rapidapi.com" );

        // SlotixApi broken
        assertThat(client.apiIsUp(slotixapi), is(false));

        // CountryStatApi broken
        assertThat(client.apiIsUp(countrystatapi), is(false));
    }

    @Test
    @DisplayName("Client returns true if Api is up")
    void clientReturnsTrueWhenApiUp() throws IOException, InterruptedException {
        SlotixApi slotixapi = new SlotixApi();
        CountryStatApi countrystatapi = new CountryStatApi();

        // SlotixApi up
        assertThat(client.apiIsUp(slotixapi), is(true));

        // CountryStatApi up
        assertThat(client.apiIsUp(countrystatapi), is(true));
    }

    @Test
    @DisplayName("Client gets results from the Api call")
    void clientGetsResultsFromApi() throws IOException, InterruptedException, NoApisException, ParseException {
        // Country
        String country = "Portugal";

        // APIs
        SlotixApi slotixapi = new SlotixApi();
        CountryStatApi countrystatapi = new CountryStatApi();

        // Assert API response
        assertThat(client.getStats(cache, country, slotixapi, countrystatapi).toString().replaceAll("[0-9-]",""), is("SlotixResponse{Time='', NewCases='', NewDeaths='', TotalCases='', TotalDeaths=''}"));
    }

    @Test
    @DisplayName("Client gets results from the Cache")
    void clientGetsResultsFromCache() throws IOException, InterruptedException, NoApisException, ParseException {
        // Country
        String country = "Portugal";

        // APIs
        SlotixApi slotixapi = new SlotixApi();
        CountryStatApi countrystatapi = new CountryStatApi();

        // Generate static result
        String dateNow = java.time.LocalDate.now().toString();
        SlotixResponse response = slotixapi.parseResponse("{\"error\":false,\"statusCode\":200,\"message\":\"OK\",\"data\":{\"recovered\":null,\"deaths\":38416,\"confirmed\":3647860,\"lastChecked\":\"" + dateNow + "T19:58:07+00:00\",\"lastReported\":\"2022-04-19T04:21:00+00:00\",\"location\":\"Canada\"}}");

        // Add response to cache
        cache.addStats(country, response);

        // Assert API response
        assertThat(client.getStats(cache, country, slotixapi, countrystatapi), is(response));
    }

    @Test
    @DisplayName("Client Switches Api if first is down")
    void clientSwitchesIfApiDown() throws IOException, InterruptedException, NoApisException, ParseException {
        // Country
        String country = "Portugal";

        // Broken API
        SlotixApi slotixapi = Mockito.mock( SlotixApi.class );
        Mockito.when( slotixapi.getURI( "DoesntExist" )).thenReturn( "https://doesntexistatalllllllll.com" );
        Mockito.when( slotixapi.getHost()).thenReturn( "covid-19-tracking.p.rapidapi.com" );

        // API up
        CountryStatApi countrystatapi = new CountryStatApi();
        assertThat(client.getStats(cache, country, slotixapi, countrystatapi).toString().replaceAll("[0-9-]",""), is("SlotixResponse{Time='', NewCases='', NewDeaths='', TotalCases='', TotalDeaths=''}"));
    }

    @Test
    @DisplayName("Client gives error if both apis are down")
    void clientGivesErrorIfBothApisDown() {
        // Country
        String country = "Portugal";

        // Broken APIs mocks
        SlotixApi slotixapi = Mockito.mock( SlotixApi.class );
        CountryStatApi countrystatapi = Mockito.mock( CountryStatApi.class );
        Mockito.when( slotixapi.getURI( "DoesntExist" )).thenReturn( "https://doesntexistatalllllllll.com" );
        Mockito.when( slotixapi.getHost()).thenReturn( "covid-19-tracking.p.rapidapi.com" );
        Mockito.when( countrystatapi.getURI( "DoesntExist" )).thenReturn( "https://doesntexistatalllllllll.com" );
        Mockito.when( countrystatapi.getHost()).thenReturn( "covid-19-tracking.p.rapidapi.com" );

        assertThrows(NoApisException.class, ()->client.getStats(cache, country, slotixapi, countrystatapi));
    }
}
