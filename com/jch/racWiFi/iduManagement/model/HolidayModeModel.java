package com.jch.racWiFi.iduManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HolidayModeModel implements Parcelable {
    public static final Parcelable.Creator<HolidayModeModel> CREATOR = new Parcelable.Creator<HolidayModeModel>() {
        public HolidayModeModel createFromParcel(Parcel parcel) {
            return new HolidayModeModel(parcel);
        }

        public HolidayModeModel[] newArray(int i) {
            return new HolidayModeModel[i];
        }
    };
    public boolean isHolidayModeSupport;
    public boolean isOnLine;
    public boolean isSelected;
    public String name;
    public String racCloudId;
    public int racId;
    public int racTypeId;

    public static class HolidayModeData {
        public boolean enabled;
        public boolean holidayModeExists;
        public int holidayModeId;
        public String name;
        public int racId;
        public String switchOffAfter;
        public String switchOnAfter;
        public double temperature;
        public String thingId;
        public String vendorThingId;
    }

    public static class HolidayModeFailureResponce extends GenericResponse {
    }

    public static class HolidayModeFetchResponse extends GenericResponse<HolidayModeResponseData[]> {
    }

    public static class HolidayModeInterruptResponse extends GenericResponse {
    }

    public static class HolidayModeRequestData {
        public boolean enabled;
        public int humidity;
        public int[] iduList;
        public String mode;
        public String switchOffAfter;
        public String switchOnAfter;
        public float temperature;
    }

    public static class HolidayModeRequestDataV2 {
        @SerializedName("endsAt")
        public String endDate;
        @SerializedName("id")
        public int holidayModeId;
        public Map<Integer, Boolean> iduList = new HashMap();
        public String scheduleType;
        public float temperature;
    }

    public static class HolidayModeUpdateResponse extends GenericResponse<HolidayModeUpdateResponseData[]> {
    }

    public static class HolidayModeUpdateResponseData {
        public String message;
        public int racId;
    }

    public static class HolidayModeUpdateResponseDataV2 extends GenericResponse<HolidayModeResponse> {
    }

    public int describeContents() {
        return 0;
    }

    public HolidayModeModel(DetailedIduModel detailedIduModel, boolean z) {
        this.racId = detailedIduModel.f454id.intValue();
        this.name = detailedIduModel.name;
        this.racTypeId = detailedIduModel.modelTypeId;
        this.racCloudId = detailedIduModel.cloudId;
        this.isHolidayModeSupport = z;
    }

    public HolidayModeModel() {
    }

    public HolidayModeModel(int i, String str, int i2, String str2, boolean z) {
        this.racId = i;
        this.name = str;
        this.racTypeId = i2;
        this.racCloudId = str2;
        this.isOnLine = z;
    }

    protected HolidayModeModel(Parcel parcel) {
        this.racId = parcel.readInt();
        this.name = parcel.readString();
        this.racTypeId = parcel.readInt();
        this.racCloudId = parcel.readString();
        this.isSelected = parcel.readByte() != 0;
    }

    public void copy(HolidayModeModel holidayModeModel) {
        this.racId = holidayModeModel.racId;
        this.name = holidayModeModel.name;
        this.isSelected = holidayModeModel.isSelected;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.racId);
        parcel.writeString(this.name);
        parcel.writeInt(this.racTypeId);
        parcel.writeString(this.racCloudId);
        parcel.writeByte(this.isSelected ? (byte) 1 : 0);
    }

    public static class HolidayModeDataV2 extends BaseObservable implements Cloneable {
        public boolean enabled;
        public String endDate = "dd/mm/yyyy";
        public int[] iduList;
        public boolean isSelected;
        public String mode;
        public float temperature;

        @Bindable
        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean z) {
            this.enabled = z;
            notifyPropertyChanged(1);
        }

        @Bindable
        public int[] getIduList() {
            return this.iduList;
        }

        public void setIduList(int[] iArr) {
            this.iduList = iArr;
            notifyPropertyChanged(4);
        }

        @Bindable
        public String getMode() {
            return this.mode;
        }

        public void setMode(String str) {
            this.mode = str;
            notifyPropertyChanged(5);
        }

        @Bindable
        public float getTemperature() {
            return this.temperature;
        }

        public void setTemperature(float f) {
            this.temperature = f;
            notifyPropertyChanged(7);
        }

        @Bindable
        public String getEndDate() {
            return this.endDate;
        }

        public void setEndDate(String str) {
            this.endDate = str;
            notifyPropertyChanged(2);
        }

        @Bindable
        public boolean isSelected() {
            return this.isSelected;
        }

        public void setSelected(boolean z) {
            this.isSelected = z;
            notifyPropertyChanged(6);
        }

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static class InterruptHolidayMode {
        public ArrayList<Integer> iduList;
        public boolean interrupted;
        public String scheduleTypes;

        public InterruptHolidayMode(int i) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            this.iduList = arrayList;
            this.interrupted = true;
            this.scheduleTypes = "SCHEDULE_DISABLED";
            arrayList.add(Integer.valueOf(i));
        }

        public InterruptHolidayMode(Map<Integer, Boolean> map) {
            this.iduList = new ArrayList<>();
            this.interrupted = true;
            this.scheduleTypes = "SCHEDULE_DISABLED";
            for (Map.Entry next : map.entrySet()) {
                if (((Boolean) next.getValue()).booleanValue()) {
                    this.iduList.add((Integer) next.getKey());
                }
            }
        }
    }

    public static class HolidayModeResponseData {
        public String endsAt;
        public int holidayModeId;
        public boolean isEnabled;
        public String mode;
        public int racId;
        public String scheduleTypes;
        public float temperature;

        public void copy(HolidayModeResponseData holidayModeResponseData) {
            this.racId = holidayModeResponseData.racId;
            this.holidayModeId = holidayModeResponseData.holidayModeId;
            this.scheduleTypes = holidayModeResponseData.scheduleTypes;
            this.endsAt = holidayModeResponseData.endsAt;
            this.temperature = holidayModeResponseData.temperature;
            this.mode = holidayModeResponseData.mode;
            this.isEnabled = true;
        }
    }

    public static class HolidayModeResponse implements Parcelable {
        public static final Parcelable.Creator<HolidayModeResponse> CREATOR = new Parcelable.Creator<HolidayModeResponse>() {
            public HolidayModeResponse createFromParcel(Parcel parcel) {
                return new HolidayModeResponse(parcel);
            }

            public HolidayModeResponse[] newArray(int i) {
                return new HolidayModeResponse[i];
            }
        };
        public String message;
        public Response[] result;

        public int describeContents() {
            return 0;
        }

        public HolidayModeResponse() {
        }

        public HolidayModeResponse(Parcel parcel) {
            this.message = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.message);
        }
    }

    public static class Response implements Parcelable {
        public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
            public Response createFromParcel(Parcel parcel) {
                return new Response(parcel);
            }

            public Response[] newArray(int i) {
                return new Response[i];
            }
        };
        public String message;
        public int racId;

        public int describeContents() {
            return 0;
        }

        public Response() {
        }

        protected Response(Parcel parcel) {
            this.racId = parcel.readInt();
            this.message = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.racId);
            parcel.writeString(this.message);
        }
    }
}
