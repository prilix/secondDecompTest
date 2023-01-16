package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;

public class ForgotPassordModel {
    @SerializedName("email")
    public String email;
    @SerializedName("mobileNumber")
    public String mobileNumber;
    private String secret = "";

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String str) {
        this.secret = str;
    }
}
