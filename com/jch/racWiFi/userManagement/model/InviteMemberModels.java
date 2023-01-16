package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.GenericSuccessResponse;

public class InviteMemberModels {
    public static final String FAMILY_MEMBERS_EXCEEDS_LIMIT = "LEE001";
    public static final String FAMILY_NOT_FOUND = "NFE004";
    public static final String INVALID_CODE = "NFE005";
    public static final String INVITATION_DOESNOT_BELONG_TO_THIS_USER = "NFE007";
    public static final String INVITED_USER_IS_NOT_REGISTERED_WITH_APP = "NFE006";
    public static final String INVITING_ALREADY_EXISTING_MEMBERS = "EAE004";
    public static final String THE_USER_CANNNOT_INVITE_HIMSELF = "EAE001";
    public static final String USER_DETAILS_NOT_FOUND = "NFE002";
    public static final String USER_IS_NOT_OWNER_OF_THE_FAMILY = "NFE003";
    public static final String USER_NOT_FOUND = "NFE001";
    public static final String USER_WITH_EMAIL_ALREADY_EXIST_IN_FAMILY = "EAE002";
    public static final String USER_WITH_PHONE_NUMBER_ALREADY_EXIST_IN_FAMILY = "EAE003";

    public static class InviteMemberCodeVerificationFailure {
        @SerializedName("code")
        public String code;
        @SerializedName("desc")
        public String desc;
        @SerializedName("stackTrace")
        public String stackTrace;
        public int statusCode;
        @SerializedName("type")
        public String type;
    }

    public static class InviteMemberData {
        @SerializedName("email")
        public String email;
        @SerializedName("phoneNumber")
        public String phoneNumber;
    }

    public static class InviteMemberFailureResponse extends GenericErrorResponse {
        @SerializedName("code")
        public String code;
        @SerializedName("desc")
        public String desc;
        @SerializedName("stackTrace")
        public String stackTrace;
        @SerializedName("type")
        public String type;
    }

    public static class InviteMemberSuccessResponse extends GenericSuccessResponse {
    }
}
