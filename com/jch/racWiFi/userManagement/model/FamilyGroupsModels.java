package com.jch.racWiFi.userManagement.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.GenericSuccessResponse;

public class FamilyGroupsModels {

    public static class FamilyGroupsModelResponseSuccess extends GenericSuccessResponse {
        @SerializedName("result")
        public FamilyResult[] familyResult;
        @SerializedName("message")
        public String message;
    }

    public static class FamilyGroupsModelResponseSuccessFailure extends GenericErrorResponse {
        public static final String FAMILY_MEMBERS_NOT_FOUND = "NFE013";
        public static final String USER_DETAILS_NOT_FOUND = "NFE002";
        @SerializedName("code")
        public String code;
        @SerializedName("details")
        public String details;
        @SerializedName("message")
        public String message;
        public int statusCode;
        @SerializedName("time")
        public String time;
    }

    public static class FamilyResult {
        @SerializedName("createdBy")
        public String createdBy;
        @SerializedName("pictureData")
        public ProfilePicture creatorProfilePic;
        @SerializedName("familyId")
        public int familyId = -1;
        @SerializedName("familyName")
        public String familyName;
        @SerializedName("role")
        public Role role = new Role();

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof FamilyResult) && this.familyId == ((FamilyResult) obj).familyId) {
                return true;
            }
            return false;
        }

        public String getFamilyName() {
            String str = this.familyName;
            if (str != null) {
                return str;
            }
            return null;
        }
    }

    public static class Role {
        @SerializedName("id")

        /* renamed from: id */
        public int f480id = 2;
        @SerializedName("level")
        public String level;
        @SerializedName("name")
        public String name;

        public enum UserRole {
            OWNER,
            MEMBER,
            CHILD
        }

        public boolean isChild() {
            return this.f480id == 3;
        }

        public boolean isMember() {
            return this.f480id == 2;
        }

        public boolean isOwner() {
            return this.f480id == 1;
        }

        public static UserRole getUserRoleFromRoleId(int i) {
            return UserRole.values()[i - 1];
        }
    }

    public static class SelectedFamilyGroup {
        public String selectedFamilyCreatedBy;
        public int selectedFamilyId;
        public String selectedFamilyName;

        public void copy() {
            this.selectedFamilyId = this.selectedFamilyId;
            this.selectedFamilyName = this.selectedFamilyName;
            this.selectedFamilyCreatedBy = this.selectedFamilyCreatedBy;
        }
    }
}
