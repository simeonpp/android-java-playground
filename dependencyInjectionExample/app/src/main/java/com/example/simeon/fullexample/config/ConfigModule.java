package com.example.simeon.fullexample.config;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfigModule {
    @Provides
    @Named("streetName")
    String provideStreetName() {
        return "bul. Ivan Vazov 10";
    }

    @Provides
    @Named("apiBaseUrl")
    String provideBaseUrl() {
        return "http://192.168.0.100:3001/";
    }
}
