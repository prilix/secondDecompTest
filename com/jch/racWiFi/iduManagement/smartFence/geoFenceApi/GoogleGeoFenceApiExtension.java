package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class GoogleGeoFenceApiExtension implements OnCompleteListener<Void> {
    private static final String TAG = "GoogleGeoFenceApiExtension";
    private Context context;
    private FamilyIdGeoFenceDataMap familyIdGeoFenceDataMap;
    private PendingIntent mGeofencePendingIntent;
    private GeofencingClient mGeofencingClient;

    public void setFamilyIdGeoFenceDataMap(FamilyIdGeoFenceDataMap familyIdGeoFenceDataMap2) {
        this.familyIdGeoFenceDataMap = familyIdGeoFenceDataMap2;
    }

    public GoogleGeoFenceApiExtension(Context context2) {
        this.context = context2;
        this.mGeofencingClient = LocationServices.getGeofencingClient(context2);
    }

    public void addGeoFencesAll(OnCompleteListener<Void> onCompleteListener) {
        if (!this.familyIdGeoFenceDataMap.isEmpty()) {
            List<Geofence> listOfGeofenceForGoogleApi = this.familyIdGeoFenceDataMap.getListOfGeofenceForGoogleApi();
            if (!listOfGeofenceForGoogleApi.isEmpty()) {
                this.mGeofencingClient.addGeofences(getGeofencingRequest(listOfGeofenceForGoogleApi), getGeofencePendingIntent()).addOnCompleteListener(onCompleteListener);
            }
        }
    }

    private PendingIntent getGeofencePendingIntent() {
        PendingIntent pendingIntent = this.mGeofencePendingIntent;
        if (pendingIntent != null) {
            return pendingIntent;
        }
        Intent intent = new Intent(this.context, GeofenceBroadcastReceiver.class);
        if (Build.VERSION.SDK_INT >= 23) {
            this.mGeofencePendingIntent = PendingIntent.getBroadcast(this.context, 0, intent, 201326592);
        } else {
            this.mGeofencePendingIntent = PendingIntent.getBroadcast(this.context, 0, intent, 134217728);
        }
        return this.mGeofencePendingIntent;
    }

    private GeofencingRequest getGeofencingRequest(List<Geofence> list) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(0);
        builder.addGeofences(list);
        return builder.build();
    }

    public void removeGeofences(OnCompleteListener<Void> onCompleteListener) {
        this.mGeofencingClient.removeGeofences(getGeofencePendingIntent()).addOnCompleteListener(onCompleteListener);
    }

    public void onComplete(Task<Void> task) {
        if (!task.isSuccessful()) {
            Log.w(TAG, GeofenceErrorMessages.getErrorString(this.context, task.getException()));
        }
    }
}
