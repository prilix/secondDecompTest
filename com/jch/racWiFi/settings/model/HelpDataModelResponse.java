package com.jch.racWiFi.settings.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class HelpDataModelResponse {
    @SerializedName("globalLink")
    public ArrayList<String> globalLink;
    @SerializedName("manualLinks")
    public ArrayList<String> manualLinks;
    @SerializedName("regionalLink")
    public ArrayList<String> regionalLink;
}
