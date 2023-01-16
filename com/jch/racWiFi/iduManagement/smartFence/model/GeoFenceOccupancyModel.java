package com.jch.racWiFi.iduManagement.smartFence.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.SharedPreference.SharedPref;

public class GeoFenceOccupancyModel {
    @SerializedName("geofenceOccupancyStatus")
    private GeoFenceOccupancyStatus mGeofenceOccupancyStatus;
    @SerializedName("locationControlsSettingsId")
    private Long mLocationControlsSettingsId;

    public GeoFenceOccupancyStatus getGeofenceOccupancyStatus() {
        return this.mGeofenceOccupancyStatus;
    }

    public void setGeofenceOccupancyStatus(GeoFenceOccupancyStatus geoFenceOccupancyStatus) {
        this.mGeofenceOccupancyStatus = geoFenceOccupancyStatus;
    }

    public Long getLocationControlsSettingsId() {
        return this.mLocationControlsSettingsId;
    }

    public void setLocationControlsSettingsId(Long l) {
        this.mLocationControlsSettingsId = l;
    }

    public enum GeoFenceOccupancyStatus {
        OCCUPIED,
        UNOCCUPIED;
        
        private static final String PREF_KEY = "GeoFenceOccupancyStatus_PREF_KEY";

        public static void persist(GeoFenceOccupancyStatus geoFenceOccupancyStatus) {
            SharedPref.getInstance().getSharedPreferenceEditor().putString(PREF_KEY, geoFenceOccupancyStatus.name()).commit();
        }

        public static GeoFenceOccupancyStatus getCurrentOccupancyFromPreference() {
            String string = SharedPref.getInstance().getSharedPreferencesReader().getString(PREF_KEY, (String) null);
            if (string != null) {
                return valueOf(string);
            }
            return null;
        }

        public static void clearCurrentOccupancy() {
            SharedPref.getInstance().getSharedPreferenceEditor().putString(PREF_KEY, (String) null).commit();
        }
    }
}
