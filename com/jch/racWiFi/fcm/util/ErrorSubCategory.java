package com.jch.racWiFi.fcm.util;

import com.jch_hitachi.aircloudglobal.R;

public enum ErrorSubCategory {
    INDOOR(R.string.notification_lbl_errors, R.string.notification_lbl_errorTitle, R.string.notification_lbl_errorDesc, R.string.notification_lbl_iduError, R.string.notification_lbl_indoor),
    OUTDOOR(R.string.notification_lbl_errors, R.string.notification_lbl_errorTitle, R.string.notification_lbl_errorDesc, R.string.notification_lbl_oduError, R.string.notification_lbl_outdoor);
    
    private final int[] mAttributes;

    private ErrorSubCategory(int... iArr) {
        this.mAttributes = iArr;
    }

    public int[] getAttributes() {
        return this.mAttributes;
    }
}
