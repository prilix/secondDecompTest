package com.jch.racWiFi.fcm.util;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class DeepLink implements Parcelable {
    public static final Parcelable.Creator<DeepLink> CREATOR = new Parcelable.Creator<DeepLink>() {
        public DeepLink createFromParcel(Parcel parcel) {
            return new DeepLink(parcel);
        }

        public DeepLink[] newArray(int i) {
            return new DeepLink[i];
        }
    };
    public static final String PARCEL_KEY = "DeepLink_PARCEL_KEY";
    private final List<Integer> destinationList;
    private Long racId;
    private String racName;
    private Type type;

    public int describeContents() {
        return 0;
    }

    public DeepLink() {
        this.racId = -1L;
        this.destinationList = new ArrayList();
    }

    protected DeepLink(Parcel parcel) {
        this();
        Type type2 = null;
        if (parcel.readByte() == 0) {
            this.racId = null;
        } else {
            this.racId = Long.valueOf(parcel.readLong());
        }
        parcel.readList(this.destinationList, Integer.class.getClassLoader());
        int readInt = parcel.readInt();
        this.type = readInt != -1 ? Type.values()[readInt] : type2;
        this.racName = parcel.readString();
    }

    public Long getRacId() {
        return this.racId;
    }

    public void setRacId(Long l) {
        this.racId = l;
    }

    public List<Integer> getDestinationList() {
        return this.destinationList;
    }

    public void addDestination(Integer num) {
        this.destinationList.add(num);
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        this.type = type2;
    }

    public String getRacName() {
        return this.racName;
    }

    public void setRacName(String str) {
        this.racName = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.racId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.racId.longValue());
        }
        parcel.writeList(this.destinationList);
        Type type2 = this.type;
        parcel.writeInt(type2 == null ? -1 : type2.ordinal());
        parcel.writeString(this.racName);
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARCEL_KEY, this);
        return bundle;
    }
}
