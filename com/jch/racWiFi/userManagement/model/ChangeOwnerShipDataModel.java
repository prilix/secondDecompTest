package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericSuccessResponse;

public class ChangeOwnerShipDataModel {

    public static class ChangeOwnerShipDataModelFailureResponse extends GenericSuccessResponse {
        public static final String FAMILY_MEMBERS_EXCEEDS_LIMIT = "LEE001";
        public static final String FAMILY_NOT_FOUND = "NFE004";
        public static final String MEMBER_CANNOT_TRANSFER_OWNERSHIP = "FAE002";
        public static final String USER_CANNOT_TRANSFER_OWNERSHIP_TO_HIMESELF = "FAE001";
        public static final String USER_DETAILS_NOT_FOUND = "NFE002";
        public static final String USER_IS_NOT_CREATOR_OF_THE_FAMILY = "NFE010";
        public static final String USER_IS_NOT_FAMILY_MEMBER = "NFE009";
        @SerializedName("code")
        public String code;
        @SerializedName("details")
        public String details;
        @SerializedName("message")
        public String message;
        @SerializedName("time")
        public String time;
    }
}
