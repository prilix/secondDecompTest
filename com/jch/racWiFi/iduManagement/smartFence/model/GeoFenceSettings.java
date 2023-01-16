package com.jch.racWiFi.iduManagement.smartFence.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

public class GeoFenceSettings {
    @SerializedName("arrivingRadius")
    private Long mArrivingRadius;
    @SerializedName("latitude")
    private Double mLatitude;
    @SerializedName("leavingRadius")
    private Long mLeavingRadius;
    @SerializedName("longitude")
    private Double mLongitude;

    public Long getArrivingRadius() {
        return this.mArrivingRadius;
    }

    public void setArrivingRadius(Long l) {
        this.mArrivingRadius = l;
    }

    public Double getLatitude() {
        return this.mLatitude;
    }

    public void setLatitude(Double d) {
        this.mLatitude = d;
    }

    public Long getLeavingRadius() {
        return this.mLeavingRadius;
    }

    public void setLeavingRadius(Long l) {
        this.mLeavingRadius = l;
    }

    public Double getLongitude() {
        return this.mLongitude;
    }

    public void setLongitude(Double d) {
        this.mLongitude = d;
    }

    public LatLng getLatLng() {
        return new LatLng(this.mLatitude.doubleValue(), this.mLongitude.doubleValue());
    }
}
