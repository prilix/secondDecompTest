package com.jch.racWiFi.userManagement;

import com.google.gson.annotations.SerializedName;

public class InviteVerificationModels {

    public static class InviteVerificationFailureResponse {
        public int statusCode;
    }

    public static class InviteVerificationRequestData {
        @SerializedName("token")
        public String token;
    }

    public static class InviteVerificationSuccessResponse {
        @SerializedName("doesExist")
        public boolean doesExist;
        public int statusCode;
    }
}
