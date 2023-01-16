package com.jch.racWiFi.linking.amazon.model;

import com.google.gson.annotations.SerializedName;

public class TokenRequest {
    @SerializedName("authcode")
    public String authCode;
    @SerializedName("redirecturi")
    public String redirectUri;

    public TokenRequest(String str, String str2) {
        this.authCode = str;
        this.redirectUri = str2;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public void setAuthCode(String str) {
        this.authCode = str;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public void setRedirectUri(String str) {
        this.redirectUri = str;
    }
}
