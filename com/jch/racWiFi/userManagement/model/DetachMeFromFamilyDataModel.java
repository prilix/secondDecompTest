package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericSuccessResponse;

public class DetachMeFromFamilyDataModel {

    public static class DetachMeFromFamilyDataModelFailureResponse extends GenericSuccessResponse {
        public static final String FAMILY_OWNERSGHIP_NEEDS_TO_BE_TRANSFER = "FAE006";
        public static final String ROLE_NOT_FOUND = "NFE008";
        public static final String USER_DETAILS_NOT_FOUND = "NFE002";
        public static final String USER_ISNOT_FAMILY_MEMBER = "NFE009";
        @SerializedName("code")
        public String code;
        @SerializedName("desc")
        public String desc;
        @SerializedName("stackTrace")
        public String stackTrace;
        @SerializedName("type")
        public String type;
    }

    public static class DetachMeFromFamilyDataModelSuccessResponse extends GenericSuccessResponse {
    }
}
