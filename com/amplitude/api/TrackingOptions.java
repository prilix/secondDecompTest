package com.amplitude.api;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class TrackingOptions {
    private static String[] COPPA_CONTROL_PROPERTIES = {Constants.AMP_TRACKING_OPTION_ADID, Constants.AMP_TRACKING_OPTION_CITY, Constants.AMP_TRACKING_OPTION_IP_ADDRESS, Constants.AMP_TRACKING_OPTION_LAT_LNG};
    private static String[] SERVER_SIDE_PROPERTIES = {Constants.AMP_TRACKING_OPTION_CITY, Constants.AMP_TRACKING_OPTION_COUNTRY, Constants.AMP_TRACKING_OPTION_DMA, Constants.AMP_TRACKING_OPTION_IP_ADDRESS, Constants.AMP_TRACKING_OPTION_LAT_LNG, Constants.AMP_TRACKING_OPTION_REGION};
    private static final String TAG = "com.amplitude.api.TrackingOptions";
    Set<String> disabledFields = new HashSet();

    public TrackingOptions disableAdid() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_ADID);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackAdid() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_ADID);
    }

    public TrackingOptions disableCarrier() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_CARRIER);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackCarrier() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_CARRIER);
    }

    public TrackingOptions disableCity() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_CITY);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackCity() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_CITY);
    }

    public TrackingOptions disableCountry() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_COUNTRY);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackCountry() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_COUNTRY);
    }

    public TrackingOptions disableDeviceBrand() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_DEVICE_BRAND);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackDeviceBrand() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_DEVICE_BRAND);
    }

    public TrackingOptions disableDeviceManufacturer() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_DEVICE_MANUFACTURER);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackDeviceManufacturer() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_DEVICE_MANUFACTURER);
    }

    public TrackingOptions disableDeviceModel() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_DEVICE_MODEL);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackDeviceModel() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_DEVICE_MODEL);
    }

    public TrackingOptions disableDma() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_DMA);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackDma() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_DMA);
    }

    public TrackingOptions disableIpAddress() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_IP_ADDRESS);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackIpAddress() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_IP_ADDRESS);
    }

    public TrackingOptions disableLanguage() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_LANGUAGE);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackLanguage() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_LANGUAGE);
    }

    public TrackingOptions disableLatLng() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_LAT_LNG);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackLatLng() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_LAT_LNG);
    }

    public TrackingOptions disableOsName() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_OS_NAME);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackOsName() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_OS_NAME);
    }

    public TrackingOptions disableOsVersion() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_OS_VERSION);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackOsVersion() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_OS_VERSION);
    }

    public TrackingOptions disableApiLevel() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_API_LEVEL);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackApiLevel() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_API_LEVEL);
    }

    public TrackingOptions disablePlatform() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_PLATFORM);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackPlatform() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_PLATFORM);
    }

    public TrackingOptions disableRegion() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_REGION);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackRegion() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_REGION);
    }

    public TrackingOptions disableVersionName() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_VERSION_NAME);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTrackVersionName() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_VERSION_NAME);
    }

    private void disableTrackingField(String str) {
        this.disabledFields.add(str);
    }

    /* access modifiers changed from: protected */
    public JSONObject getApiPropertiesTrackingOptions() {
        JSONObject jSONObject = new JSONObject();
        if (this.disabledFields.isEmpty()) {
            return jSONObject;
        }
        for (String str : SERVER_SIDE_PROPERTIES) {
            if (this.disabledFields.contains(str)) {
                try {
                    jSONObject.put(str, false);
                } catch (JSONException e) {
                    AmplitudeLog.getLogger().mo13082e(TAG, e.toString());
                }
            }
        }
        return jSONObject;
    }

    private boolean shouldTrackField(String str) {
        return !this.disabledFields.contains(str);
    }

    /* access modifiers changed from: package-private */
    public TrackingOptions mergeIn(TrackingOptions trackingOptions) {
        for (String disableTrackingField : trackingOptions.disabledFields) {
            disableTrackingField(disableTrackingField);
        }
        return this;
    }

    static TrackingOptions copyOf(TrackingOptions trackingOptions) {
        TrackingOptions trackingOptions2 = new TrackingOptions();
        for (String disableTrackingField : trackingOptions.disabledFields) {
            trackingOptions2.disableTrackingField(disableTrackingField);
        }
        return trackingOptions2;
    }

    static TrackingOptions forCoppaControl() {
        TrackingOptions trackingOptions = new TrackingOptions();
        for (String disableTrackingField : COPPA_CONTROL_PROPERTIES) {
            trackingOptions.disableTrackingField(disableTrackingField);
        }
        return trackingOptions;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return ((TrackingOptions) obj).disabledFields.equals(this.disabledFields);
        }
        return false;
    }
}
