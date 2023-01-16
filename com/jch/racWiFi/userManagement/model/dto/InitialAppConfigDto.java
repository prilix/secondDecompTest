package com.jch.racWiFi.userManagement.model.dto;

import java.util.Map;

public class InitialAppConfigDto {
    private Map<Integer, FeatureDto> features;
    private Map<Integer, RoleDto> roles;

    public Map<Integer, FeatureDto> getFeatures() {
        return this.features;
    }

    public void setFeatures(Map<Integer, FeatureDto> map) {
        this.features = map;
    }

    public Map<Integer, RoleDto> getRoles() {
        return this.roles;
    }

    public void setRoles(Map<Integer, RoleDto> map) {
        this.roles = map;
    }
}
