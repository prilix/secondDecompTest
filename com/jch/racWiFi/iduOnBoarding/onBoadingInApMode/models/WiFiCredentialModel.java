package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.models;

public class WiFiCredentialModel {
    public static final WiFiCredentialModel CURRENT_HOME_ROUTER_CREDENTIALS = new WiFiCredentialModel();
    public static final WiFiCredentialModel CURRENT_RAC_CREDENTIALS = new WiFiCredentialModel();
    private String adapterType = "";
    private String capability;
    private String password = "";
    private String ssid = "";

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str.replaceAll("^\"|\"$", "");
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getCapability() {
        return this.capability;
    }

    public void setCapability(String str) {
        this.capability = str;
    }

    public String getAdapterType() {
        return this.adapterType;
    }

    public void setAdapterType(String str) {
        this.adapterType = str;
    }
}
