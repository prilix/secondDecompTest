package com.jch.racWiFi.iduManagement.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class RefreshRequestPayload {
    @SerializedName("racId")
    public int racId;
    @SerializedName("requestType")
    public String requestType;

    public String toJson() {
        return new Gson().toJson((Object) this);
    }
}
