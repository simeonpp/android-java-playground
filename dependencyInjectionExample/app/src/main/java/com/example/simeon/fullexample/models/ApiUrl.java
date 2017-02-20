package com.example.simeon.fullexample.models;

import javax.inject.Inject;
import javax.inject.Named;

public class ApiUrl<T> {
    private String apiBaseUrl;
    private String suffix;

    public ApiUrl(String apiBaseUrl, String suffix) {
        this.apiBaseUrl = apiBaseUrl;
        this.suffix = suffix;
    }

    public String getUrl() {
        return this.apiBaseUrl + this.suffix;
    }
}
