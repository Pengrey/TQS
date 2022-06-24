package com.tqs.WebApp.API;

public abstract class ApiResponse {
    public abstract String getTime();
    public abstract String getNewCases();
    public abstract String getNewDeaths();
    public abstract String getTotalCases();
    public abstract String getTotalDeaths();
}
