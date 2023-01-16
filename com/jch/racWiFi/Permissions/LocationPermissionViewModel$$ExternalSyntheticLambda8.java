package com.jch.racWiFi.Permissions;

import com.google.android.gms.tasks.OnFailureListener;
import com.jch.racWiFi.Permissions.LocationPermissionViewModel;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LocationPermissionViewModel$$ExternalSyntheticLambda8 implements OnFailureListener {
    public final /* synthetic */ LocationPermissionViewModel.LocationOnOffCallback f$0;

    public /* synthetic */ LocationPermissionViewModel$$ExternalSyntheticLambda8(LocationPermissionViewModel.LocationOnOffCallback locationOnOffCallback) {
        this.f$0 = locationOnOffCallback;
    }

    public final void onFailure(Exception exc) {
        this.f$0.locationDisabled();
    }
}
