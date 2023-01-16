package com.jch.racWiFi.fcm.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.fcm.util.ErrorSubCategory;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.p010di.util.Constants;

public class Error implements Parcelable {
    public static final Parcelable.Creator<Error> CREATOR = new Parcelable.Creator<Error>() {
        public Error createFromParcel(Parcel parcel) {
            return new Error(parcel);
        }

        public Error[] newArray(int i) {
            return new Error[i];
        }
    };
    public static final String PARCEL_KEY = "Error_PARCEL_KEY";
    private Bundle bundle;
    @SerializedName("errorCode")
    private String errorCode;
    @SerializedName("racErrorDesc")
    private String errorDesc;
    @SerializedName("errorType")
    private ErrorSubCategory errorSubCategory = ErrorSubCategory.INDOOR;
    @SerializedName("familyId")
    private String familyId;
    @SerializedName("id")

    /* renamed from: id */
    private String f441id;
    @SerializedName("racName")
    private String name;
    @SerializedName("racId")
    private long racId;
    private ErrorSubCategory subCategory;
    @SerializedName("subCategory")
    private String subCategory1;
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("racErrorTitle")
    private String title;
    private Type type = Type.ERRORS;
    @SerializedName("vendorThingId")
    private String vendorThingId;

    public int describeContents() {
        return 0;
    }

    public Error() {
    }

    protected Error(Parcel parcel) {
        Type type2;
        this.f441id = parcel.readString();
        this.title = parcel.readString();
        this.name = parcel.readString();
        this.errorDesc = parcel.readString();
        this.errorCode = parcel.readString();
        this.racId = parcel.readLong();
        this.timestamp = parcel.readLong();
        this.bundle = parcel.readBundle();
        int readInt = parcel.readInt();
        ErrorSubCategory errorSubCategory2 = null;
        if (readInt == -1) {
            type2 = null;
        } else {
            type2 = Type.values()[readInt];
        }
        this.type = type2;
        int readInt2 = parcel.readInt();
        this.subCategory = readInt2 != -1 ? ErrorSubCategory.values()[readInt2] : errorSubCategory2;
        this.familyId = parcel.readString();
        this.vendorThingId = parcel.readString();
        this.subCategory1 = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f441id);
        parcel.writeString(this.title);
        parcel.writeString(this.name);
        parcel.writeString(this.errorDesc);
        parcel.writeString(this.errorCode);
        parcel.writeLong(this.racId);
        parcel.writeLong(this.timestamp);
        parcel.writeBundle(this.bundle);
        Type type2 = this.type;
        int i2 = -1;
        parcel.writeInt(type2 == null ? -1 : type2.ordinal());
        ErrorSubCategory errorSubCategory2 = this.subCategory;
        if (errorSubCategory2 != null) {
            i2 = errorSubCategory2.ordinal();
        }
        parcel.writeInt(i2);
        parcel.writeString(this.familyId);
        parcel.writeString(this.vendorThingId);
        parcel.writeString(this.subCategory1);
    }

    public Type getType() {
        return this.type;
    }

    public String getPushTitle(Context context) {
        return Constants.CC.getResource(context).getString(getErrorSubCategory().getAttributes()[0]);
    }

    public String getBellTitle(Context context) {
        return Constants.CC.getResource(context).getString(getErrorSubCategory().getAttributes()[1], new Object[]{getName(), getErrorCode(), context.getString(getErrorSubCategory().getAttributes()[3])});
    }

    public String getPushDescription(Context context) {
        String string = Constants.CC.getResource(context).getString(getErrorSubCategory().getAttributes()[1], new Object[]{getName(), getErrorCode(), context.getString(getErrorSubCategory().getAttributes()[3])});
        Resources resource = Constants.CC.getResource(context);
        int i = getErrorSubCategory().getAttributes()[2];
        return resource.getString(i, new Object[]{string + " - ", context.getString(getErrorSubCategory().getAttributes()[3])});
    }

    public String getBellDescription(Context context) {
        return Constants.CC.getResource(context).getString(getErrorSubCategory().getAttributes()[2], new Object[]{"", context.getString(getErrorSubCategory().getAttributes()[3])});
    }

    public void setType(Type type2) {
        this.type = type2;
    }

    public String getId() {
        return this.f441id;
    }

    public void setId(String str) {
        this.f441id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public void setErrorDesc(String str) {
        this.errorDesc = str;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public ErrorSubCategory getErrorSubCategory() {
        return this.errorSubCategory;
    }

    public void setErrorSubCategory(ErrorSubCategory errorSubCategory2) {
        this.errorSubCategory = errorSubCategory2;
    }

    public Long getRacId() {
        return Long.valueOf(this.racId);
    }

    public void setRacId(Long l) {
        this.racId = l.longValue();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public Bundle getBundle() {
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(PARCEL_KEY, this);
        return bundle2;
    }

    public static Error getInstance(Bundle bundle2) {
        return (Error) bundle2.getParcelable(PARCEL_KEY);
    }

    public void setBundle(Bundle bundle2) {
        this.bundle = bundle2;
    }

    public Bundle getBundle1() {
        return this.bundle;
    }

    public String getFamilyId() {
        return this.familyId;
    }

    public void setFamilyId(String str) {
        this.familyId = str;
    }

    public ErrorSubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(ErrorSubCategory errorSubCategory2) {
        this.subCategory = errorSubCategory2;
    }

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }

    public String getSubCategory1() {
        return this.subCategory1;
    }

    public void setSubCategory1(String str) {
        this.subCategory1 = str;
    }

    public void setRacId(long j) {
        this.racId = j;
    }
}
