package com.jch.racWiFi.iduManagement.smartFence.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.userManagement.model.UserFamilyMemberModels;
import java.util.List;

public class RacToUserFamilyMemberInfo {
    @SerializedName("racId")
    public Long racId;
    @SerializedName("users")
    public List<UserFamilyMemberModels.UserFamilyMemberInfo> users;

    public boolean equals(Object obj) {
        if (obj instanceof RacToUserFamilyMemberInfo) {
            return this.racId.equals(((RacToUserFamilyMemberInfo) obj).racId);
        }
        return false;
    }
}
