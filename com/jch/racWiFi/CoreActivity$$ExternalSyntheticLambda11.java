package com.jch.racWiFi;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnSuccessListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CoreActivity$$ExternalSyntheticLambda11 implements OnSuccessListener {
    public final /* synthetic */ CoreActivity f$0;
    public final /* synthetic */ CoreActivity f$1;
    public final /* synthetic */ LocationCallback f$2;

    public /* synthetic */ CoreActivity$$ExternalSyntheticLambda11(CoreActivity coreActivity, CoreActivity coreActivity2, LocationCallback locationCallback) {
        this.f$0 = coreActivity;
        this.f$1 = coreActivity2;
        this.f$2 = locationCallback;
    }

    public final void onSuccess(Object obj) {
        this.f$0.m790lambda$verifyLocationService$4$comjchracWiFiCoreActivity(this.f$1, this.f$2, (LocationSettingsResponse) obj);
    }
}
