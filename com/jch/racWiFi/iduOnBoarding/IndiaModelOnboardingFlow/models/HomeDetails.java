package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeDetails implements Parcelable {
    public static final Parcelable.Creator<HomeDetails> CREATOR = new Parcelable.Creator<HomeDetails>() {
        public HomeDetails createFromParcel(Parcel parcel) {
            return new HomeDetails(parcel);
        }

        public HomeDetails[] newArray(int i) {
            return new HomeDetails[i];
        }
    };
    private String creatorProfilePicUrl = "";
    private int familyId;
    private String familyName = "";
    private String vendorThingId = "";

    public int describeContents() {
        return 0;
    }

    public int getFamilyId() {
        return this.familyId;
    }

    public void setFamilyId(int i) {
        this.familyId = i;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String str) {
        this.familyName = str;
    }

    public String getCreatorProfilePicUrl() {
        return this.creatorProfilePicUrl;
    }

    public void setCreatorProfilePicUrl(String str) {
        this.creatorProfilePicUrl = str;
    }

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }

    protected HomeDetails(Parcel parcel) {
        this.familyId = parcel.readInt();
        this.familyName = parcel.readString();
        this.creatorProfilePicUrl = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.familyId);
        parcel.writeString(this.familyName);
        parcel.writeString(this.creatorProfilePicUrl);
        parcel.writeString(this.vendorThingId);
    }
}
