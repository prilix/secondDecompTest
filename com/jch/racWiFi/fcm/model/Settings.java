package com.jch.racWiFi.fcm.model;

import com.google.gson.annotations.SerializedName;

public class Settings {
    @SerializedName("homeOnWeekdays")
    private boolean homeOnWeekdays;
    @SerializedName("homeOnWeekends")
    private boolean homeOnWeekends;
    @SerializedName("language")
    private String language;
    @SerializedName("outOfHomeAddress")
    private String outOfHomeAddress;
    @SerializedName("outOfHomeLatitude")
    private double outOfHomeLatitude;
    @SerializedName("outOfHomeLongitude")
    private double outOfHomeLongitude;
    @SerializedName("outOfHomeRadius")
    private double outOfHomeRadius;
    @SerializedName("outOfHomeRemainderEnabled")
    private boolean outOfHomeRemainderEnabled;
    @SerializedName("sensitiveToCold")
    private boolean sensitiveToCold;
    @SerializedName("temperatureUnit")
    private String temperatureUnit;

    public String getOutOfHomeAddress() {
        return this.outOfHomeAddress;
    }

    public void setOutOfHomeAddress(String str) {
        this.outOfHomeAddress = str;
    }

    public boolean isSensitiveToCold() {
        return this.sensitiveToCold;
    }

    public void setSensitiveToCold(boolean z) {
        this.sensitiveToCold = z;
    }

    public String getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public void setTemperatureUnit(String str) {
        this.temperatureUnit = str;
    }

    public double getOutOfHomeLongitude() {
        return this.outOfHomeLongitude;
    }

    public void setOutOfHomeLongitude(double d) {
        this.outOfHomeLongitude = d;
    }

    public boolean isHomeOnWeekdays() {
        return this.homeOnWeekdays;
    }

    public void setHomeOnWeekdays(boolean z) {
        this.homeOnWeekdays = z;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public double getOutOfHomeRadius() {
        return this.outOfHomeRadius;
    }

    public void setOutOfHomeRadius(double d) {
        this.outOfHomeRadius = d;
    }

    public boolean isHomeOnWeekends() {
        return this.homeOnWeekends;
    }

    public void setHomeOnWeekends(boolean z) {
        this.homeOnWeekends = z;
    }

    public boolean isOutOfHomeRemainderEnabled() {
        return this.outOfHomeRemainderEnabled;
    }

    public void setOutOfHomeRemainderEnabled(boolean z) {
        this.outOfHomeRemainderEnabled = z;
    }

    public double getOutOfHomeLatitude() {
        return this.outOfHomeLatitude;
    }

    public void setOutOfHomeLatitude(double d) {
        this.outOfHomeLatitude = d;
    }
}
