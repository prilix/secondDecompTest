package com.jch.racWiFi.weather.model;

import java.io.Serializable;

public class Location implements Serializable {
    private String city;
    private String country;
    private float latitude;
    private float longitude;
    private long sunrise;
    private long sunset;

    public float getLongitude() {
        return this.longitude;
    }

    public void setLongitude(float f) {
        this.longitude = f;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(float f) {
        this.latitude = f;
    }

    public long getSunset() {
        return this.sunset;
    }

    public void setSunset(long j) {
        this.sunset = j;
    }

    public long getSunrise() {
        return this.sunrise;
    }

    public void setSunrise(long j) {
        this.sunrise = j;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void copy(Location location) {
        this.longitude = location.longitude;
        this.latitude = location.latitude;
        this.sunset = location.sunset;
        this.sunrise = location.sunrise;
        this.country = location.country;
        this.city = location.city;
    }
}
