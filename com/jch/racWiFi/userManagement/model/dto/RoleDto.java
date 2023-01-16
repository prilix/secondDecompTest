package com.jch.racWiFi.userManagement.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class RoleDto implements Parcelable {
    public static final int CHILD = 3;
    public static final Parcelable.Creator<RoleDto> CREATOR = new Parcelable.Creator<RoleDto>() {
        public RoleDto createFromParcel(Parcel parcel) {
            return new RoleDto(parcel);
        }

        public RoleDto[] newArray(int i) {
            return new RoleDto[i];
        }
    };
    public static final int MEMBER = 2;
    public static final int OWNER = 1;

    /* renamed from: id */
    private Integer f485id;
    private int level;
    private String name;

    public int describeContents() {
        return 0;
    }

    public void copy(RoleDto roleDto) {
        this.f485id = roleDto.f485id;
        this.name = roleDto.name;
        this.level = roleDto.level;
    }

    public RoleDto() {
    }

    protected RoleDto(Parcel parcel) {
        if (parcel.readByte() == 0) {
            this.f485id = null;
        } else {
            this.f485id = Integer.valueOf(parcel.readInt());
        }
        this.name = parcel.readString();
        this.level = parcel.readInt();
    }

    public Integer getId() {
        return this.f485id;
    }

    public void setId(Integer num) {
        this.f485id = num;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.f485id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(this.f485id.intValue());
        }
        parcel.writeString(this.name);
        parcel.writeInt(this.level);
    }
}
