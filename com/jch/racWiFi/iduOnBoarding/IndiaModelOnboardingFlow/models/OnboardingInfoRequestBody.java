package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models;

import java.util.TimeZone;

public class OnboardingInfoRequestBody {
    private String city = "";
    private String country = "";
    private String name;
    private String thingPassword;
    private long timeZoneOffsetInMillis = ((long) TimeZone.getDefault().getRawOffset());
    private String vendorThingId;
    private String zipCode = "";

    public String getThingPassword() {
        return this.thingPassword;
    }

    public void setThingPassword(String str) {
        this.thingPassword = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
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

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String str) {
        this.zipCode = str;
    }

    public long getTimeZoneOffsetInMillis() {
        return this.timeZoneOffsetInMillis;
    }

    public void setTimeZoneOffsetInMillis(long j) {
        this.timeZoneOffsetInMillis = j;
    }

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }
}
