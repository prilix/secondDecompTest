package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import com.google.android.gms.maps.model.LatLng;
import java.util.HashMap;

final class Constants {
    static final HashMap<String, LatLng> BAY_AREA_LANDMARKS;
    static final String GEOFENCES_ADDED_KEY = "com.google.android.gms.location.Geofence.GEOFENCES_ADDED_KEY";
    private static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;
    static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS = 43200000;
    static final float GEOFENCE_RADIUS_IN_METERS = 100.0f;
    private static final String PACKAGE_NAME = "com.google.android.gms.location.Geofence";

    private Constants() {
    }

    static {
        HashMap<String, LatLng> hashMap = new HashMap<>();
        BAY_AREA_LANDMARKS = hashMap;
        hashMap.put("SFO", new LatLng(13.0184369d, 77.6689387d));
        hashMap.put("GOOGLE", new LatLng(37.422611d, -122.0840577d));
    }
}
