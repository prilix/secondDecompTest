package com.jch.racWiFi.iduManagement.smartFence.model;

import com.github.mikephil.charting.utils.C1030Utils;
import com.google.gson.annotations.SerializedName;

public class RacParameterForArriving {
    @SerializedName("fanSpeed")
    private String fanSpeed;
    @SerializedName("fanSwing")
    private String fanSwing;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("mode")
    private String mode;
    @SerializedName("powerMode")
    private String powerMode;
    @SerializedName("relativeTemperature")
    private Double relativeTemperature;
    @SerializedName("temperature")
    private Double temperature;

    public int getHumidity() {
        return this.humidity;
    }

    public void setHumidity(int i) {
        this.humidity = i;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public String getPowerMode() {
        return this.powerMode;
    }

    public void setPowerMode(String str) {
        this.powerMode = str;
    }

    public Double getRelativeTemperature() {
        Double d = this.relativeTemperature;
        if (d != null) {
            return d;
        }
        return Double.valueOf(C1030Utils.DOUBLE_EPSILON);
    }

    public void setRelativeTemperature(Double d) {
        this.relativeTemperature = d;
    }

    public Double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Double d) {
        this.temperature = d;
    }

    public String getFanSpeed() {
        return this.fanSpeed;
    }

    public void setFanSpeed(String str) {
        this.fanSpeed = str;
    }

    public String getFanSwing() {
        return this.fanSwing;
    }

    public void setFanSwing(String str) {
        this.fanSwing = str;
    }
}
