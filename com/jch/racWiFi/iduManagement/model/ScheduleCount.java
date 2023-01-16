package com.jch.racWiFi.iduManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.jch.racWiFi.GenericResponse;

public class ScheduleCount implements Parcelable {
    public static final Parcelable.Creator<ScheduleCount> CREATOR = new Parcelable.Creator<ScheduleCount>() {
        public ScheduleCount createFromParcel(Parcel parcel) {
            return new ScheduleCount(parcel);
        }

        public ScheduleCount[] newArray(int i) {
            return new ScheduleCount[i];
        }
    };
    public int count;
    public long racId;

    public static class ScheduleCountResponse extends GenericResponse<ScheduleCount[]> {
    }

    public int describeContents() {
        return 0;
    }

    public ScheduleCount() {
    }

    public ScheduleCount(String str, String str2) {
        this.racId = Long.parseLong(str);
        this.count = Integer.parseInt(str2);
    }

    public ScheduleCount(long j, int i) {
        this.racId = j;
        this.count = i;
    }

    protected ScheduleCount(Parcel parcel) {
        this.racId = parcel.readLong();
        this.count = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.racId);
        parcel.writeInt(this.count);
    }
}
