package com.jch.racWiFi;

import android.os.Parcel;
import android.os.Parcelable;

public class TimerHolderModel implements Parcelable {
    public static final Parcelable.Creator<TimerHolderModel> CREATOR = new Parcelable.Creator<TimerHolderModel>() {
        public TimerHolderModel createFromParcel(Parcel parcel) {
            return new TimerHolderModel(parcel);
        }

        public TimerHolderModel[] newArray(int i) {
            return new TimerHolderModel[i];
        }
    };
    public static final String TIMER_MODEL = "TIMER_MODEL";
    public int endHour = 0;
    public int endMinute = 0;
    public String format;
    public int startHour = 0;
    public int startMinute = 0;
    public boolean switchOffAfter = true;
    public boolean switchOnAfter = true;

    public int describeContents() {
        return 0;
    }

    public TimerHolderModel() {
    }

    public void copy(TimerHolderModel timerHolderModel) {
        this.startHour = timerHolderModel.startHour;
        this.startMinute = timerHolderModel.startMinute;
        this.endHour = timerHolderModel.endHour;
        this.endMinute = timerHolderModel.endMinute;
        this.format = timerHolderModel.format;
    }

    protected TimerHolderModel(Parcel parcel) {
        this.startHour = parcel.readInt();
        this.startMinute = parcel.readInt();
        this.endHour = parcel.readInt();
        this.endMinute = parcel.readInt();
        this.format = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.startHour);
        parcel.writeInt(this.startMinute);
        parcel.writeInt(this.endHour);
        parcel.writeInt(this.endMinute);
        parcel.writeString(this.format);
    }
}
