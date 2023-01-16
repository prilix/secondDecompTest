package com.jch.racWiFi.userManagement.model.dto;

import com.google.gson.annotations.SerializedName;
import java.util.Collection;

public class PermissionSaveDto {
    @SerializedName("changes")
    private Collection<ChangeDto> mChangeDtos;
    @SerializedName("racId")
    private Integer mRacId;

    public Collection<ChangeDto> getChanges() {
        return this.mChangeDtos;
    }

    public void setChanges(Collection<ChangeDto> collection) {
        this.mChangeDtos = collection;
    }

    public Integer getRacId() {
        return this.mRacId;
    }

    public void setRacId(Integer num) {
        this.mRacId = num;
    }
}
