package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericErrorResponse;

public class UserLoginDataModel {

    public static class LoginData {
        @SerializedName("email")
        public String email;
        @SerializedName("mobileNumber")
        public String mobileNumber;
        @SerializedName("password")
        public String password;
    }

    public static class LoginFailureResponse extends GenericErrorResponse {
        public static final String HARD_LOCK = "HARD_LOCK";
        public static final String INVALID_USER_ID_OR_PASSWORD = "Invalid UserId Or Password";
        public static final int LOCK_HAPPENED = 423;
        public static final String SOFT_LOCK = "SOFT_LOCK";
        public static final int UNAUTHORIZED = 401;
        public static final String UNKNOWN_USER = "UNKNOWN_USER";
        public static final int USER_NOT_VERIFIED = 412;
        @SerializedName("attemptLeft")
        public int attemptsLeft;
        @SerializedName("errorState")
        public String errorState;
    }
}
