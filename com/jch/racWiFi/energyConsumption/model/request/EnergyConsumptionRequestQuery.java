package com.jch.racWiFi.energyConsumption.model.request;

public class EnergyConsumptionRequestQuery {
    private String end = "";
    private String filterBy = "";
    private String requestType = "";
    private String start = "";
    private String time;
    private String type;
    private String year = "";

    public String getFilterBy() {
        return this.filterBy;
    }

    public void setFilterBy(String str) {
        this.filterBy = str;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String str) {
        this.start = str;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String str) {
        this.end = str;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String str) {
        this.year = str;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String str) {
        this.requestType = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }
}
