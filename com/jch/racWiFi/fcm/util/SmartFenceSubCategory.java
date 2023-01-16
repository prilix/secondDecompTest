package com.jch.racWiFi.fcm.util;

import com.jch_hitachi.aircloudglobal.R;

public enum SmartFenceSubCategory {
    ENABLE_LOCATION_ACCESS_PERMISSION(R.drawable.ic_smart_fence_notification_icon, R.string.notification_lbl_smartFenceMonitoringTitle, R.string.notification_lbl_smartFenceEnableLocationAccessPermissionSingleDesc, R.string.notification_lbl_smartFenceEnableLocationAccessPermissionMultipleDesc),
    LOCATION_CONTROLS_DISABLED(R.drawable.ic_smart_fence_notification_icon, R.string.notification_lbl_smartFenceDisabledTitle, R.string.notification_lbl_smartFenceLocationControlsDisabled),
    ARRIVING(R.drawable.ic_geofence_arriving_red, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceArrivingSingleDesc, R.string.notification_lbl_smartFenceArrivingMultipleDesc),
    ARRIVING_ENABLED(R.drawable.ic_geofence_arriving_red, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceArrivingEnabledSingleDesc, R.string.notification_lbl_smartFenceArrivingEnabledMultipleDesc),
    ARRIVING_ON(R.drawable.ic_geofence_arriving_red, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceArrivingOnSingleDesc, R.string.notification_lbl_smartFenceArrivingOnMultipleDesc),
    ARRIVING_DISABLED(R.drawable.ic_geofence_arriving_red, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceArrivingDisabledSingleDesc, R.string.notification_lbl_smartFenceArrivingDisabledMultipleDesc),
    ARRIVING_OFF(R.drawable.ic_geofence_arriving_red, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceArrivingOffSingleDesc, R.string.notification_lbl_smartFenceArrivingOffMultipleDesc),
    LEAVING(R.drawable.ic_geofence_leaving_red, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceLeavingSingleDesc, R.string.notification_lbl_smartFenceLeavingMultipleDesc),
    LEAVING_ENABLED(R.drawable.ic_geofence_leaving_red, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceLeavingEnabledSingleDesc, R.string.notification_lbl_smartFenceLeavingEnabledMultipleDesc),
    LEAVING_ON(R.drawable.ic_geofence_leaving_red, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceLeavingOnSingleDesc, R.string.notification_lbl_smartFenceLeavingOnMultipleDesc),
    LEAVING_DISABLED(R.drawable.ic_geofence_leaving_red, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceLeavingDisabledSingleDesc, R.string.notification_lbl_smartFenceLeavingDisabledMultipleDesc),
    LEAVING_OFF(R.drawable.ic_geofence_leaving_red, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceLeavingOffSingleDesc, R.string.notification_lbl_smartFenceLeavingOffMultipleDesc),
    LOCATION_CONTROLS_SETTINGS_UPDATED(R.drawable.ic_smart_fence_notification_icon, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceLocationControlsSettingsUpdatedSingleDesc, R.string.notification_lbl_smartFenceLocationControlsSettingsUpdatedMultipleDesc),
    USER_REMOVED(R.drawable.ic_smart_fence_notification_icon, R.string.smartFence_lbl_smartFence, R.string.notification_lbl_smartFenceUserRemovedSingleDesc, R.string.notification_lbl_smartFenceUserRemovedMultipleDesc);
    
    private final int[] mAttributes;
    private final int mIcon;

    private SmartFenceSubCategory(int i, int... iArr) {
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
