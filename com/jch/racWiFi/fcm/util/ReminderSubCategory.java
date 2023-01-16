package com.jch.racWiFi.fcm.util;

import com.jch_hitachi.aircloudglobal.R;

public enum ReminderSubCategory {
    CLEANING(R.drawable.ic_cleaning_and_trial, R.string.notification_lbl_cleaningAndTrialRun, R.string.notification_lbl_cleaningAndTrialRunDesc),
    USER_ACCEPTED(R.drawable.ic_user_pic, R.string.notifications_lbl_userAdded, R.string.notifications_lbl_userAcceptedInviteDesc);
    
    private final int[] mAttributes;
    private final int mIcon;

    private ReminderSubCategory(int i, int... iArr) {
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
