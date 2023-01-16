package com.jch.racWiFi.iduManagement.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.GenericResponse;

public class TimerModels {

    public static class AddRequestData {
        public String displayFormat;
        public String endsAt;
        public String mode;
        public String power;
        public long racId;
        public String startsAt;
        public double temperature;
    }

    public static class ResponseData {
        public String displayFormat;
        public String endsAt;

        /* renamed from: id */
        public long f461id;
        public String mode;
        public String power;
        public long racId;
        public String startsAt;
        public double temperature;
    }

    public static class TimerFetchResponse extends GenericResponse<ResponseData> {
        public boolean isFromTimerPage;
    }

    public static class TimerRequestData {
        @SerializedName("enabled")
        public boolean enabled;
        @SerializedName("endsAt")
        public String endsAt;
        @SerializedName("humidity")
        public int humidity;
        @SerializedName("mode")
        public String mode;
        public int racId;
        @SerializedName("startsAt")
        public String startsAt;
        @SerializedName("switchOffAfter")
        public String switchOffAfter;
        @SerializedName("switchOnAfter")
        public String switchOnAfter;
        @SerializedName("temperature")
        public double temperature;
        @SerializedName("timeZone")
        public double timeZone;
    }

    public static class TimerUpdateResponse extends GenericResponse {
        public static final String ERROR_PCF011 = "PCF011";
        public static final int IDU_OFFLINE = 412;
        @SerializedName("code")
        public String code;
        @SerializedName("desc")
        public String desc;
        @SerializedName("stackTrace")
        public String stackTrace;
        @SerializedName("type")
        public String type;
    }

    public static class UpdateRequestData {
        public String displayFormat;
        public String endsAt;

        /* renamed from: id */
        public long f462id;
        public String mode;
        public String power;
        public long racId;
        public String startsAt;
        public double temperature;
    }
}
