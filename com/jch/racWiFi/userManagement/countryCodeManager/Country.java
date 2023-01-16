package com.jch.racWiFi.userManagement.countryCodeManager;

public class Country {
    private String countryCode;
    private final int iso;
    private final int iso3Letter = -1;
    private String isoCode;
    private final int name;
    private final int phoneCode;

    public String getIsoCode() {
        return this.isoCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCodeAndIsoCode(String str, String str2) {
        this.countryCode = str;
        this.isoCode = str2;
    }

    public Country(int i, int i2, int i3) {
        this.iso = i;
        this.phoneCode = i2;
        this.name = i3;
    }

    public int getIso() {
        return this.iso;
    }

    public int getPhoneCode() {
        return this.phoneCode;
    }

    public int getName() {
        return this.name;
    }
}
