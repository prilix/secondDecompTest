package com.jch.racWiFi.iduManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.GenericResponse;
import com.jch.racWiFi.GenericSuccessResponse;

public class WeeklyTimerModels {

    public static class WeeklyTimerAddResponse extends GenericSuccessResponse {
        public static final int WEEKLY_TIMER_CONFLICT = 409;
        public static final int WEEKLY_TIMER_MAX_SCHEDULE = 406;
        public boolean success = false;
    }

    public static class WeeklyTimerFailureResponse extends GenericErrorResponse {
    }

    public static class WeeklyTimerFetchResponse extends GenericResponse<WeeklyTimerData[]> {
    }

    public static class WeeklyTimerRemoveResponse extends GenericResponse {
        public boolean success;
    }

    public static class WeeklyTimerRequestData {
        public boolean allDay;
        public boolean enabled;
        public int humidity;

        /* renamed from: id */
        public int f468id;
        public String mode;
        public boolean[] selectedDays;
        public String switchOffAfter;
        public String switchOnAfter;
        public double temperature;
        public double timeZone;
    }

    public static class WeeklyTimerSuccessResponse extends GenericSuccessResponse {
    }

    public static class WeeklyTimerUpdateResponse extends GenericSuccessResponse {
        public static final int WEEKLY_TIMER_CONFLICT = 409;
        public static final int WEEKLY_TIMER_MAX_SCHEDULE = 406;
        public boolean success = false;
    }

    public static class WeeklyTimerData implements Parcelable {
        public static final Parcelable.Creator<WeeklyTimerData> CREATOR = new Parcelable.Creator<WeeklyTimerData>() {
            public WeeklyTimerData createFromParcel(Parcel parcel) {
                return new WeeklyTimerData(parcel);
            }

            public WeeklyTimerData[] newArray(int i) {
                return new WeeklyTimerData[i];
            }
        };
        public static final String PARCEL_KEY = "WeeklyTimerData";
        public boolean allDays;
        public boolean enabled;
        public String endsAt;
        public int humidity;
        @SerializedName("id")

        /* renamed from: id */
        public int f467id;
        public String mode = "COOLING";
        public boolean[] selectedDays;
        public String startsAt;
        public double temperature;

        public int describeContents() {
            return 0;
        }

        public WeeklyTimerData() {
        }

        protected WeeklyTimerData(Parcel parcel) {
            this.f467id = parcel.readInt();
            boolean z = true;
            this.allDays = parcel.readByte() != 0;
            this.enabled = parcel.readByte() == 0 ? false : z;
            this.humidity = parcel.readInt();
            this.mode = parcel.readString();
            this.selectedDays = parcel.createBooleanArray();
            this.endsAt = parcel.readString();
            this.startsAt = parcel.readString();
            this.temperature = parcel.readDouble();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f467id);
            parcel.writeByte(this.allDays ? (byte) 1 : 0);
            parcel.writeByte(this.enabled ? (byte) 1 : 0);
            parcel.writeInt(this.humidity);
            parcel.writeString(this.mode);
            parcel.writeBooleanArray(this.selectedDays);
            parcel.writeString(this.endsAt);
            parcel.writeString(this.startsAt);
            parcel.writeDouble(this.temperature);
        }

        public void copy(WeeklyTimerData weeklyTimerData) {
            this.f467id = weeklyTimerData.f467id;
            this.allDays = weeklyTimerData.allDays;
            this.enabled = weeklyTimerData.enabled;
            this.humidity = weeklyTimerData.humidity;
            this.mode = weeklyTimerData.mode;
            this.selectedDays = weeklyTimerData.selectedDays;
            this.endsAt = weeklyTimerData.endsAt;
            this.startsAt = weeklyTimerData.startsAt;
            this.temperature = weeklyTimerData.temperature;
        }
    }
}
