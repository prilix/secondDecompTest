package com.jch.racWiFi.userManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordDataModels {
    public static final int INVALID_OTP = 400;
    public static final int USER_NOT_FOUND = 404;

    public static class ForgotPasswordOTPRequestData implements Parcelable {
        public static final Parcelable.Creator<ForgotPasswordOTPRequestData> CREATOR = new Parcelable.Creator<ForgotPasswordOTPRequestData>() {
            public ForgotPasswordOTPRequestData createFromParcel(Parcel parcel) {
                return new ForgotPasswordOTPRequestData(parcel);
            }

            public ForgotPasswordOTPRequestData[] newArray(int i) {
                return new ForgotPasswordOTPRequestData[i];
            }
        };
        public static final String FORGOT_PASSWORD_REQUEST_OTP_KEY = "FORGOT_PASSWORD_REQUEST_OTP_KEY";
        @SerializedName("email")
        public String emailID;
        @SerializedName("mobileNumber")
        public String mobileNumber;
        public boolean requestOtpToEmail;

        public int describeContents() {
            return 0;
        }

        public ForgotPasswordOTPRequestData() {
        }

        public ForgotPasswordOTPRequestData(Parcel parcel) {
            this.emailID = parcel.readString();
            this.mobileNumber = parcel.readString();
            this.requestOtpToEmail = parcel.readByte() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.emailID);
            parcel.writeString(this.mobileNumber);
            parcel.writeByte(this.requestOtpToEmail ? (byte) 1 : 0);
        }
    }

    public static class ForgotPasswordVerifyOTPRequestData implements Parcelable {
        public static final Parcelable.Creator<ForgotPasswordVerifyOTPRequestData> CREATOR = new Parcelable.Creator<ForgotPasswordVerifyOTPRequestData>() {
            public ForgotPasswordVerifyOTPRequestData createFromParcel(Parcel parcel) {
                return new ForgotPasswordVerifyOTPRequestData(parcel);
            }

            public ForgotPasswordVerifyOTPRequestData[] newArray(int i) {
                return new ForgotPasswordVerifyOTPRequestData[i];
            }
        };
        public static final String FORGOT_PASSWORD_TOKEN = "FORGOT_PASSWORD_TOKEN";
        public static final String FORGOT_PASSWORD_VERIFY_OTP_KEY = "FORGOT_PASSWORD_VERIFY_OTP_KEY";
        @SerializedName("otp")
        public int OTP;
        @SerializedName("email")
        public String email;
        @SerializedName("mobileNumber")
        public String mobileNumber;

        public int describeContents() {
            return 0;
        }

        public ForgotPasswordVerifyOTPRequestData() {
        }

        public ForgotPasswordVerifyOTPRequestData(Parcel parcel) {
            this.email = parcel.readString();
            this.mobileNumber = parcel.readString();
            this.OTP = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.email);
            parcel.writeString(this.mobileNumber);
            parcel.writeInt(this.OTP);
        }
    }

    public static class ForgotPasswordUpdateRequestData implements Parcelable {
        public static final Parcelable.Creator<ForgotPasswordUpdateRequestData> CREATOR = new Parcelable.Creator<ForgotPasswordUpdateRequestData>() {
            public ForgotPasswordUpdateRequestData createFromParcel(Parcel parcel) {
                return new ForgotPasswordUpdateRequestData(parcel);
            }

            public ForgotPasswordUpdateRequestData[] newArray(int i) {
                return new ForgotPasswordUpdateRequestData[i];
            }
        };
        public static final String FORGOT_PASSWORD_UPDATE_OTP_KEY = "FORGOT_PASSWORD_UPDATE_OTP_KEY";
        @SerializedName("email")
        public String email;
        @SerializedName("mobileNumber")
        public String mobileNumber;
        @SerializedName("password")
        public String newPassword;
        @SerializedName("token")
        public String token;

        public int describeContents() {
            return 0;
        }

        public ForgotPasswordUpdateRequestData() {
        }

        public ForgotPasswordUpdateRequestData(Parcel parcel) {
            this.email = parcel.readString();
            this.newPassword = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.email);
            parcel.writeString(this.newPassword);
        }
    }
}
