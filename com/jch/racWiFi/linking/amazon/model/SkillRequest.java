package com.jch.racWiFi.linking.amazon.model;

import com.google.gson.annotations.SerializedName;

public class SkillRequest {
    @SerializedName("oauth2code")
    public String code;
    @SerializedName("amazon_token")
    public String token;
    @SerializedName("redirecturi")
    public String uri;

    public SkillRequest(String str, String str2, String str3) {
        this.code = str;
        this.uri = str2;
        this.token = str3;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
