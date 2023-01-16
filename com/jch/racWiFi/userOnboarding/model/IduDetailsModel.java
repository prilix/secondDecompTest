package com.jch.racWiFi.userOnboarding.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import java.io.Serializable;
import java.util.Objects;

public class IduDetailsModel implements Serializable, Parcelable {
    public static final Parcelable.Creator<IduDetailsModel> CREATOR = new Parcelable.Creator<IduDetailsModel>() {
        public IduDetailsModel createFromParcel(Parcel parcel) {
            return new IduDetailsModel(parcel);
        }

        public IduDetailsModel[] newArray(int i) {
            return new IduDetailsModel[i];
        }
    };
    public static final String LIST_ARCEL_KEY = "list_of_idu";

    /* renamed from: id */
    private Long f487id;
    private String name;
    private String thingId;
    private String vendorThingId;

    public int describeContents() {
        return 0;
    }

    public IduDetailsModel() {
    }

    protected IduDetailsModel(Parcel parcel) {
        if (parcel.readByte() == 0) {
            this.f487id = null;
        } else {
            this.f487id = Long.valueOf(parcel.readLong());
        }
        this.thingId = parcel.readString();
        this.vendorThingId = parcel.readString();
        this.name = parcel.readString();
    }

    public Long getId() {
        return this.f487id;
    }

    public void setId(Long l) {
        this.f487id = l;
    }

    public String getThingId() {
        return this.thingId;
    }

    public void setThingId(String str) {
        this.thingId = str;
    }

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ((IduDetailsModel) obj).f487id.equals(this.f487id);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.f487id, this.thingId, this.vendorThingId, this.name});
    }

    public void copyFromDetailIduModel(DetailedIduModel detailedIduModel) {
        this.f487id = Long.valueOf((long) detailedIduModel.f454id.intValue());
        this.name = detailedIduModel.name;
        this.thingId = detailedIduModel.vendorThingId;
        this.vendorThingId = detailedIduModel.vendorThingId;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.f487id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.f487id.longValue());
        }
        parcel.writeString(this.thingId);
        parcel.writeString(this.vendorThingId);
        parcel.writeString(this.name);
    }
}
