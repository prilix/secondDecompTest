package com.jch.racWiFi.selectMultipleContacts.RxContacts;

import android.os.Parcel;
import android.os.Parcelable;

public class PhoneNumber implements Parcelable {
    public static final Parcelable.Creator<PhoneNumber> CREATOR = new Parcelable.Creator<PhoneNumber>() {
        public PhoneNumber createFromParcel(Parcel parcel) {
            return new PhoneNumber(parcel);
        }

        public PhoneNumber[] newArray(int i) {
            return new PhoneNumber[i];
        }
    };
    private String number;
    private String typeLabel;

    public int describeContents() {
        return 0;
    }

    PhoneNumber(String str, String str2) {
        this.typeLabel = str;
        this.number = str2;
    }

    private PhoneNumber(Parcel parcel) {
        this.typeLabel = parcel.readString();
        this.number = parcel.readString();
    }

    public String getTypeLabel() {
        return this.typeLabel;
    }

    public void setTypeLabel(String str) {
        this.typeLabel = str;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.typeLabel);
        parcel.writeString(this.number);
    }
}
