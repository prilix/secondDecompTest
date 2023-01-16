package com.jch.racWiFi.userManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.userManagement.model.dto.RoleDto;
import java.util.List;

public class UserFamilyMemberModels {

    public static class UserFamilyMemberFailureResponse extends GenericErrorResponse {
        public static final String FAMILY_MEMBERS_NOT_FOUND = "NFE013";
        public static final String MEMBER_NOT_ALLOWED_TO_CHANGE_THE_ROLE = "FAE004";
        public static final String ROLE_NOT_FOUND = "NFE008";
        public static final String ROLE_OF_CREATOR_CANNOT_BE_CHANGED = "FAE005";
        public static final int UNAUTHORIZED = 403;
        public static final String USER_CANNOT_CHANGE_HIS_OWN_ROLE = "FAE003";
        public static final String USER_CANNOT_REMOVE_HIMSELF = "FAE009";
        public static final String USER_DETAILS_NOT_FOUND = "NFE002";
        public static final String USER_DOES_NOT_HAVE_THE_PERMISSION = "FAE007";
        public static final String USER_ISNOT_FAMILY_MEMBER = "NFE009";
        public static final String USER_IS_NOT_OWNER_OF_THE_FAMILY = "NFE003";
        @SerializedName("code")
        public String code;
        public boolean deleteUser;
        @SerializedName("desc")
        public String desc;
        @SerializedName("stackTrace")
        public String stackTrace;
        @SerializedName("type")
        public String type;
    }

    public static class UserFamilyMemberSuccessResponse extends GenericErrorResponse {
        public boolean deleteUser;
        @SerializedName("message")
        public String message;
        @SerializedName("result")
        public List<UserFamilyMemberInfo> userFamilyMemberInfo;
    }

    public static class UserFamilyMemberInfo implements Parcelable {
        public static final Parcelable.Creator<UserFamilyMemberInfo> CREATOR = new Parcelable.Creator<UserFamilyMemberInfo>() {
            public UserFamilyMemberInfo createFromParcel(Parcel parcel) {
                return new UserFamilyMemberInfo(parcel);
            }

            public UserFamilyMemberInfo[] newArray(int i) {
                return new UserFamilyMemberInfo[i];
            }
        };
        public static final String USER_FAMILY_MEMBER = "user_family_member";
        @SerializedName("detailsUserInfoId")
        public int detailsUserInfoId;
        @SerializedName("firstName")
        public String firstName;
        @SerializedName("lastName")
        public String lastName;
        @SerializedName("middleName")
        public String middleName;
        @SerializedName("pictureData")
        public ProfilePicture profilePicture;
        @SerializedName("role")
        public RoleDto role;
        @SerializedName("id")
        public int userId;

        public int describeContents() {
            return 0;
        }

        protected UserFamilyMemberInfo(Parcel parcel) {
            this.detailsUserInfoId = parcel.readInt();
            this.firstName = parcel.readString();
            this.middleName = parcel.readString();
            this.lastName = parcel.readString();
            this.role = (RoleDto) parcel.readParcelable(RoleDto.class.getClassLoader());
            this.userId = parcel.readInt();
            this.profilePicture = (ProfilePicture) parcel.readParcelable(ProfilePicture.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.detailsUserInfoId);
            parcel.writeString(this.firstName);
            parcel.writeString(this.middleName);
            parcel.writeString(this.lastName);
            parcel.writeParcelable(this.role, i);
            parcel.writeInt(this.userId);
            parcel.writeParcelable(this.profilePicture, i);
        }

        public void copy(UserFamilyMemberInfo userFamilyMemberInfo) {
            this.detailsUserInfoId = userFamilyMemberInfo.detailsUserInfoId;
            this.firstName = userFamilyMemberInfo.firstName;
            this.middleName = userFamilyMemberInfo.middleName;
            this.lastName = userFamilyMemberInfo.lastName;
            RoleDto roleDto = new RoleDto();
            this.role = roleDto;
            roleDto.copy(userFamilyMemberInfo.role);
            this.userId = userFamilyMemberInfo.userId;
            this.profilePicture = userFamilyMemberInfo.profilePicture;
        }

        public UserFamilyMemberInfo() {
        }
    }
}
