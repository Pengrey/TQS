package com.tqs.WebApp.Controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MainControllerTest {
    // Api tests

    public String doHttpGet(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        }
    }

    @Test
    public void whenGoodCountry_returnStats() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newCases\":\"\",\"newDeaths\":\"\",\"totalCases\":\"\",\"totalDeaths\":\"\"}"));
    }

    @Test
    public void whenBadCountry_returnNothing() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Prtugal");
        assertThat(result.replaceAll("[0-9-]",""), is("{}"));
    }

    @Test
    public void whenFilterOutnewCases_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newCases=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newDeaths\":\"\",\"totalCases\":\"\",\"totalDeaths\":\"\"}"));
    }

    @Test
    public void whenFilterOutnewDeaths_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newDeaths=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newCases\":\"\",\"totalCases\":\"\",\"totalDeaths\":\"\"}"));
    }

    @Test
    public void whenFilterOuttotalCases_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?totalCases=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newCases\":\"\",\"newDeaths\":\"\",\"totalDeaths\":\"\"}"));
    }

    @Test
    public void whenFilterOuttotalDeaths_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?totalDeaths=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newCases\":\"\",\"newDeaths\":\"\",\"totalCases\":\"\"}"));
    }

    @Test
    public void whenFilterOutnewCasesnewDeaths_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newCases=0&newDeaths=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"totalCases\":\"\",\"totalDeaths\":\"\"}"));
    }

    @Test
    public void whenFilterOutnewCasestotalCases_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newCases=0&totalCases=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newDeaths\":\"\",\"totalDeaths\":\"\"}"));
    }

    @Test
    public void whenFilterOutnewCasestotalDeaths_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newCases=0&totalDeaths=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newDeaths\":\"\",\"totalCases\":\"\"}"));
    }

    @Test
    public void whenFilterOuttotalCasestotalDeaths_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?totalDeaths=0&totalCases=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newCases\":\"\",\"newDeaths\":\"\"}"));
    }

    @Test
    public void whenFilterOutnewDeathstotalCases_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newDeaths=0&totalCases=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newCases\":\"\",\"totalDeaths\":\"\"}"));
    }

    @Test
    public void whenFilterOutnewDeathstotalDeaths_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newDeaths=0&totalDeaths=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newCases\":\"\",\"totalCases\":\"\"}"));
    }

    @Test
    public void whenFilterOutnewCasesnewDeathstotalCases_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newCases=0&newDeaths=0&totalCases=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"totalDeaths\":\"\"}"));
    }

    @Test
    public void whenFilterOutnewCasesnewDeathstotalDeaths_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newCases=0&newDeaths=0&totalDeaths=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"totalCases\":\"\"}"));
    }

    @Test
    public void whenFilterOutnewCasestotalCasestotalDeaths_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newCases=0&totalCases=0&totalDeaths=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\",\"newDeaths\":\"\"}"));
    }

    @Test
    public void whenFilterOutnewCasesnewDeathstotalCasestotalDeaths_getRest() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/stats/Portugal?newCases=0&newDeaths=0&totalCases=0&totalDeaths=0");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"time\":\"\"}"));
    }

    @Test
    public void getCacheStatusFromApi() throws IOException {
        String result = doHttpGet("http://localhost:8080/api/v1/cache");
        assertThat(result.replaceAll("[0-9-]",""), is("{\"requests\":,\"updates\":,\"size\":}"));
    }
}
