package com.jch.racWiFi.energyConsumption.model.response;

import java.util.HashMap;

public class RacWiseEnergyUsageResponseMain {
    private HashMap<String, Double> data;
    private long dataAvailableFrom;
    private long lastUpdatedOn;
    private String vendorThingId;

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }

    public HashMap<String, Double> getData() {
        return this.data;
    }

    public void setData(HashMap<String, Double> hashMap) {
        this.data = hashMap;
    }

    public long getLastUpdatedOn() {
        return this.lastUpdatedOn;
    }

    public void setLastUpdatedOn(long j) {
        this.lastUpdatedOn = j;
    }

    public long getDataAvailableFrom() {
        return this.dataAvailableFrom;
    }

    public void setDataAvailableFrom(long j) {
        this.dataAvailableFrom = j;
    }

    public String toString() {
        return "ClassPojo [vendorThingId = " + this.vendorThingId + ", data = " + this.data + ", lastUpdatedOn = " + this.lastUpdatedOn + ", dataAvailableFrom = " + this.dataAvailableFrom + "]";
    }
}
