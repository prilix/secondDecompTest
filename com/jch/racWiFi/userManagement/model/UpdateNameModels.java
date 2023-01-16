package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;

public class UpdateNameModels {

    public static class UpdateName {
        @SerializedName("firstName")
        public String firstName;
        @SerializedName("lastName")
        public String lastName;
        @SerializedName("middleName")
        public String middleName;
    }
}
