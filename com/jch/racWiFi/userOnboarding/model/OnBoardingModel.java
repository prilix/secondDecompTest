package com.jch.racWiFi.userOnboarding.model;

import java.util.TimeZone;

public class OnBoardingModel {
    private String adapterType = "";
    private String city;
    private String country = "";
    private String name = "";
    private String racName = "";
    private String routerSSID = "";
    private String thingPassword = "";
    private long timeZoneOffsetInMillis = ((long) TimeZone.getDefault().getRawOffset());
    private String vendorThingId = "";
    private String zipCode;
    private String zoneId = TimeZone.getDefault().getID();

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

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }

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

    public String getAdapterType() {
        return this.adapterType;
    }

    public void setAdapterType(String str) {
        this.adapterType = str;
    }

    public String getRouterSSID() {
        return this.routerSSID;
    }

    public void setRouterSSID(String str) {
        this.routerSSID = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public long getTimeZoneOffsetInMillis() {
        return this.timeZoneOffsetInMillis;
    }

    public void setTimeZoneOffsetInMillis(long j) {
        this.timeZoneOffsetInMillis = j;
    }

    public String getZoneId() {
        return this.zoneId;
    }

    public void setZoneId(String str) {
        this.zoneId = str;
    }

    public String getRacName() {
        return this.racName;
    }

    public void setRacName(String str) {
        this.racName = str;
    }
}
