package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.GenericSuccessResponse;

public class UserRegistrationModels {

    public static class UserRegistration {
        public static UserRegistration NEW_USER = new UserRegistration();
        @SerializedName("email")
        public String email;
        @SerializedName("firstName")
        public String firstName;
        @SerializedName("inviteBy")
        public String inviteBy;
        @SerializedName("inviteeEmail")
        public String inviteeEmail;
        @SerializedName("lastName")
        public String lastName;
        @SerializedName("otp")
        public String mOtp;
        @SerializedName("middleName")
        public String middleName;
        @SerializedName("mobileNumber")
        public String mobileNumber;
        @SerializedName("password")
        public String password;
        @SerializedName("role")
        public String role;
        @SerializedName("verificationRequired")
        public boolean verificationRequired;
    }

    public static class UserRegistrationFailureResponse extends GenericErrorResponse {
        public static final String USER_ALREADY_EXISTS_WITH_EMAIL = "User already exists with this EmailId";
    }

    public static class UserRegistrationSuccessResponse extends GenericSuccessResponse {
        public ResendOTPTokenData resendOTPTokenData = new ResendOTPTokenData();
    }
}
