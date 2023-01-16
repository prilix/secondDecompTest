package com.jch.racWiFi.fcm.model;

import com.google.gson.annotations.SerializedName;

public class TokenRequest {
    @SerializedName("deviceToken")
    public String deviceToken;
    @SerializedName("deviceType")
    public String deviceType;

    public TokenRequest(String str, String str2) {
        this.deviceToken = str;
        this.deviceType = str2;
    }

    public String getDeviceToken() {
        return this.deviceToken;
    }

    public void setDeviceToken(String str) {
        this.deviceToken = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}
