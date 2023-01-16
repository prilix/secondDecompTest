package com.jch.racWiFi.userManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.GenericSuccessResponse;

public class DisableAccountDataModels {
    public static String ACTIVE_USER = "EAE005";
    public static int INACTIVE_USER_CODE = 403;
    public static String INACTIVE_USER_SUB_CODE = "IUE001";
    public static String INVALID_OTP = "IOE001";
    public static String TRANSFER_OWNERSHIP = "FAE006";
    public static String USER_DETALIS_NOT_FOUND = "NFE002";
    public static String USER_NOT_FOUND = "NFE001";

    public static class DisableAccountFailureResponse {
        @SerializedName("code")
        public String code;
        @SerializedName("desc")
        public String desc;
        @SerializedName("stackTrace")
        public String stackTrace;
        @SerializedName("type")
        public String type;
    }

    public static class DisableAccountSuccessResponse extends GenericSuccessResponse {
    }

    public static class EnableAccountDataModel {
        @SerializedName("email")
        public String email;
        @SerializedName("mobileNumber")
        public String mobileNumber;
        @SerializedName("otp")
        public String otp;
    }

    public static class EnableAccountFailureResponse extends GenericErrorResponse {
        @SerializedName("code")
        public String code;
        @SerializedName("desc")
        public String desc;
        @SerializedName("stackTrace")
        public String stackTrace;
        @SerializedName("type")
        public String type;
    }

    public static class InitiateAccountActivationFailureResponse extends GenericErrorResponse {
        public static final String ACTIVE_USER_EXIST = "EAE005";
        public static final String EMAIL_OR_PHONE_NUMBER_CANNOT_BE_EMPTY = "PCF002";
        public static final String USER_NOT_FOUND = "NFE001";
        @SerializedName("code")
        public String code;
        @SerializedName("desc")
        public String desc;
        @SerializedName("stackTrace")
        public String stackTrace;
        @SerializedName("type")
        public String type;
    }

    public static class InitiateEnableAccountDataModel implements Parcelable {
        public static final Parcelable.Creator<InitiateEnableAccountDataModel> CREATOR = new Parcelable.Creator<InitiateEnableAccountDataModel>() {
            public InitiateEnableAccountDataModel createFromParcel(Parcel parcel) {
                return new InitiateEnableAccountDataModel(parcel);
            }

            public InitiateEnableAccountDataModel[] newArray(int i) {
                return new InitiateEnableAccountDataModel[i];
            }
        };
        @SerializedName("email")
        public String email;
        @SerializedName("mobileNumber")
        public String mobileNumber;
        public boolean requestOtpToEmail;

        public int describeContents() {
            return 0;
        }

        public InitiateEnableAccountDataModel() {
        }

        protected InitiateEnableAccountDataModel(Parcel parcel) {
            this.email = parcel.readString();
            this.mobileNumber = parcel.readString();
            this.requestOtpToEmail = parcel.readByte() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.email);
            parcel.writeString(this.mobileNumber);
            parcel.writeByte(this.requestOtpToEmail ? (byte) 1 : 0);
        }
    }

    public static class EnableAccountSuccessResponse extends GenericSuccessResponse implements Parcelable {
        public static final Parcelable.Creator<EnableAccountSuccessResponse> CREATOR = new Parcelable.Creator<EnableAccountSuccessResponse>() {
            public EnableAccountSuccessResponse createFromParcel(Parcel parcel) {
                return new EnableAccountSuccessResponse(parcel);
            }

            public EnableAccountSuccessResponse[] newArray(int i) {
                return new EnableAccountSuccessResponse[i];
            }
        };
        @SerializedName("errorState")
        public String errorState;
        @SerializedName("newUser")
        public boolean newUser;
        public String refreshToken = "";
        @SerializedName("token")
        public String token;

        public int describeContents() {
            return 0;
        }

        public EnableAccountSuccessResponse() {
        }

        protected EnableAccountSuccessResponse(Parcel parcel) {
            this.token = parcel.readString();
            this.newUser = parcel.readByte() != 0;
            this.refreshToken = parcel.readString();
            this.errorState = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.token);
            parcel.writeByte(this.newUser ? (byte) 1 : 0);
            parcel.writeString(this.refreshToken);
            parcel.writeString(this.errorState);
        }
    }
}
