package com.tqs.WebApp.API;

import java.util.List;
import java.util.Map;

public class SlotixResponse extends ApiResponse{
    private String error, statusCode, message, recovered, deaths, confirmed, lastChecked, lastReported, location;

    public String getTime() {
        return this.lastChecked.substring(0,10);
    }

    public String getNewCases() {
        return "-";
    }

    public String getNewDeaths() {
        return "-";
    }

    public String getTotalCases() {
        return this.confirmed;
    }

    public String getTotalDeaths() {
        return this.deaths;
    }

    @Override
    public String toString() {
        return "SlotixResponse{" +
                "Time='" + this.getTime() + '\'' +
                ", NewCases='" + this.getNewCases() + '\'' +
                ", NewDeaths='" + this.getNewDeaths() + '\'' +
                ", TotalCases='" + this.getTotalCases() + '\'' +
                ", TotalDeaths='" + this.getTotalDeaths() + '\'' +
                '}';
    }
}
