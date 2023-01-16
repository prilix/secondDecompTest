package com.jch.racWiFi;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CoreActivity$$ExternalSyntheticLambda8 implements Observer {
    public final /* synthetic */ CoreActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ CallBackListener f$2;

    public /* synthetic */ CoreActivity$$ExternalSyntheticLambda8(CoreActivity coreActivity, boolean z, CallBackListener callBackListener) {
        this.f$0 = coreActivity;
        this.f$1 = z;
        this.f$2 = callBackListener;
    }

    public final void onChanged(Object obj) {
        this.f$0.m788lambda$refreshToken$2$comjchracWiFiCoreActivity(this.f$1, this.f$2, (Resource) obj);
    }
}
