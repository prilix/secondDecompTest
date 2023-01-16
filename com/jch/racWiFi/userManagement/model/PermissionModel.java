package com.jch.racWiFi.userManagement.model;

import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class PermissionModel implements Comparable<PermissionModel> {
    public static final int LEVEL_ARRAY_SIZE = 4;
    public boolean clickableChildDisable;
    public boolean clickableMemberDisable;
    public Integer featureID;
    public boolean isChanged;
    public Boolean[] levelWisePermission;
    public String permissionName;

    public PermissionModel() {
        this.levelWisePermission = new Boolean[3];
        this.isChanged = false;
        this.clickableMemberDisable = true;
        this.clickableChildDisable = true;
        for (int i = 0; i < 3; i++) {
            this.levelWisePermission[i] = false;
        }
    }

    public PermissionModel(PermissionModel permissionModel) {
        Boolean[] boolArr = new Boolean[3];
        this.levelWisePermission = boolArr;
        this.isChanged = false;
        this.clickableMemberDisable = true;
        this.clickableChildDisable = true;
        this.permissionName = permissionModel.permissionName;
        Boolean[] boolArr2 = permissionModel.levelWisePermission;
        if (boolArr2 != null) {
            System.arraycopy(boolArr2, 0, boolArr, 0, boolArr.length);
        }
        this.featureID = permissionModel.featureID;
        this.isChanged = permissionModel.isChanged;
        this.clickableMemberDisable = permissionModel.clickableMemberDisable;
        this.clickableChildDisable = permissionModel.clickableChildDisable;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PermissionModel permissionModel = (PermissionModel) obj;
        if (!this.permissionName.equals(permissionModel.permissionName) || !Arrays.equals(this.levelWisePermission, permissionModel.levelWisePermission) || !this.featureID.equals(permissionModel.featureID)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Objects.hash(new Object[]{this.permissionName, this.featureID}) * 31) + Arrays.hashCode(this.levelWisePermission);
    }

    public String toString() {
        return "PermissionModel{ permissionName='" + this.permissionName + "\n levelWisePermission=" + Arrays.toString(this.levelWisePermission) + "\n featureID=" + this.featureID + "\n isChanged=" + this.isChanged + "\n clickableMemberDisable=" + this.clickableMemberDisable + "\n clickableChildDisable=" + this.clickableChildDisable + StringUtils.f715LF + '}' + "\n\n";
    }

    public int compareTo(PermissionModel permissionModel) {
        return this.permissionName.compareTo(permissionModel.permissionName);
    }
}
