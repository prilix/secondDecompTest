package com.jch.racWiFi.iduManagement.model;

import com.jch.racWiFi.GenericErrorResponse;
import com.jch.racWiFi.GenericSuccessResponse;
import java.util.List;

public class CleaningHistoryModel {

    public static class CleaningHistoryFailureResponse extends GenericErrorResponse {
    }

    public static class CleaningHistorySuccessResponse extends GenericSuccessResponse<List<CleaningHistoryModelV2>> {
    }

    public static class CleaningHistoryModelV2 {
        private String cleaningDate;
        private String cleaningTime;
        private String operationName;

        public CleaningHistoryModelV2(String str, String str2, String str3) {
            this.operationName = str;
            this.cleaningDate = str2;
            this.cleaningTime = str3;
        }

        public String getOperationName() {
            return this.operationName;
        }

        public void setOperationName(String str) {
            this.operationName = str;
        }

        public String getDate() {
            return this.cleaningDate;
        }

        public void setDate(String str) {
            this.cleaningDate = str;
        }

        public String getTime() {
            return this.cleaningTime;
        }

        public void setTime(String str) {
            this.cleaningTime = str;
        }
    }
}
