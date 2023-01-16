package com.jch.racWiFi.iduManagement.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.C1662R2;
import java.util.ArrayList;
import java.util.List;

public class StopAllUnitsModels {
    public static List<Integer> ERROR_RESPONSE;

    public static class IndividualRacStartAllUnitResponseData {
        @SerializedName("commandResponse")
        public CommandStatus commandStatus;
        @SerializedName("errorCode")
        public int errorCode;
        public String errorMessage;
        public int racId;
        public boolean success;
    }

    public static class IndividualRacStopAllUintResponseData {
        @SerializedName("commandResponse")
        public CommandStatus commandStatus;
        @SerializedName("errorCode")
        public int errorCode;
        public String errorMessage;
        public int racId;
        public boolean success;
    }

    public static class StartAllUnitsFailureResponse {
        public int statusCode;
    }

    public static class StartAllUnitsSuccessResponse {
        public boolean allSucceeded;
        public List<IndividualRacStartAllUnitResponseData> resultSet = new ArrayList();
    }

    public static class StopAllnitsFailureResponse {
        public int statusCode;
    }

    public static class StopAllnitsSuccessResponse {
        public boolean allSucceeded;
        public List<IndividualRacStopAllUintResponseData> resultSet = new ArrayList();
    }

    static {
        ArrayList arrayList = new ArrayList();
        ERROR_RESPONSE = arrayList;
        arrayList.add(412);
        ERROR_RESPONSE.add(Integer.valueOf(C1662R2.attr.defaultNavHost));
        ERROR_RESPONSE.add(423);
        ERROR_RESPONSE.add(429);
        ERROR_RESPONSE.add(404);
        ERROR_RESPONSE.add(500);
    }
}
