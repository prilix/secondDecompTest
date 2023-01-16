package com.jch.racWiFi.amplitude.model;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;

@Metadata(mo36737d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR \u0010\f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR \u0010\u000f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR \u0010\u0012\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR \u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\b¨\u0006\u0018"}, mo36738d2 = {"Lcom/jch/racWiFi/amplitude/model/RequestBody;", "", "()V", "apiLevel", "", "getApiLevel", "()Ljava/lang/String;", "setApiLevel", "(Ljava/lang/String;)V", "appPlatform", "getAppPlatform", "setAppPlatform", "appVersion", "getAppVersion", "setAppVersion", "countryCode", "getCountryCode", "setCountryCode", "countryName", "getCountryName", "setCountryName", "deviceVersion", "getDeviceVersion", "setDeviceVersion", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: RequestBody.kt */
public final class RequestBody {
    @SerializedName("apiLevel")
    private String apiLevel;
    @SerializedName("appPlatform")
    private String appPlatform;
    @SerializedName("appVersion")
    private String appVersion;
    @SerializedName("countryCode")
    private String countryCode;
    @SerializedName("countryName")
    private String countryName;
    @SerializedName("deviceVersion")
    private String deviceVersion;

    public final String getApiLevel() {
        return this.apiLevel;
    }

    public final void setApiLevel(String str) {
        this.apiLevel = str;
    }

    public final String getAppPlatform() {
        return this.appPlatform;
    }

    public final void setAppPlatform(String str) {
        this.appPlatform = str;
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final void setAppVersion(String str) {
        this.appVersion = str;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final void setCountryCode(String str) {
        this.countryCode = str;
    }

    public final String getCountryName() {
        return this.countryName;
    }

    public final void setCountryName(String str) {
        this.countryName = str;
    }

    public final String getDeviceVersion() {
        return this.deviceVersion;
    }

    public final void setDeviceVersion(String str) {
        this.deviceVersion = str;
    }
}
