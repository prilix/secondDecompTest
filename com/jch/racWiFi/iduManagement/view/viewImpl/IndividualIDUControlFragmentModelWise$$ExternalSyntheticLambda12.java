package com.jch.racWiFi.iduManagement.view.viewImpl;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda12 implements Observer {
    public final /* synthetic */ IndividualIDUControlFragmentModelWise f$0;
    public final /* synthetic */ CallBackListener f$1;

    public /* synthetic */ IndividualIDUControlFragmentModelWise$$ExternalSyntheticLambda12(IndividualIDUControlFragmentModelWise individualIDUControlFragmentModelWise, CallBackListener callBackListener) {
        this.f$0 = individualIDUControlFragmentModelWise;
        this.f$1 = callBackListener;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo30945x7c3f252f(this.f$1, (Resource) obj);
    }
}
