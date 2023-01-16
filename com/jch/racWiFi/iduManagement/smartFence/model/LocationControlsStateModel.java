package com.jch.racWiFi.iduManagement.smartFence.model;

import com.google.gson.annotations.SerializedName;

public class LocationControlsStateModel {
    @SerializedName("enabled")
    public boolean enabled;

    public LocationControlsStateModel(boolean z) {
        this.enabled = z;
    }
}
