package com.jch.racWiFi.iduManagement.view.viewImpl;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class HomePageActivity$$ExternalSyntheticLambda4 implements Observer {
    public final /* synthetic */ HomePageActivity f$0;
    public final /* synthetic */ CallBackListener f$1;

    public /* synthetic */ HomePageActivity$$ExternalSyntheticLambda4(HomePageActivity homePageActivity, CallBackListener callBackListener) {
        this.f$0 = homePageActivity;
        this.f$1 = callBackListener;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo30746xcf62f38(this.f$1, (Resource) obj);
    }
}
