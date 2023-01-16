package com.jch.racWiFi.fcm.util;

import com.jch_hitachi.aircloudglobal.R;

public enum MaintenanceSubCategory {
    PLANNED_MAINTENANCE(R.mipmap.ic_launcher_global_round, R.string.splash_lbl_plannedMaintanance, R.string.plannedMaintenance_lbl_desc),
    SERVICE_UNAVAILABLE(R.mipmap.ic_launcher_global_round, R.string.splash_lbl_servicesTempUnavailable, R.string.splash_lbl_servicesTempUnavailableDesc);
    
    private final int[] mAttributes;
    private final int mIcon;

    private MaintenanceSubCategory(int i, int... iArr) {
        this.mIcon = i;
        this.mAttributes = iArr;
    }

    public int[] getAttributes() {
        return this.mAttributes;
    }

    public int getIcon() {
        return this.mIcon;
    }
}
