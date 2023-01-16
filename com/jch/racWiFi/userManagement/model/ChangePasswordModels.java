package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.GenericSuccessResponse;

public class ChangePasswordModels {

    public static class ChangePasswordData {
        @SerializedName("confirmPassword")
        public String confirmPassword;
        @SerializedName("password")
        public String newPassword;
        @SerializedName("oldPassword")
        public String oldPassword;
    }

    public static class ChangePasswordFailureResponse extends GenericErrorResponse {
        public static final String OLD_PASSWORD_INCORRECT = "The old password is incorrect";
        public String message;
    }

    public static class ChangePasswordSuccessResponse extends GenericSuccessResponse {
    }
}
