package com.tqs.WebApp.API;

public abstract class Api {
    abstract String getURI(String country);
    abstract String getHost();
    abstract ApiResponse parseResponse(String response);
}
