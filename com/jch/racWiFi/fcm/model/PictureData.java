package com.jch.racWiFi.fcm.model;

import com.google.gson.annotations.SerializedName;

public class PictureData {
    @SerializedName("profilePictureUrl")
    private String profilePictureUrl;
    @SerializedName("updatedOn")
    private long updatedOn;

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String str) {
        this.profilePictureUrl = str;
    }

    public long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(long j) {
        this.updatedOn = j;
    }
}
