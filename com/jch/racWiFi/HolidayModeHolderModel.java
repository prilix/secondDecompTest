package com.jch.racWiFi;

import android.os.Parcel;
import android.os.Parcelable;

public class HolidayModeHolderModel implements Parcelable {
    public static final Parcelable.Creator<HolidayModeHolderModel> CREATOR = new Parcelable.Creator<HolidayModeHolderModel>() {
        public HolidayModeHolderModel createFromParcel(Parcel parcel) {
            return new HolidayModeHolderModel(parcel);
        }

        public HolidayModeHolderModel[] newArray(int i) {
            return new HolidayModeHolderModel[i];
        }
    };
    public static final String HOLIDAY_HOLDER_MODEL = "TIMER_DATE_MODEL";
    public int endDate;
    public int endHour;
    public int endMinute;
    public int endMonth;
    public int endYear;
    public int startDate;
    public int startHour;
    public int startMinute;
    public int startMonth;
    public int startYear;
    public double temperature = 25.0d;

    public int describeContents() {
        return 0;
    }

    public HolidayModeHolderModel() {
    }

    public void copy(HolidayModeHolderModel holidayModeHolderModel) {
        this.startHour = holidayModeHolderModel.startHour;
        this.startMinute = holidayModeHolderModel.startMinute;
        this.endHour = holidayModeHolderModel.endHour;
        this.endMinute = holidayModeHolderModel.endMinute;
        this.startDate = holidayModeHolderModel.startDate;
        this.startMonth = holidayModeHolderModel.startMonth;
        this.startYear = holidayModeHolderModel.startYear;
        this.endDate = holidayModeHolderModel.endDate;
        this.endMonth = holidayModeHolderModel.endMonth;
        this.endYear = holidayModeHolderModel.endYear;
    }

    protected HolidayModeHolderModel(Parcel parcel) {
        this.startHour = parcel.readInt();
        this.startMinute = parcel.readInt();
        this.endHour = parcel.readInt();
        this.endMinute = parcel.readInt();
        this.startDate = parcel.readInt();
        this.startMonth = parcel.readInt();
        this.startYear = parcel.readInt();
        this.endDate = parcel.readInt();
        this.endMonth = parcel.readInt();
        this.endYear = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.startHour);
        parcel.writeInt(this.startMinute);
        parcel.writeInt(this.endHour);
        parcel.writeInt(this.endMinute);
        parcel.writeInt(this.startDate);
        parcel.writeInt(this.startMonth);
        parcel.writeInt(this.startYear);
        parcel.writeInt(this.endDate);
        parcel.writeInt(this.endMonth);
        parcel.writeInt(this.endYear);
    }
}
