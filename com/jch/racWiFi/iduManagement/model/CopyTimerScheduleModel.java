package com.jch.racWiFi.iduManagement.model;

import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.GenericResponse;
import com.jch.racWiFi.GenericSuccessResponse;

public class CopyTimerScheduleModel {

    public static class CopyTimerScheduleFailureResponse extends GenericErrorResponse<CopyTimerScheduleModel> {
    }

    public static class CopyTimerScheduleResponse extends GenericResponse {
    }

    public static class CopyTimerScheduleSuccessResponse extends GenericSuccessResponse<CopyTimerScheduleModel> {
    }
}
