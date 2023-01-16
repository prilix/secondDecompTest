package com.jch.racWiFi.userManagement.view.activate_user;

import android.os.Parcel;
import android.os.Parcelable;

public class ActivateAccountParcelData implements Parcelable {
    public static final Parcelable.Creator<ActivateAccountParcelData> CREATOR = new Parcelable.Creator<ActivateAccountParcelData>() {
        public ActivateAccountParcelData createFromParcel(Parcel parcel) {
            return new ActivateAccountParcelData(parcel);
        }

        public ActivateAccountParcelData[] newArray(int i) {
            return new ActivateAccountParcelData[i];
        }
    };
    private String emailId;
    private String password;
    private String phoneNumber;

    public int describeContents() {
        return 0;
    }

    public ActivateAccountParcelData() {
    }

    protected ActivateAccountParcelData(Parcel parcel) {
        this.phoneNumber = parcel.readString();
        this.emailId = parcel.readString();
        this.password = parcel.readString();
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String str) {
        this.emailId = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.phoneNumber);
        parcel.writeString(this.emailId);
        parcel.writeString(this.password);
    }
}
