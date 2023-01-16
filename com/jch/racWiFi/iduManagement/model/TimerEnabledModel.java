package com.jch.racWiFi.iduManagement.model;

import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.GenericResponse;
import com.jch.racWiFi.GenericSuccessResponse;

public class TimerEnabledModel {

    public static class TimerDisableResponse extends GenericResponse {
        public static final int Code2 = 100;
        public static final int Code3 = 100;
        public static final int DEVICE_OFFLINE = 412;
    }

    public static class TimerEnabled {
    }

    public static class TimerEnabledResponse extends GenericResponse {
        public static final int Code2 = 100;
        public static final int Code3 = 100;
        public static final int DEVICE_OFFLINE = 412;
    }

    public static class TimerUpdateFailureResponse extends GenericErrorResponse {
    }

    public static class TimerUpdateSuccessResponse extends GenericSuccessResponse {
    }

    public static class WeeklyTimerEnabled {
    }
}
