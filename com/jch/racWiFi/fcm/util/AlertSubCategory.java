package com.jch.racWiFi.fcm.util;

import com.jch_hitachi.aircloudglobal.R;

public enum AlertSubCategory {
    ENERGY_CONSUMPTION_BUDGET(R.drawable.ic_energy_consumption, R.string.notification_lbl_energyConsumptionMonthlyBudget, R.string.notification_lbl_energyConsumptionYouHaveConsumed),
    RAC_OFFLINE_20_HOURS(R.drawable.ic_red_critical_error_notification, R.string.errorCode_alert_PCF009, R.string.notification_lbl_racOffline20Hrs),
    RAC_OFFLINE(R.drawable.ic_red_critical_error_notification, R.string.notification_lbl_racOffline, R.string.notification_lbl_racOfflineTryAgain),
    USER_PERMISSIONS_UPDATED_FOR_ALL_ACS(R.drawable.ic_notification_role_permissions, R.string.userPermission_alert_permissionUpdated, R.string.notification_lbl_permissionUpdatedAllAcsDesc),
    USER_PERMISSIONS_UPDATED_FOR_SPECIFIC_AC(R.drawable.ic_notification_role_permissions, R.string.userPermission_alert_permissionUpdated, R.string.notification_lbl_permissionUpdatedSingleDesc),
    REMOVED_FROM_HOME(R.drawable.ic_notification_remove_home, R.string.notification_lbl_removedFromHome, R.string.notification_lbl_removedFromHomeDesc),
    ROLE_UPDATED(R.drawable.ic_notification_user_role, R.string.notification_lbl_roleUpdate, R.string.notification_lbl_roleUpdateDesc),
    USER_DETACHES_HIMSELF(R.drawable.ic_notification_remove_home, R.string.notification_lbl_userDetachedTitle, R.string.notification_lbl_userDetachedDesc),
    HOLIDAY_MODE_UPDATED(R.drawable.ic_holiday_mode, R.string.menu_item_holidaymode, R.string.notification_lbl_holidayModeUpdated),
    HOLIDAY_MODE_SWITCHED_ON_ALL(R.drawable.ic_holiday_mode, R.string.menu_item_holidaymode, R.string.notification_lbl_holidayModeSwitchedOnAll),
    HOLIDAY_MODE_SWITCHED_OFF_ALL(R.drawable.ic_holiday_mode, R.string.menu_item_holidaymode, R.string.notification_lbl_holidayModeSwitchedOffAll),
    HOLIDAY_MODE_SWITCHED_ON_SPECIFIC(R.drawable.ic_holiday_mode, R.string.menu_item_holidaymode, R.string.notification_lbl_holidayModeSwitchedOnSpecific),
    HOLIDAY_MODE_SWITCHED_OFF_SPECIFIC(R.drawable.ic_holiday_mode, R.string.menu_item_holidaymode, R.string.notification_lbl_holidayModeSwitchedOffSpecific),
    HOLIDAY_MODE_SWITCHED_OFF_DUE_TO_INTERRUPTION(R.drawable.ic_holiday_mode, R.string.menu_item_holidaymode, R.string.notification_lbl_holidayModeSwitchedOffDueToInterruption),
    HOLIDAY_MODE_SWITCHED_OFF_MULTIPLE_AS_PER_SCHEDULE(R.drawable.ic_holiday_mode, R.string.menu_item_holidaymode, R.string.notification_lbl_holidayModeSwitchedOffMultipleAsPerSchedule),
    HOLIDAY_MODE_SWITCHED_OFF_SPECIFIC_AS_PER_SCHEDULE(R.drawable.ic_holiday_mode, R.string.menu_item_holidaymode, R.string.notification_lbl_holidayModeSwitchedOffSpecificAsPerSchedule),
    HOLIDAY_MODE_SWITCHED_OFF_DUE_TO_REMOTE_ACTIVITY(R.drawable.ic_holiday_mode, R.string.menu_item_holidaymode, R.string.notification_lbl_holidayModeSwitchedOffDueToRemoveActivity);
    
    private final int[] mAttributes;
    private final int mIcon;

    private AlertSubCategory(int i, int... iArr) {
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
