package com.jch.racWiFi.userManagement.model.dto;

import com.google.gson.annotations.SerializedName;

public class ChangeDto {
    @SerializedName("enabled")
    private Boolean mEnabled;
    @SerializedName("featureId")
    private Long mFeatureId;
    @SerializedName("roleId")
    private Long mRoleId;

    public Boolean getEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(Boolean bool) {
        this.mEnabled = bool;
    }

    public Long getFeatureId() {
        return this.mFeatureId;
    }

    public void setFeatureId(Long l) {
        this.mFeatureId = l;
    }

    public Long getRoleId() {
        return this.mRoleId;
    }

    public void setRoleId(Long l) {
        this.mRoleId = l;
    }

    public String toString() {
        return "ChangeDto{mEnabled=" + this.mEnabled + ", mFeatureId=" + this.mFeatureId + ", mRoleId=" + this.mRoleId + '}' + "\n\n";
    }
}
