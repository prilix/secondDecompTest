package com.thanosfisherman.wifiutils;

import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.thanosfisherman.elvis.Elvis;

public class LocationUtils {
    public static final int GOOD_TO_GO = 1000;
    public static final int LOCATION_DISABLED = 1112;
    public static final int NO_LOCATION_AVAILABLE = 1111;
    private static final String TAG = "LocationUtils";

    public static int checkLocationAvailability(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!context.getPackageManager().hasSystemFeature("android.hardware.location")) {
                Log.d(TAG, "NO GPS SENSOR");
                return 1111;
            } else if (!isLocationEnabled(context)) {
                Log.d(TAG, "Location DISABLED");
                return 1112;
            }
        }
        Log.d(TAG, "GPS GOOD TO GO");
        return 1000;
    }

    private static boolean isLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        return Elvis.m668of(locationManager).next(LocationUtils$$ExternalSyntheticLambda0.INSTANCE).getBoolean() || Elvis.m668of(locationManager).next(LocationUtils$$ExternalSyntheticLambda1.INSTANCE).getBoolean();
    }
}
