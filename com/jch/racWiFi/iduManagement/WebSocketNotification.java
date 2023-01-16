package com.jch.racWiFi.iduManagement;

import com.google.gson.annotations.SerializedName;

public class WebSocketNotification<T> {
    @SerializedName("data")
    public T data;
    @SerializedName("notificationType")
    public String requestType;

    public RequestType getRequestTypeEnum() {
        return RequestType.valueOf(this.requestType);
    }

    public enum RequestType {
        ON_CONNECT,
        REFRESH_ALL,
        REFRESH_INDIVIDUAL,
        BUCKET_UPDATE,
        SCHEDULE_CONFLICT,
        USER_FAMILY_DETACH;

        public boolean isBucketUpdate() {
            return equals(BUCKET_UPDATE);
        }

        public boolean isRefreshAll() {
            return equals(REFRESH_ALL);
        }

        public boolean isRefreshIndividual() {
            return equals(REFRESH_INDIVIDUAL);
        }

        public boolean onConnect() {
            return equals(ON_CONNECT);
        }
    }
}
