package com.jch.racWiFi.fcm.util;

import com.jch_hitachi.aircloudglobal.R;

public enum AcActivitiesSubCategory {
    CLEAN_FILTER(R.drawable.ic_red_clean_filter_error_message, R.string.notification_lbl_cleanFilter, R.string.notification_lbl_cleanFiltermessage),
    ERROR_DETAILS(R.drawable.ic_red_critical_error_notification, R.string.notification_lbl_criticalError, R.string.notification_lbl_criticalErrorMessage),
    SPECIAL_OPERATION(R.drawable.ic_grey_special_state_2_notification, R.string.notification_lbl_specialOp, R.string.notification_lbl_specialOperationMessage),
    IDU_FROST_WASH(R.drawable.ic_idu_frost_wash_svg, R.string.notification_lbl_frostWashIndoor, R.string.notification_lbl_frostWashIndoorDesc),
    ODU_FROST_WASH(R.drawable.ic_notification_odu_frost_wash, R.string.odu_forst_wash, R.string.odu_forst_wash_message);
    
    private final int[] mAttributes;
    private final int mIcon;

    private AcActivitiesSubCategory(int i, int... iArr) {
        this.mIcon = i;
        this.mAttributes = iArr;
    }

    public int getIcon() {
        return this.mIcon;
    }

    public int[] getAttributes() {
        return this.mAttributes;
    }
}
