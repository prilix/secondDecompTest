package com.jch.racWiFi.userManagement.view.viewImpl;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SplashFragment$$ExternalSyntheticLambda4 implements Observer {
    public final /* synthetic */ CallBackListener f$0;

    public /* synthetic */ SplashFragment$$ExternalSyntheticLambda4(CallBackListener callBackListener) {
        this.f$0 = callBackListener;
    }

    public final void onChanged(Object obj) {
        SplashFragment.lambda$deRegisterDevice$1(this.f$0, (Resource) obj);
    }
}
