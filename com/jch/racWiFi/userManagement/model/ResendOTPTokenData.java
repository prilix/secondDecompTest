package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;

public class ResendOTPTokenData {
    public static ResendOTPTokenData CURRENT = new ResendOTPTokenData();
    @SerializedName("token")
    private String resendOtpToken;

    public String getResendOtpToken() {
        return this.resendOtpToken;
    }

    public void setResendOtpToken(String str) {
        this.resendOtpToken = str;
    }

    public ResendOTPTokenData() {
    }

    public ResendOTPTokenData(ResendOTPTokenData resendOTPTokenData) {
        this.resendOtpToken = resendOTPTokenData.resendOtpToken;
    }

    public void copy(ResendOTPTokenData resendOTPTokenData) {
        this.resendOtpToken = resendOTPTokenData.resendOtpToken;
    }
}
