package com.jch.racWiFi.fcm.model;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("addressLine")
    private String addressLine;
    @SerializedName("city")
    private String city;
    @SerializedName("countryCode")
    private String countryCode;
    @SerializedName("state")
    private String state;
    @SerializedName("street")
    private String street;
    @SerializedName("zipCode")
    private String zipCode;

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String str) {
        this.zipCode = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String str) {
        this.street = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getAddressLine() {
        return this.addressLine;
    }

    public void setAddressLine(String str) {
        this.addressLine = str;
    }
}
