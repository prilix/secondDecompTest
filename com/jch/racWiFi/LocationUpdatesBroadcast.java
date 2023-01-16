package com.jch.racWiFi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class LocationUpdatesBroadcast extends BroadcastReceiver {
    public static final String ACTION_ON_LOCATION_CHANGED = "on_location_changed";
    public static final String LOCATION_PARCEL = "location_object_instance";
    private ILocationChangeUpdates mLocationChangeUpdates;

    public interface ILocationChangeUpdates {
        void onLocationChangedFromBroadcast(Location location);
    }

    public LocationUpdatesBroadcast(ILocationChangeUpdates iLocationChangeUpdates) {
        this.mLocationChangeUpdates = iLocationChangeUpdates;
    }

    public void registerLocationBroadcastReceiver(Context context) {
        LocalBroadcastManager.getInstance(context).registerReceiver(this, new IntentFilter(ACTION_ON_LOCATION_CHANGED));
    }

    public void unregisterLocationBroadcastReceiver(Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals(ACTION_ON_LOCATION_CHANGED)) {
            this.mLocationChangeUpdates.onLocationChangedFromBroadcast((Location) intent.getParcelableExtra(LOCATION_PARCEL));
        }
    }
}
