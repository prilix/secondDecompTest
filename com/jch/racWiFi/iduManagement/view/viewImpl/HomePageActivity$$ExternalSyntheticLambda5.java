package com.jch.racWiFi.iduManagement.view.viewImpl;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class HomePageActivity$$ExternalSyntheticLambda5 implements Observer {
    public final /* synthetic */ HomePageActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ CallBackListener f$2;

    public /* synthetic */ HomePageActivity$$ExternalSyntheticLambda5(HomePageActivity homePageActivity, String str, CallBackListener callBackListener) {
        this.f$0 = homePageActivity;
        this.f$1 = str;
        this.f$2 = callBackListener;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo30744x739d8ff6(this.f$1, this.f$2, (Resource) obj);
    }
}
