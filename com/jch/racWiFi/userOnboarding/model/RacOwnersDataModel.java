package com.jch.racWiFi.userOnboarding.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.userManagement.model.ProfilePicture;

public class RacOwnersDataModel {

    public static class FamilyGroupsModelResponseSuccessFailure extends GenericErrorResponse {
        public int statusCode;
    }

    public static class FamilyResult {
        @SerializedName("createdBy")
        public String createdBy;
        @SerializedName("creatorProfilePic")
        public String creatorProfilePic;
        @SerializedName("familyId")
        public int familyId = -1;
        @SerializedName("familyName")
        public String familyName;
        @SerializedName("pictureData")
        public ProfilePicture pictureData;
        @SerializedName("role")
        public Role role;
    }

    public static class RacOwnersDataModelResponseSuccess extends GenericErrorResponse {
        @SerializedName("result")
        public FamilyResult[] familyResult;
        @SerializedName("message")
        public String message;
    }

    public static class Role {
        @SerializedName("id")

        /* renamed from: id */
        public int f488id;
        @SerializedName("level")
        public String level;
        @SerializedName("name")
        public String name;
    }
}
