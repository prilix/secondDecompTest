package com.jch.racWiFi.userManagement.model.dto;

public class PermissionErrorData {
    private String code = "";
    private String details = "";
    private String message = "";
    private String time = "";

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String str) {
        this.details = str;
    }
}
