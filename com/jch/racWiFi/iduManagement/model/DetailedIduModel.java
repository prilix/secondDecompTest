package com.jch.racWiFi.iduManagement.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.fcm.repository.ModelRepository;
import com.jch.racWiFi.fcm.util.AcActivitiesList;
import com.jch.racWiFi.fcm.util.AcActivitiesSubCategory;
import com.jch.racWiFi.iduManagement.WebSocketNotification;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class DetailedIduModel implements Parcelable, Comparable<DetailedIduModel> {
    public static final DetailedIduModel COOLING_DEFAULT;
    public static final Parcelable.Creator<DetailedIduModel> CREATOR = new Parcelable.Creator<DetailedIduModel>() {
        public DetailedIduModel createFromParcel(Parcel parcel) {
            return new DetailedIduModel(parcel);
        }

        public DetailedIduModel[] newArray(int i) {
            return new DetailedIduModel[i];
        }
    };
    public static final String IDU_LIST = "IDU_LIST";
    public static final long MILLISECONDS_22_HOURS = 79200000;
    public static final String OUT_SIDE_CITY = "OUT_SIDE_CITY";
    public static final String OUT_SIDE_TEMPERATURE = "OUT_SIDE_TEMPERATURE";
    public static final String OUT_SIDE_TEMPERATURE_UNIT = "OUT_SIDE_TEMPERATURE_UNIT";
    public static final String PARCEL_KEY = "DetailedIduModel_PARCEL_KEY";
    public static final String POWER_OFF = "OFF";
    public static final String POWER_ON = "ON";
    public static final String WEATHER_AVAILABLE = "WEATHER_AVAILABLE";
    public static final String WEATHER_DETAILS = "WEATHER_DETAILS";
    public static final String WEATHER_ID = "WEATHER_ID";
    public static final String WEATHER_TYPE = "WEATHER_TYPE";
    @SerializedName("cloudId")
    public String cloudId;
    @SerializedName("cmdInProgress")
    public boolean cmdInProgress;
    @SerializedName("notificationScheduleType")
    public String conflictScheduleType;
    @SerializedName("fanSpeed1")
    public int fanSpeed;
    @SerializedName("fanSpeed")
    public String fanSpeedStr;
    @SerializedName("fanSwing1")
    public int fanSwing;
    @SerializedName("fanSwing")
    public String fanSwingStr;
    public String humidity;
    public int humidityValue;

    /* renamed from: id */
    public Integer f454id;
    @SerializedName("errorStatus")
    public IduErrorStatus iduErrorStatus;
    @SerializedName("iduFrostWash")
    public boolean iduFrostWash;
    @SerializedName("iduFrostWashStatus")
    public IduFrostWashStatus iduFrostWashStatus;
    @SerializedName("iduOnByScheduler")
    public IduOnByScheduler iduOnByScheduler;
    public float iduTemperature;
    @SerializedName("lastOnlineUpdatedAt")
    public long lastOnlineUpdatedAt;
    @SerializedName("maintenanceMode")
    public int maintenanceMode;
    public String mode;
    @SerializedName("modelTypeId")
    public int modelTypeId;
    public String name;
    @SerializedName("oduFrostWash")
    public boolean oduFrostWash;
    @SerializedName("online")
    public boolean online;
    public String power;
    public Integer racId;
    @SerializedName("relativeTemperature")
    public float relativeTemperature;
    private String requestTypeString;
    public Float roomTemperature;
    @SerializedName("scheduleConflict")
    public ScheduleConflict scheduleConflict;
    public String scheduletype;
    @SerializedName("serialNumber")
    public String serialNumber;
    @SerializedName("specialOperationStatus")
    public SpecialOperationStatus specialOperationStatus;
    @SerializedName("updatedAt")
    public long updatedAt;
    public String vendorThingId;

    public enum ConflictScheduleType {
        TIMER,
        WEEKLY_TIMER
    }

    public static class FailureResponse {
        public int statusCode;
    }

    public static class SuccessResponse {
        public int statusCode;
    }

    public int describeContents() {
        return 0;
    }

    protected DetailedIduModel(Parcel parcel) {
        this.updatedAt = Long.MAX_VALUE;
        this.f454id = -1;
        this.racId = -1;
        this.roomTemperature = Float.valueOf(Float.MAX_VALUE);
        this.iduTemperature = Float.MAX_VALUE;
        this.relativeTemperature = Float.MAX_VALUE;
        this.humidity = "";
        this.power = "";
        boolean z = false;
        this.maintenanceMode = 0;
        this.lastOnlineUpdatedAt = Long.MAX_VALUE;
        this.requestTypeString = parcel.readString();
        this.updatedAt = parcel.readLong();
        this.cmdInProgress = parcel.readByte() != 0;
        this.modelTypeId = parcel.readInt();
        this.cloudId = parcel.readString();
        if (parcel.readByte() == 0) {
            this.f454id = null;
        } else {
            this.f454id = Integer.valueOf(parcel.readInt());
        }
        if (parcel.readByte() == 0) {
            this.racId = null;
        } else {
            this.racId = Integer.valueOf(parcel.readInt());
        }
        this.name = parcel.readString();
        this.vendorThingId = parcel.readString();
        this.roomTemperature = Float.valueOf(parcel.readFloat());
        this.mode = parcel.readString();
        this.iduTemperature = parcel.readFloat();
        this.relativeTemperature = parcel.readFloat();
        this.humidityValue = parcel.readInt();
        this.humidity = parcel.readString();
        this.power = parcel.readString();
        this.fanSpeed = parcel.readInt();
        this.fanSpeedStr = parcel.readString();
        this.fanSwing = parcel.readInt();
        this.fanSwingStr = parcel.readString();
        this.iduFrostWash = parcel.readByte() != 0;
        this.oduFrostWash = parcel.readByte() != 0;
        this.serialNumber = parcel.readString();
        this.maintenanceMode = parcel.readInt();
        this.online = parcel.readByte() != 0 ? true : z;
        this.iduFrostWashStatus = (IduFrostWashStatus) parcel.readParcelable(IduFrostWashStatus.class.getClassLoader());
        this.specialOperationStatus = (SpecialOperationStatus) parcel.readParcelable(SpecialOperationStatus.class.getClassLoader());
        this.iduErrorStatus = (IduErrorStatus) parcel.readParcelable(IduErrorStatus.class.getClassLoader());
        this.iduOnByScheduler = (IduOnByScheduler) parcel.readParcelable(IduOnByScheduler.class.getClassLoader());
        this.scheduleConflict = (ScheduleConflict) parcel.readParcelable(ScheduleConflict.class.getClassLoader());
        this.scheduletype = parcel.readString();
        this.conflictScheduleType = parcel.readString();
        this.lastOnlineUpdatedAt = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.requestTypeString);
        parcel.writeLong(this.updatedAt);
        parcel.writeByte(this.cmdInProgress ? (byte) 1 : 0);
        parcel.writeInt(this.modelTypeId);
        parcel.writeString(this.cloudId);
        if (this.f454id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(this.f454id.intValue());
        }
        if (this.racId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(this.racId.intValue());
        }
        parcel.writeString(this.name);
        parcel.writeString(this.vendorThingId);
        parcel.writeFloat(this.roomTemperature.floatValue());
        parcel.writeString(this.mode);
        parcel.writeFloat(this.iduTemperature);
        parcel.writeFloat(this.relativeTemperature);
        parcel.writeInt(this.humidityValue);
        parcel.writeString(this.humidity);
        parcel.writeString(this.power);
        parcel.writeInt(this.fanSpeed);
        parcel.writeString(this.fanSpeedStr);
        parcel.writeInt(this.fanSwing);
        parcel.writeString(this.fanSwingStr);
        parcel.writeByte(this.iduFrostWash ? (byte) 1 : 0);
        parcel.writeByte(this.oduFrostWash ? (byte) 1 : 0);
        parcel.writeString(this.serialNumber);
        parcel.writeInt(this.maintenanceMode);
        parcel.writeByte(this.online ? (byte) 1 : 0);
        parcel.writeParcelable(this.iduFrostWashStatus, i);
        parcel.writeParcelable(this.specialOperationStatus, i);
        parcel.writeParcelable(this.iduErrorStatus, i);
        parcel.writeParcelable(this.iduOnByScheduler, i);
        parcel.writeParcelable(this.scheduleConflict, i);
        parcel.writeString(this.scheduletype);
        parcel.writeString(this.conflictScheduleType);
        parcel.writeLong(this.lastOnlineUpdatedAt);
    }

    static {
        DetailedIduModel detailedIduModel = new DetailedIduModel();
        COOLING_DEFAULT = detailedIduModel;
        detailedIduModel.mode = "COOLING";
        detailedIduModel.iduTemperature = 27.0f;
        detailedIduModel.humidity = StatusCode.BUILTIN_WIRELESS;
    }

    public void setRequestTypeString(String str) {
        this.requestTypeString = str;
    }

    public WebSocketNotification.RequestType getRequestTypeEnum() {
        return WebSocketNotification.RequestType.valueOf(this.requestTypeString);
    }

    public ConflictScheduleType getConflictScheduleType() {
        try {
            return ConflictScheduleType.valueOf(this.conflictScheduleType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void copyModeAndTemperature(DetailedIduModel detailedIduModel) {
        this.mode = detailedIduModel.mode;
        this.iduTemperature = detailedIduModel.iduTemperature;
        this.humidity = detailedIduModel.humidity;
        this.relativeTemperature = detailedIduModel.relativeTemperature;
    }

    public DetailedIduModel(boolean z) {
        this.updatedAt = Long.MAX_VALUE;
        this.f454id = -1;
        this.racId = -1;
        this.roomTemperature = Float.valueOf(Float.MAX_VALUE);
        this.iduTemperature = Float.MAX_VALUE;
        this.relativeTemperature = Float.MAX_VALUE;
        this.humidity = "";
        this.power = "";
        this.maintenanceMode = 0;
        this.lastOnlineUpdatedAt = Long.MAX_VALUE;
        this.f454id = 6;
        this.modelTypeId = 3;
        this.name = "Home";
        this.vendorThingId = Constants.Keys.f430S;
        this.roomTemperature = Float.valueOf(26.0f);
        this.mode = "COOLING";
        this.iduTemperature = 23.0f;
        this.humidity = "10";
        this.power = POWER_ON;
        this.humidityValue = Integer.parseInt("10");
        this.maintenanceMode = 0;
    }

    public DetailedIduModel() {
        this.updatedAt = Long.MAX_VALUE;
        this.f454id = -1;
        this.racId = -1;
        this.roomTemperature = Float.valueOf(Float.MAX_VALUE);
        this.iduTemperature = Float.MAX_VALUE;
        this.relativeTemperature = Float.MAX_VALUE;
        this.humidity = "";
        this.power = "";
        this.maintenanceMode = 0;
        this.lastOnlineUpdatedAt = Long.MAX_VALUE;
    }

    public void copy(DetailedIduModel detailedIduModel) {
        this.cmdInProgress = detailedIduModel.cmdInProgress;
        Integer num = detailedIduModel.f454id;
        this.f454id = Integer.valueOf(num == null ? 0 : num.intValue());
        this.name = detailedIduModel.name;
        this.vendorThingId = detailedIduModel.vendorThingId;
        this.roomTemperature = detailedIduModel.roomTemperature;
        this.mode = detailedIduModel.mode;
        this.iduTemperature = detailedIduModel.iduTemperature;
        this.humidity = detailedIduModel.humidity;
        this.power = detailedIduModel.power;
        this.humidityValue = detailedIduModel.humidityValue;
        this.maintenanceMode = 0;
        this.fanSpeed = detailedIduModel.fanSpeed;
        this.fanSwing = detailedIduModel.fanSwing;
        this.iduFrostWash = detailedIduModel.iduFrostWash;
        this.oduFrostWash = detailedIduModel.oduFrostWash;
        this.serialNumber = detailedIduModel.serialNumber;
        this.relativeTemperature = detailedIduModel.relativeTemperature;
        this.updatedAt = detailedIduModel.updatedAt;
        this.online = detailedIduModel.online;
        this.lastOnlineUpdatedAt = detailedIduModel.lastOnlineUpdatedAt;
        this.iduFrostWashStatus = new IduFrostWashStatus();
        this.iduErrorStatus = new IduErrorStatus();
        this.specialOperationStatus = new SpecialOperationStatus();
        this.iduFrostWashStatus.copy(detailedIduModel.iduFrostWashStatus);
        this.specialOperationStatus.copy(detailedIduModel.specialOperationStatus);
        this.iduErrorStatus.copy(detailedIduModel.iduErrorStatus);
        this.requestTypeString = detailedIduModel.requestTypeString;
        this.modelTypeId = detailedIduModel.modelTypeId;
        this.fanSpeedStr = detailedIduModel.fanSpeedStr;
        this.fanSwingStr = detailedIduModel.fanSwingStr;
        this.scheduletype = detailedIduModel.scheduletype;
        if (detailedIduModel.scheduleConflict != null) {
            ScheduleConflict scheduleConflict2 = new ScheduleConflict();
            this.scheduleConflict = scheduleConflict2;
            scheduleConflict2.copy(detailedIduModel.scheduleConflict);
        }
        this.cloudId = detailedIduModel.cloudId;
    }

    public String getPower() {
        return this.power;
    }

    public Power getPowerEnum() {
        return Power.valueOf(this.power);
    }

    public void setPower(String str) {
        this.power = str;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public Integer getId() {
        return this.f454id;
    }

    public void setId(Integer num) {
        this.f454id = num;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }

    public Float getRoomTemperature() {
        return this.roomTemperature;
    }

    public void setRoomTemperature(Float f) {
        this.roomTemperature = f;
    }

    public float getIduTemperature() {
        return this.iduTemperature;
    }

    public void setIduTemperature(float f) {
        this.iduTemperature = f;
    }

    public String getHumidity() {
        return this.humidity;
    }

    public void setHumidity(String str) {
        this.humidity = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f454id.equals(((DetailedIduModel) obj).f454id);
    }

    public boolean equalWholeObj(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetailedIduModel detailedIduModel = (DetailedIduModel) obj;
        if (!Objects.equals(this.f454id, detailedIduModel.f454id) || !Objects.equals(this.name, detailedIduModel.name) || !Objects.equals(this.vendorThingId, detailedIduModel.vendorThingId) || !Objects.equals(this.roomTemperature, detailedIduModel.roomTemperature) || !Objects.equals(this.mode, detailedIduModel.mode) || !Objects.equals(Float.valueOf(this.iduTemperature), Float.valueOf(detailedIduModel.iduTemperature)) || !Objects.equals(this.humidity, detailedIduModel.humidity) || !Objects.equals(this.power, detailedIduModel.power)) {
            return false;
        }
        return true;
    }

    public int compareTo(DetailedIduModel detailedIduModel) {
        try {
            if (getChecksum(detailedIduModel).equals(getChecksum(this))) {
                return 0;
            }
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    private String getChecksum(Parcelable parcelable) throws IOException, NoSuchAlgorithmException {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream2.writeObject(parcelable);
                    String str = new String(MessageDigest.getInstance("MD5").digest(byteArrayOutputStream.toByteArray()), StandardCharsets.UTF_8);
                    objectOutputStream2.close();
                    byteArrayOutputStream.close();
                    return str;
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    objectOutputStream.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                objectOutputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            objectOutputStream.close();
            byteArrayOutputStream.close();
            throw th;
        }
    }

    public void copyDefaults(OperationMode operationMode) {
        this.fanSpeed = operationMode.getDefaultFanSpeed();
        this.humidityValue = operationMode.getDefaultHumidity();
        this.humidity = String.valueOf(operationMode.getDefaultHumidity());
        this.relativeTemperature = 0.0f;
    }

    public void copyCoolingModeDefaults(RacModelWiseData racModelWiseData) {
        RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = racModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(OperationMode.COOLING);
        this.mode = OperationMode.COOLING.name();
        this.iduTemperature = racModeDetailBasedOnMode.getDefaultTemperature();
        this.relativeTemperature = 0.0f;
        this.humidity = String.valueOf(racModeDetailBasedOnMode.getDefaultHumidity());
        this.fanSpeedStr = racModeDetailBasedOnMode.getDefaultFanSpeedDefaults().getDefaultFanSpeed().name();
    }

    public void copyDefaults(RacModelWiseData racModelWiseData) {
        RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = racModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(getOperationModeEnum());
        if (racModeDetailBasedOnMode != null) {
            RacModelWiseData.TemperatureSettingType temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType();
            if (temperatureSettingType == null || !temperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.RELATIVE)) {
                this.iduTemperature = racModeDetailBasedOnMode.getDefaultTemperature();
            } else {
                this.relativeTemperature = racModeDetailBasedOnMode.getDefaultTemperature();
            }
            this.humidity = String.valueOf(racModeDetailBasedOnMode.getDefaultHumidity());
            this.fanSpeedStr = racModeDetailBasedOnMode.getDefaultFanSpeedDefaults().getDefaultFanSpeed().name();
        }
    }

    public void copyRacInfo(DetailedIduModel detailedIduModel) {
        this.modelTypeId = detailedIduModel.modelTypeId;
        this.name = detailedIduModel.name;
        this.f454id = detailedIduModel.f454id;
        this.vendorThingId = detailedIduModel.vendorThingId;
        this.cloudId = detailedIduModel.cloudId;
    }

    public static class SpecialOperationStatus implements Parcelable {
        public static final Parcelable.Creator<SpecialOperationStatus> CREATOR = new Parcelable.Creator<SpecialOperationStatus>() {
            public SpecialOperationStatus createFromParcel(Parcel parcel) {
                return new SpecialOperationStatus(parcel);
            }

            public SpecialOperationStatus[] newArray(int i) {
                return new SpecialOperationStatus[i];
            }
        };
        @SerializedName("active")
        public boolean active;
        @SerializedName("lastUpdatedAt")
        public long lastUpdatedAt;
        @SerializedName("priority")
        public int priority;

        public int describeContents() {
            return 0;
        }

        public void copy(SpecialOperationStatus specialOperationStatus) {
            this.active = specialOperationStatus.active;
            this.priority = specialOperationStatus.priority;
            this.lastUpdatedAt = specialOperationStatus.lastUpdatedAt;
        }

        public SpecialOperationStatus() {
        }

        protected SpecialOperationStatus(Parcel parcel) {
            this.active = parcel.readByte() != 0;
            this.priority = parcel.readInt();
            this.lastUpdatedAt = parcel.readLong();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte(this.active ? (byte) 1 : 0);
            parcel.writeInt(this.priority);
            parcel.writeLong(this.lastUpdatedAt);
        }
    }

    public static class ScheduleConflict implements Parcelable {
        public static final Parcelable.Creator<ScheduleConflict> CREATOR = new Parcelable.Creator<ScheduleConflict>() {
            public ScheduleConflict createFromParcel(Parcel parcel) {
                return new ScheduleConflict(parcel);
            }

            public ScheduleConflict[] newArray(int i) {
                return new ScheduleConflict[i];
            }
        };
        @SerializedName("active")
        public boolean active;
        @SerializedName("lastUpdatedAt")
        public long lastUpdatedAt;
        @SerializedName("priority")
        public int priority;

        public int describeContents() {
            return 0;
        }

        public void copy(ScheduleConflict scheduleConflict) {
            this.active = scheduleConflict.active;
            this.priority = scheduleConflict.priority;
            this.lastUpdatedAt = scheduleConflict.lastUpdatedAt;
        }

        public ScheduleConflict() {
        }

        protected ScheduleConflict(Parcel parcel) {
            this.active = parcel.readByte() != 0;
            this.priority = parcel.readInt();
            this.lastUpdatedAt = parcel.readLong();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte(this.active ? (byte) 1 : 0);
            parcel.writeInt(this.priority);
            parcel.writeLong(this.lastUpdatedAt);
        }
    }

    public static class IduErrorStatus implements Parcelable {
        public static final Parcelable.Creator<IduErrorStatus> CREATOR = new Parcelable.Creator<IduErrorStatus>() {
            public IduErrorStatus createFromParcel(Parcel parcel) {
                return new IduErrorStatus(parcel);
            }

            public IduErrorStatus[] newArray(int i) {
                return new IduErrorStatus[i];
            }
        };
        @SerializedName("active")
        public boolean active;
        @SerializedName("errorCode")
        public String errorCode;
        @SerializedName("lastUpdatedAt")
        public long lastUpdatedAt;
        @SerializedName("priority")
        public int priority;
        @SerializedName("subCategory")
        public String subCategory;

        public int describeContents() {
            return 0;
        }

        protected IduErrorStatus(Parcel parcel) {
            this.active = parcel.readByte() != 0;
            this.priority = parcel.readInt();
            this.lastUpdatedAt = parcel.readLong();
            this.subCategory = parcel.readString();
            this.errorCode = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte(this.active ? (byte) 1 : 0);
            parcel.writeInt(this.priority);
            parcel.writeLong(this.lastUpdatedAt);
            parcel.writeString(this.subCategory);
            parcel.writeString(this.errorCode);
        }

        public void copy(IduErrorStatus iduErrorStatus) {
            this.active = iduErrorStatus.active;
            this.priority = iduErrorStatus.priority;
            this.lastUpdatedAt = iduErrorStatus.lastUpdatedAt;
            this.subCategory = iduErrorStatus.subCategory;
            this.errorCode = iduErrorStatus.errorCode;
        }

        public IduErrorStatus() {
        }
    }

    public static class IduFrostWashStatus implements Parcelable {
        public static final Parcelable.Creator<IduFrostWashStatus> CREATOR = new Parcelable.Creator<IduFrostWashStatus>() {
            public IduFrostWashStatus createFromParcel(Parcel parcel) {
                return new IduFrostWashStatus(parcel);
            }

            public IduFrostWashStatus[] newArray(int i) {
                return new IduFrostWashStatus[i];
            }
        };
        @SerializedName("active")
        public boolean active;
        @SerializedName("lastUpdatedAt")
        public long lastUpdatedAt;
        @SerializedName("priority")
        public int priority;

        public int describeContents() {
            return 0;
        }

        public void copy(IduFrostWashStatus iduFrostWashStatus) {
            this.active = iduFrostWashStatus.active;
            this.priority = iduFrostWashStatus.priority;
            this.lastUpdatedAt = iduFrostWashStatus.lastUpdatedAt;
        }

        public IduFrostWashStatus() {
        }

        protected IduFrostWashStatus(Parcel parcel) {
            this.active = parcel.readByte() != 0;
            this.priority = parcel.readInt();
            this.lastUpdatedAt = parcel.readLong();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte(this.active ? (byte) 1 : 0);
            parcel.writeInt(this.priority);
            parcel.writeLong(this.lastUpdatedAt);
        }
    }

    public static class IduOnByScheduler implements Parcelable {
        public static final Parcelable.Creator<IduOnByScheduler> CREATOR = new Parcelable.Creator<IduOnByScheduler>() {
            public IduOnByScheduler createFromParcel(Parcel parcel) {
                return new IduOnByScheduler(parcel);
            }

            public IduOnByScheduler[] newArray(int i) {
                return new IduOnByScheduler[i];
            }
        };
        @SerializedName("active")
        public boolean active;
        @SerializedName("lastUpdatedAt")
        public long lastUpdatedAt;
        @SerializedName("priority")
        public int priority;

        public int describeContents() {
            return 0;
        }

        public void copy(IduOnByScheduler iduOnByScheduler) {
            this.active = iduOnByScheduler.active;
            this.priority = iduOnByScheduler.priority;
            this.lastUpdatedAt = iduOnByScheduler.lastUpdatedAt;
        }

        public IduOnByScheduler() {
        }

        protected IduOnByScheduler(Parcel parcel) {
            this.active = parcel.readByte() != 0;
            this.priority = parcel.readInt();
            this.lastUpdatedAt = parcel.readLong();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte(this.active ? (byte) 1 : 0);
            parcel.writeInt(this.priority);
            parcel.writeLong(this.lastUpdatedAt);
        }
    }

    public boolean isInNormalMode() {
        return !this.specialOperationStatus.active && !this.iduFrostWashStatus.active && !this.iduErrorStatus.active && this.online;
    }

    public boolean isInSpecialMode() {
        SpecialOperationStatus specialOperationStatus2 = this.specialOperationStatus;
        return specialOperationStatus2 != null && specialOperationStatus2.active;
    }

    public boolean isFrostWashRunning() {
        IduFrostWashStatus iduFrostWashStatus2 = this.iduFrostWashStatus;
        return iduFrostWashStatus2 != null && iduFrostWashStatus2.active;
    }

    public boolean isInErrorState() {
        IduErrorStatus iduErrorStatus2 = this.iduErrorStatus;
        return iduErrorStatus2 != null && iduErrorStatus2.active;
    }

    public boolean isTimerEnabled() {
        String str = this.scheduletype;
        if (str == null) {
            return false;
        }
        WeeklyTimerMode valueOf = WeeklyTimerMode.valueOf(str);
        if ((valueOf == WeeklyTimerMode.OFF_TIMER_ENABLED || valueOf == WeeklyTimerMode.ON_TIMER_ENABLED || valueOf == WeeklyTimerMode.ON_OFF_TIMER_ENABLED) && isTurnedOn()) {
            return true;
        }
        return false;
    }

    public boolean isWeeklyTimerEnabled() {
        String str = this.scheduletype;
        if (str == null || WeeklyTimerMode.valueOf(str) != WeeklyTimerMode.WEEKLY_TIMER_ENABLED) {
            return false;
        }
        return true;
    }

    public boolean isHolidayModeEnabled() {
        String str = this.scheduletype;
        if (str == null || WeeklyTimerMode.valueOf(str) != WeeklyTimerMode.HOLIDAY_MODE_ENABLED) {
            return false;
        }
        return true;
    }

    public boolean isInOnStateByScheduler() {
        IduOnByScheduler iduOnByScheduler2 = this.iduOnByScheduler;
        return iduOnByScheduler2 != null && iduOnByScheduler2.active;
    }

    public boolean isOnline() {
        return this.online;
    }

    public OperationMode getOperationModeEnum() {
        return OperationMode.valueOf(this.mode);
    }

    public boolean isTurnedOn() {
        return this.power.equals(POWER_ON);
    }

    public SwingOption getSwingOptionEnum() {
        return SwingOption.valueOf(this.fanSwingStr);
    }

    public boolean isOfflineFromLast20Hours() {
        return System.currentTimeMillis() - this.lastOnlineUpdatedAt >= MILLISECONDS_22_HOURS;
    }

    public enum FanSpeed {
        LV1,
        LV2,
        LV3,
        LV4,
        LV5,
        AUTO;

        public static FanSpeed getDefaultFanSpeedEnum(RacModelWiseData.FanSpeedDefaults fanSpeedDefaults) {
            if (fanSpeedDefaults.getLv1()) {
                return LV1;
            }
            if (fanSpeedDefaults.getLv2()) {
                return LV2;
            }
            if (fanSpeedDefaults.getLv3()) {
                return LV3;
            }
            if (fanSpeedDefaults.getLv4()) {
                return LV4;
            }
            if (fanSpeedDefaults.getLv5()) {
                return LV5;
            }
            if (fanSpeedDefaults.getAuto()) {
                return AUTO;
            }
            return null;
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.model.DetailedIduModel$2 */
    static /* synthetic */ class C18502 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$iduManagement$model$DetailedIduModel$SwingOption */
        static final /* synthetic */ int[] f455x93333a03;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$SwingOption[] r0 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.SwingOption.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f455x93333a03 = r0
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$SwingOption r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.SwingOption.OFF     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f455x93333a03     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$SwingOption r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.SwingOption.VERTICAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f455x93333a03     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$SwingOption r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.SwingOption.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f455x93333a03     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$SwingOption r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.SwingOption.BOTH     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f455x93333a03     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.DetailedIduModel$SwingOption r1 = com.jch.racWiFi.iduManagement.model.DetailedIduModel.SwingOption.AUTO     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.model.DetailedIduModel.C18502.<clinit>():void");
        }
    }

    public enum SwingOption {
        OFF,
        VERTICAL,
        HORIZONTAL,
        BOTH,
        AUTO;

        public int getStringResForDisplay() {
            int i = C18502.f455x93333a03[ordinal()];
            if (i == 1) {
                return R.string.iduSwing_lbl_off;
            }
            if (i == 2) {
                return R.string.iduSwing_lbl_vertical;
            }
            if (i == 3) {
                return R.string.iduSwing_lbl_horizontal;
            }
            if (i == 4) {
                return R.string.iduSwing_lbl_horizontalAndVertical;
            }
            if (i != 5) {
                return -1;
            }
            return R.string.common_lbl_auto;
        }
    }

    public void updateCommandBasedOnRacModelWiseRestrictions(RacModelWiseData racModelWiseData) {
        RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = racModelWiseData.getRacModeDetails().getRacModeDetailBasedOnMode(getOperationModeEnum());
        if (racModeDetailBasedOnMode != null) {
            if (!isHumidityValidBasedOnRacModelWiseData(racModeDetailBasedOnMode)) {
                this.humidity = String.valueOf(racModeDetailBasedOnMode.getDefaultHumidity());
            }
            if (!isTemperatureValidRacModelWiseData(racModeDetailBasedOnMode)) {
                RacModelWiseData.TemperatureSettingType temperatureSettingType = racModeDetailBasedOnMode.getTemperatureSettingType();
                if (temperatureSettingType == null || !temperatureSettingType.equals(RacModelWiseData.TemperatureSettingType.ABSOLUTE)) {
                    this.relativeTemperature = racModeDetailBasedOnMode.getDefaultTemperature();
                } else {
                    this.iduTemperature = racModeDetailBasedOnMode.getDefaultTemperature();
                }
            }
            if (!isFanSpeedRacModelWiseData(racModeDetailBasedOnMode)) {
                this.fanSpeedStr = racModeDetailBasedOnMode.getEnableFanSpeedDefaults().getDefaultFanSpeed().name();
            }
            if (getSwingOptionEnum() != null && getSwingOptionEnum().equals(SwingOption.AUTO)) {
                this.fanSwingStr = SwingOption.OFF.name();
            }
        }
    }

    private boolean isHumidityValidBasedOnRacModelWiseData(RacModelWiseData.RacModeDetail racModeDetail) {
        return racModeDetail.isHumidityValueInRange(Float.parseFloat(this.humidity));
    }

    private boolean isTemperatureValidRacModelWiseData(RacModelWiseData.RacModeDetail racModeDetail) {
        if (racModeDetail.getTemperatureSettingType() == null || !racModeDetail.getTemperatureSettingType().equals(RacModelWiseData.TemperatureSettingType.ABSOLUTE)) {
            return racModeDetail.isTemperatureValueInRange(this.relativeTemperature);
        }
        return racModeDetail.isTemperatureValueInRange(this.iduTemperature);
    }

    private boolean isFanSpeedRacModelWiseData(RacModelWiseData.RacModeDetail racModeDetail) {
        return racModeDetail.isFanSpeedValueInRange(FanSpeed.valueOf(this.fanSpeedStr));
    }

    public boolean didModeChanged(DetailedIduModel detailedIduModel) {
        return !getOperationModeEnum().equals(detailedIduModel.getOperationModeEnum());
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARCEL_KEY, this);
        return bundle;
    }

    public AcActivitiesList getAcActivitiesList(ModelRepository modelRepository) {
        AcActivitiesList acActivitiesList = new AcActivitiesList();
        acActivitiesList.add(modelRepository.getAcActivity(this, AcActivitiesSubCategory.SPECIAL_OPERATION));
        acActivitiesList.add(modelRepository.getAcActivity(this, AcActivitiesSubCategory.IDU_FROST_WASH));
        acActivitiesList.add(modelRepository.getAcActivity(this, AcActivitiesSubCategory.ERROR_DETAILS));
        return acActivitiesList;
    }
}
