package com.jch.racWiFi.iduManagement.smartFence.geoFenceUtils;

import com.google.android.gms.maps.model.LatLng;

public class LocationUtil {
    static double DEG2RAD = 0.017453292519943295d;
    static int RADIUS_EARTH_METERS = 6378137;

    public static LatLng destinationPoint(LatLng latLng, double d, double d2) {
        double d3 = d / ((double) RADIUS_EARTH_METERS);
        double d4 = DEG2RAD;
        double d5 = d2 * d4;
        double d6 = d4 * latLng.latitude;
        double d7 = DEG2RAD * latLng.longitude;
        double asin = Math.asin((Math.sin(d6) * Math.cos(d3)) + (Math.cos(d6) * Math.sin(d3) * Math.cos(d5)));
        double atan2 = d7 + Math.atan2(Math.sin(d5) * Math.sin(d3) * Math.cos(d6), Math.cos(d3) - (Math.sin(d6) * Math.sin(asin)));
        double d8 = DEG2RAD;
        return new LatLng(asin / d8, atan2 / d8);
    }
}
