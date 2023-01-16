package com.jch.racWiFi;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CoreActivity$$ExternalSyntheticLambda7 implements Observer {
    public final /* synthetic */ CoreActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ CallBackListener f$3;

    public /* synthetic */ CoreActivity$$ExternalSyntheticLambda7(CoreActivity coreActivity, String str, String str2, CallBackListener callBackListener) {
        this.f$0 = coreActivity;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = callBackListener;
    }

    public final void onChanged(Object obj) {
        this.f$0.m783lambda$deRegisterDevice$0$comjchracWiFiCoreActivity(this.f$1, this.f$2, this.f$3, (Resource) obj);
    }
}
