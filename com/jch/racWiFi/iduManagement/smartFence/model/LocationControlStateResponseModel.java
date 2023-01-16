package com.jch.racWiFi.iduManagement.smartFence.model;

import com.google.gson.annotations.SerializedName;

public class LocationControlStateResponseModel {
    @SerializedName("enabled")
    public boolean enabled;
    @SerializedName("familyId")
    public int familyId;
    @SerializedName("familyName")
    public String familyName;
}
