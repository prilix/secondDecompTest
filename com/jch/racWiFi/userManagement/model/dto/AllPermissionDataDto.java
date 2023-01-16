package com.jch.racWiFi.userManagement.model.dto;

import java.util.HashMap;

public class AllPermissionDataDto {
    private HashMap<String, Boolean> editableSettingsMap;
    private HashMap<String, Boolean> map;
    private Integer totalRac;

    public HashMap<String, Boolean> getEditableSettingsMap() {
        return this.editableSettingsMap;
    }

    public Integer getTotalRac() {
        return this.totalRac;
    }

    public void setTotalRac(Integer num) {
        this.totalRac = num;
    }

    public HashMap<String, Boolean> getMap() {
        return this.map;
    }

    public void setMap(HashMap<String, Boolean> hashMap) {
        this.map = hashMap;
    }
}
