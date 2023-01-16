package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;

public class RemoveFromFamilyNotificationModel {
    @SerializedName("familyName")
    public String familyName;
    @SerializedName("fromFamilyId")
    public int fromFamilyId;
    @SerializedName("ownerDetailsId")
    public int ownerDetailsId;
    @SerializedName("removedUserDetailsId")
    public int removedUserDetailsId;
}
