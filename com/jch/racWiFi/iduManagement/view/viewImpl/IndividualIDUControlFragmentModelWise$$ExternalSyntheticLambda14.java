package com.jch.racWiFi.iduManagement.view.viewImpl;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda14 implements Observer {
    public final /* synthetic */ IndividualIDUControlFragmentModelWise f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ CallBackListener f$2;

    public /* synthetic */ IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda14(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise, String str, CallBackListener callBackListener) {
        this.f$0 = individualIDUControlFragmentModelWise;
        this.f$1 = str;
        this.f$2 = callBackListener;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo30939x7e102c49(this.f$1, this.f$2, (Resource) obj);
    }
}
