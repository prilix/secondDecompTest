package com.accord.fusedlocationmodule;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.accord.common.utils.Logger;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class FusedLocationProviderAPIExtension implements LifecycleObserver {
    private static final LocationRequest BALANCED_POWER_ACCURACY_FIVE_SECOND_INTERVAL_LOCATION_REQUEST;
    private static final LocationRequest BALANCED_POWER_ACCURACY_ONE_SECOND_INTERVAL_LOCATION_REQUEST;
    /* access modifiers changed from: private */
    public static final LocationRequest HIGH_ACCURACY_FIVE_SECOND_INTERVAL_LOCATION_REQUEST;
    /* access modifiers changed from: private */
    public static final LocationRequest HIGH_ACCURACY_ONE_SECOND_INTERVAL_LOCATION_REQUEST;
    /* access modifiers changed from: private */
    public static final LocationRequest LOW_POWER_FIVE_SECOND_INTERVAL_LOCATION_REQUEST;
    /* access modifiers changed from: private */
    public static final LocationRequest LOW_POWER_ONE_SECOND_INTERVAL_LOCATION_REQUEST;
    private Lifecycle lifecycle;
    private boolean locationCallbackAdded = false;
    /* access modifiers changed from: private */
    public MutableLiveData<Location> locationMutableLiveData;
    /* access modifiers changed from: private */
    public MutableLiveData<LocationResult> locationResultMutableLiveData;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback = new LocationCallback() {
        public void onLocationResult(LocationResult locationResult) {
            if (FusedLocationProviderAPIExtension.this.locationResultMutableLiveData != null && FusedLocationProviderAPIExtension.this.locationResultMutableLiveData.hasActiveObservers()) {
                FusedLocationProviderAPIExtension.this.locationResultMutableLiveData.postValue(locationResult);
            }
            if (FusedLocationProviderAPIExtension.this.locationMutableLiveData != null && FusedLocationProviderAPIExtension.this.locationMutableLiveData.hasActiveObservers()) {
                List<Location> locations = locationResult.getLocations();
                Location location = locations.get(locations.size() - 1);
                Logger.m49i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                FusedLocationProviderAPIExtension.this.locationMutableLiveData.postValue(location);
            }
        }

        public void onLocationAvailability(LocationAvailability locationAvailability) {
            super.onLocationAvailability(locationAvailability);
        }
    };
    private LocationRequest mLocationRequest;
    private SettingsClient mSettingsClient;

    public MutableLiveData<Location> getLocationMutableLiveData() {
        MutableLiveData<Location> mutableLiveData = new MutableLiveData<>();
        this.locationMutableLiveData = mutableLiveData;
        return mutableLiveData;
    }

    public MutableLiveData<LocationResult> getLocationResultMutableLiveData() {
        MutableLiveData<LocationResult> mutableLiveData = new MutableLiveData<>();
        this.locationResultMutableLiveData = mutableLiveData;
        return mutableLiveData;
    }

    public FusedLocationProviderAPIExtension(Context context) {
        init(context);
        this.mLocationRequest = prepareLocationRequestInstance();
    }

    public FusedLocationProviderAPIExtension(Context context, LocationRequestType locationRequestType) {
        init(context);
        this.mLocationRequest = locationRequestType.getLocationRequest();
    }

    public FusedLocationProviderAPIExtension(Context context, LocationRequest locationRequest) {
        init(context);
        this.mLocationRequest = locationRequest;
    }

    public FusedLocationProviderAPIExtension(Context context, long j, long j2, int i) {
        init(context);
        this.mLocationRequest = prepareLocationRequestInstance(j, j2, i);
    }

    private void init(Context context) {
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        this.mSettingsClient = LocationServices.getSettingsClient(context);
    }

    public void getLastKnownLocation(OnSuccessListener<Location> onSuccessListener) {
        this.mFusedLocationClient.getLastLocation().addOnSuccessListener(onSuccessListener);
    }

    public void checkLocationServiceEnabled(OnSuccessListener<LocationSettingsResponse> onSuccessListener, OnFailureListener onFailureListener) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(this.mLocationRequest);
        Task<LocationSettingsResponse> checkLocationSettings = this.mSettingsClient.checkLocationSettings(builder.build());
        if (onSuccessListener != null) {
            checkLocationSettings.addOnSuccessListener(onSuccessListener);
        }
        if (onFailureListener != null) {
            checkLocationSettings.addOnFailureListener(onFailureListener);
        }
    }

    public void requestForLocation(Lifecycle lifecycle2) {
        this.lifecycle = lifecycle2;
        lifecycle2.addObserver(this);
    }

    public void stopFusedLocationCallback() {
        stopFusedLocation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void startFusedLocation() {
        if (!this.locationCallbackAdded) {
            this.locationCallbackAdded = true;
            this.mFusedLocationClient.requestLocationUpdates(this.mLocationRequest, this.mLocationCallback, Looper.getMainLooper());
            Logger.m50v("FusedLocation", "started");
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void stopFusedLocation() {
        if (this.locationCallbackAdded) {
            this.locationCallbackAdded = false;
            this.mFusedLocationClient.removeLocationUpdates(this.mLocationCallback);
            Logger.m50v("FusedLocation", "stopped");
            Lifecycle lifecycle2 = this.lifecycle;
            if (lifecycle2 != null) {
                lifecycle2.removeObserver(this);
            }
        }
    }

    private LocationRequest prepareLocationRequestInstance() {
        return prepareLocationRequestInstance(5000, 5000, 102);
    }

    private LocationRequest prepareLocationRequestInstance(long j, long j2, int i) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(j);
        locationRequest.setFastestInterval(j2);
        locationRequest.setPriority(i);
        return locationRequest;
    }

    public enum LocationRequestType {
        HIGH_ACCURACY_ONE_SECOND_INTERVAL(FusedLocationProviderAPIExtension.HIGH_ACCURACY_ONE_SECOND_INTERVAL_LOCATION_REQUEST),
        HIGH_ACCURACY_FIVE_SECOND_INTERVAL(FusedLocationProviderAPIExtension.HIGH_ACCURACY_FIVE_SECOND_INTERVAL_LOCATION_REQUEST),
        LOW_POWER_ONE_SECOND_INTERVAL(FusedLocationProviderAPIExtension.LOW_POWER_ONE_SECOND_INTERVAL_LOCATION_REQUEST),
        LOW_POWER_FIVE_SECOND_INTERVAL(FusedLocationProviderAPIExtension.LOW_POWER_FIVE_SECOND_INTERVAL_LOCATION_REQUEST);
        
        private LocationRequest locationRequest;

        private LocationRequestType(LocationRequest locationRequest2) {
            this.locationRequest = locationRequest2;
        }

        public LocationRequest getLocationRequest() {
            return this.locationRequest;
        }
    }

    static {
        LocationRequest locationRequest = new LocationRequest();
        HIGH_ACCURACY_ONE_SECOND_INTERVAL_LOCATION_REQUEST = locationRequest;
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(100);
        LocationRequest locationRequest2 = new LocationRequest();
        HIGH_ACCURACY_FIVE_SECOND_INTERVAL_LOCATION_REQUEST = locationRequest2;
        locationRequest2.setInterval(5000);
        locationRequest2.setFastestInterval(5000);
        locationRequest2.setPriority(100);
        LocationRequest locationRequest3 = new LocationRequest();
        LOW_POWER_ONE_SECOND_INTERVAL_LOCATION_REQUEST = locationRequest3;
        locationRequest3.setInterval(1000);
        locationRequest3.setFastestInterval(1000);
        locationRequest3.setPriority(104);
        LocationRequest locationRequest4 = new LocationRequest();
        LOW_POWER_FIVE_SECOND_INTERVAL_LOCATION_REQUEST = locationRequest4;
        locationRequest4.setInterval(5000);
        locationRequest4.setFastestInterval(5000);
        locationRequest4.setPriority(104);
        LocationRequest locationRequest5 = new LocationRequest();
        BALANCED_POWER_ACCURACY_ONE_SECOND_INTERVAL_LOCATION_REQUEST = locationRequest5;
        locationRequest5.setInterval(1000);
        locationRequest5.setFastestInterval(1000);
        locationRequest5.setPriority(102);
        LocationRequest locationRequest6 = new LocationRequest();
        BALANCED_POWER_ACCURACY_FIVE_SECOND_INTERVAL_LOCATION_REQUEST = locationRequest6;
        locationRequest6.setInterval(5000);
        locationRequest6.setFastestInterval(5000);
        locationRequest6.setPriority(102);
    }
}
