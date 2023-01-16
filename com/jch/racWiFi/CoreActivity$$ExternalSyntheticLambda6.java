package com.jch.racWiFi;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CoreActivity$$ExternalSyntheticLambda6 implements Observer {
    public final /* synthetic */ CoreActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ CallBackListener f$2;

    public /* synthetic */ CoreActivity$$ExternalSyntheticLambda6(CoreActivity coreActivity, String str, CallBackListener callBackListener) {
        this.f$0 = coreActivity;
        this.f$1 = str;
        this.f$2 = callBackListener;
    }

    public final void onChanged(Object obj) {
        this.f$0.m789lambda$registerDevice$1$comjchracWiFiCoreActivity(this.f$1, this.f$2, (Resource) obj);
    }
}
