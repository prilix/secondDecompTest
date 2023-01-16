package com.jch.racWiFi.iduManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.GenericResponse;
import com.jch.racWiFi.GenericSuccessResponse;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.iduManagement.Weekday;
import java.util.ArrayList;
import java.util.Collections;

public class WeeklyTimerModelV2 {

    public static class WeeklyTimerAddResponse extends GenericResponse {
        public static final int WEEKLY_TIMER_CONFLICT = 409;
        public static final int WEEKLY_TIMER_MAX_SCHEDULE = 406;
        public boolean success = false;
    }

    public static class WeeklyTimerFailureResponse extends GenericErrorResponse {
    }

    public static class WeeklyTimerFetchResponse extends GenericResponse<WeeklyTimerResponseData[]> {
    }

    public static class WeeklyTimerRemoveResponse extends GenericResponse {
        public boolean success;
    }

    public static class WeeklyTimerSuccessResponse extends GenericSuccessResponse {
    }

    public static class WeeklyTimerUpdateResponse extends GenericResponse {
        public static final int WEEKLY_TIMER_CONFLICT = 409;
        public static final int WEEKLY_TIMER_MAX_SCHEDULE = 406;
        public boolean success = false;
    }

    public static class AddRequestData {
        public String day;
        @SerializedName("racId")

        /* renamed from: id */
        public long f463id;
        public String mode;
        public String power;
        @SerializedName("startsAt")
        public String startAt;
        public double temperature;

        public void copy(Integer num, String str, String str2, String str3, double d, String str4) {
            this.f463id = (long) num.intValue();
            this.day = str;
            this.mode = str2;
            this.startAt = str3;
            this.temperature = d;
            this.power = str4;
        }
    }

    public static class UpdateRequestData {
        public String day;
        @SerializedName("racId")

        /* renamed from: id */
        public long f464id;
        public String mode;
        public String power;
        @SerializedName("id")
        public long scheduleId;
        @SerializedName("startsAt")
        public String startAt;
        public double temperature;

        public void copy(Integer num, String str, String str2, String str3, float f, String str4, long j) {
            this.f464id = (long) num.intValue();
            this.day = str;
            this.mode = str2;
            this.startAt = str3;
            this.temperature = (double) f;
            this.power = str4;
            this.scheduleId = j;
        }
    }

    public static class WeeklyTimerResponseData implements Parcelable, Comparable<WeeklyTimerResponseData> {
        public static final Parcelable.Creator<WeeklyTimerResponseData> CREATOR = new Parcelable.Creator<WeeklyTimerResponseData>() {
            public WeeklyTimerResponseData createFromParcel(Parcel parcel) {
                return new WeeklyTimerResponseData(parcel);
            }

            public WeeklyTimerResponseData[] newArray(int i) {
                return new WeeklyTimerResponseData[i];
            }
        };
        public String day;
        @SerializedName("racId")

        /* renamed from: id */
        public long f466id;
        public String mode = "COOLING";
        public String power;
        @SerializedName("id")
        public long scheduleId;
        @SerializedName("startsAt")
        public String startAt;
        public double temperature;

        public int describeContents() {
            return 0;
        }

        public int compareTo(WeeklyTimerResponseData weeklyTimerResponseData) {
            int position = Weekday.getPosition(this.day);
            int position2 = Weekday.getPosition(weeklyTimerResponseData.day);
            if (position < position2) {
                return -1;
            }
            if (position > position2) {
                return 1;
            }
            return DateAndTimeUtils.checktimings(this.startAt, weeklyTimerResponseData.startAt);
        }

        public WeeklyTimerResponseData() {
        }

        protected WeeklyTimerResponseData(Parcel parcel) {
            this.f466id = parcel.readLong();
            this.scheduleId = parcel.readLong();
            this.day = parcel.readString();
            this.startAt = parcel.readString();
            this.mode = parcel.readString();
            this.temperature = parcel.readDouble();
            this.power = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.f466id);
            parcel.writeLong(this.scheduleId);
            parcel.writeString(this.day);
            parcel.writeString(this.startAt);
            parcel.writeString(this.mode);
            parcel.writeDouble(this.temperature);
            parcel.writeString(this.power);
        }
    }

    public static class WeeklyTimerFactoryData extends WeeklyTimerData implements Parcelable, Comparable<WeeklyTimerFactoryData> {
        public static final Parcelable.Creator<WeeklyTimerFactoryData> CREATOR = new Parcelable.Creator<WeeklyTimerFactoryData>() {
            public WeeklyTimerFactoryData createFromParcel(Parcel parcel) {
                return new WeeklyTimerFactoryData(parcel);
            }

            public WeeklyTimerFactoryData[] newArray(int i) {
                return new WeeklyTimerFactoryData[i];
            }
        };
        public static String PARCEL_KEY = "weekly_timer_data";
        public String day;
        public String endAt;
        public String endingDay = "";

        /* renamed from: id */
        public long f465id;
        public String mode = "COOLING";
        public String power;
        public long scheduleId;
        public String startAt;
        public String startingDay = "";
        public double temperature;

        public int describeContents() {
            return 0;
        }

        public WeeklyTimerFactoryData() {
        }

        public int compareTo(WeeklyTimerFactoryData weeklyTimerFactoryData) {
            int position = Weekday.getPosition(this.day);
            int position2 = Weekday.getPosition(weeklyTimerFactoryData.day);
            if (position < position2) {
                return -1;
            }
            if (position > position2) {
                return 1;
            }
            return DateAndTimeUtils.checktimings(this.startAt, weeklyTimerFactoryData.startAt);
        }

        public void copy(WeeklyTimerResponseData weeklyTimerResponseData) {
            this.f465id = weeklyTimerResponseData.f466id;
            this.scheduleId = weeklyTimerResponseData.scheduleId;
            this.day = weeklyTimerResponseData.day;
            this.startAt = weeklyTimerResponseData.startAt;
            this.mode = weeklyTimerResponseData.mode;
            this.temperature = weeklyTimerResponseData.temperature;
            this.power = weeklyTimerResponseData.power;
        }

        public void copy(WeeklyTimerFactoryData weeklyTimerFactoryData) {
            this.f465id = weeklyTimerFactoryData.f465id;
            this.scheduleId = weeklyTimerFactoryData.scheduleId;
            this.day = weeklyTimerFactoryData.day;
            this.startAt = weeklyTimerFactoryData.startAt;
            this.mode = weeklyTimerFactoryData.mode;
            this.temperature = weeklyTimerFactoryData.temperature;
            this.power = weeklyTimerFactoryData.power;
            this.endAt = weeklyTimerFactoryData.endAt;
            this.startingDay = weeklyTimerFactoryData.startingDay;
            this.endingDay = weeklyTimerFactoryData.endingDay;
        }

        protected WeeklyTimerFactoryData(Parcel parcel) {
            this.f465id = parcel.readLong();
            this.scheduleId = parcel.readLong();
            this.day = parcel.readString();
            this.startAt = parcel.readString();
            this.endAt = parcel.readString();
            this.mode = parcel.readString();
            this.temperature = parcel.readDouble();
            this.power = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.f465id);
            parcel.writeLong(this.scheduleId);
            parcel.writeString(this.day);
            parcel.writeString(this.startAt);
            parcel.writeString(this.endAt);
            parcel.writeString(this.mode);
            parcel.writeDouble(this.temperature);
            parcel.writeString(this.power);
        }
    }

    public static class Data {
        public static Data data;

        public static Data getInstance() {
            if (data == null) {
                data = new Data();
            }
            return data;
        }
    }

    public static class TimerHolderData implements Cloneable {
        public static final String TIMER_MODEL = "TIMER_MODEL";
        public int day = -1;
        public boolean enabled;
        public int endHour = 0;
        public int endMinute = 0;
        public int startHour = 0;
        public int startMinute = 0;

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static class MadeFactoryData {
        private MutableLiveData<ArrayList<WeeklyTimerFactoryData>> mutableLiveData;
        private WeeklyTimerResponseData[] response;

        public MadeFactoryData(WeeklyTimerResponseData[] weeklyTimerResponseDataArr, MutableLiveData<ArrayList<WeeklyTimerFactoryData>> mutableLiveData2) {
            this.response = weeklyTimerResponseDataArr;
            this.mutableLiveData = mutableLiveData2;
        }

        public void generateNewDataSet() {
            ArrayList arrayList = new ArrayList();
            WeeklyTimerResponseData[] weeklyTimerResponseDataArr = this.response;
            if (weeklyTimerResponseDataArr == null) {
                this.mutableLiveData.setValue(arrayList);
                return;
            }
            for (WeeklyTimerResponseData copy : weeklyTimerResponseDataArr) {
                WeeklyTimerFactoryData weeklyTimerFactoryData = new WeeklyTimerFactoryData();
                weeklyTimerFactoryData.copy(copy);
                arrayList.add(weeklyTimerFactoryData);
            }
            Collections.sort(arrayList);
            dataMadeFromFactory(arrayList);
        }

        private void dataMadeFromFactory(ArrayList<WeeklyTimerFactoryData> arrayList) {
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList(arrayList);
            if (arrayList3.size() == 1) {
                WeeklyTimerFactoryData weeklyTimerFactoryData = (WeeklyTimerFactoryData) arrayList3.get(arrayList3.size() - 1);
                weeklyTimerFactoryData.endAt = "00:00";
                weeklyTimerFactoryData.startingDay = weeklyTimerFactoryData.day;
                for (int i = 0; i < 7; i++) {
                    WeeklyTimerFactoryData weeklyTimerFactoryData2 = new WeeklyTimerFactoryData();
                    weeklyTimerFactoryData.day = Weekday.getDay(i);
                    weeklyTimerFactoryData2.copy(weeklyTimerFactoryData);
                    arrayList2.add(weeklyTimerFactoryData2);
                }
                this.mutableLiveData.setValue(arrayList2);
                return;
            }
            for (int i2 = 1; i2 < arrayList3.size(); i2++) {
                WeeklyTimerFactoryData weeklyTimerFactoryData3 = new WeeklyTimerFactoryData();
                weeklyTimerFactoryData3.copy((WeeklyTimerFactoryData) arrayList3.get(i2 - 1));
                WeeklyTimerFactoryData weeklyTimerFactoryData4 = new WeeklyTimerFactoryData();
                weeklyTimerFactoryData4.copy((WeeklyTimerFactoryData) arrayList3.get(i2));
                weeklyTimerFactoryData3.endAt = weeklyTimerFactoryData4.startAt;
                weeklyTimerFactoryData3.startingDay = weeklyTimerFactoryData3.day;
                weeklyTimerFactoryData3.endingDay = weeklyTimerFactoryData4.day;
                int position = Weekday.getPosition(weeklyTimerFactoryData3.day);
                int position2 = Weekday.getPosition(weeklyTimerFactoryData4.day);
                if (position == position2) {
                    weeklyTimerFactoryData3.day = weeklyTimerFactoryData4.day;
                    WeeklyTimerFactoryData weeklyTimerFactoryData5 = new WeeklyTimerFactoryData();
                    weeklyTimerFactoryData5.copy(weeklyTimerFactoryData3);
                    arrayList2.add(weeklyTimerFactoryData5);
                } else {
                    while (position <= position2) {
                        weeklyTimerFactoryData3.day = Weekday.getDay(position);
                        WeeklyTimerFactoryData weeklyTimerFactoryData6 = new WeeklyTimerFactoryData();
                        weeklyTimerFactoryData6.copy(weeklyTimerFactoryData3);
                        arrayList2.add(weeklyTimerFactoryData6);
                        position++;
                    }
                }
                if (i2 == arrayList3.size() - 1) {
                    WeeklyTimerFactoryData weeklyTimerFactoryData7 = (WeeklyTimerFactoryData) arrayList3.get(0);
                    WeeklyTimerFactoryData weeklyTimerFactoryData8 = (WeeklyTimerFactoryData) arrayList3.get(i2);
                    weeklyTimerFactoryData8.startingDay = weeklyTimerFactoryData8.day;
                    weeklyTimerFactoryData8.endAt = weeklyTimerFactoryData7.startAt;
                    weeklyTimerFactoryData8.endingDay = weeklyTimerFactoryData7.day;
                    int position3 = Weekday.getPosition(weeklyTimerFactoryData8.day);
                    int position4 = Weekday.getPosition(weeklyTimerFactoryData7.day);
                    if (position3 >= position4) {
                        int i3 = (position3 == 0 && position4 == 0) ? 6 : 7;
                        int i4 = position3;
                        while (i4 <= i3) {
                            if (i4 > Weekday.getPosition(Weekday.SAT.name())) {
                                i4 = Weekday.getPosition(Weekday.SUN.name());
                                if (position3 != position4) {
                                    i3 = Weekday.getPosition(weeklyTimerFactoryData7.day);
                                } else {
                                    i3 = Weekday.getPosition(weeklyTimerFactoryData7.day) - 1;
                                }
                            }
                            WeeklyTimerFactoryData weeklyTimerFactoryData9 = new WeeklyTimerFactoryData();
                            weeklyTimerFactoryData9.copy(weeklyTimerFactoryData8);
                            weeklyTimerFactoryData9.day = Weekday.getDay(i4);
                            arrayList2.add(weeklyTimerFactoryData9);
                            i4++;
                        }
                    }
                }
            }
            this.mutableLiveData.setValue(arrayList2);
        }
    }
}
