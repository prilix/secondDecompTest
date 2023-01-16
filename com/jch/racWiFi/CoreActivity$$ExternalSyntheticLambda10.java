package com.jch.racWiFi;

import com.google.android.gms.tasks.OnFailureListener;
import com.jch.racWiFi.util.listeners.LocationServiceListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CoreActivity$$ExternalSyntheticLambda10 implements OnFailureListener {
    public final /* synthetic */ CoreActivity f$0;
    public final /* synthetic */ CoreActivity f$1;
    public final /* synthetic */ LocationServiceListener f$2;

    public /* synthetic */ CoreActivity$$ExternalSyntheticLambda10(CoreActivity coreActivity, CoreActivity coreActivity2, LocationServiceListener locationServiceListener) {
        this.f$0 = coreActivity;
        this.f$1 = coreActivity2;
        this.f$2 = locationServiceListener;
    }

    public final void onFailure(Exception exc) {
        this.f$0.m791lambda$verifyLocationService$5$comjchracWiFiCoreActivity(this.f$1, this.f$2, exc);
    }
}
