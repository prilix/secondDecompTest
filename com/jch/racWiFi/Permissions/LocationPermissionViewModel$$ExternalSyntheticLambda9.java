package com.jch.racWiFi.Permissions;

import com.google.android.gms.tasks.OnSuccessListener;
import com.jch.racWiFi.Permissions.LocationPermissionViewModel;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LocationPermissionViewModel$$ExternalSyntheticLambda9 implements OnSuccessListener {
    public final /* synthetic */ LocationPermissionViewModel.LocationOnOffCallback f$0;

    public /* synthetic */ LocationPermissionViewModel$$ExternalSyntheticLambda9(LocationPermissionViewModel.LocationOnOffCallback locationOnOffCallback) {
        this.f$0 = locationOnOffCallback;
    }

    public final void onSuccess(Object obj) {
        this.f$0.locationEnabled();
    }
}
