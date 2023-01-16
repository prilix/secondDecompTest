package com.jch.racWiFi.fcm.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FcmViewModel$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ FcmViewModel f$0;
    public final /* synthetic */ MediatorLiveData f$1;
    public final /* synthetic */ LiveData f$2;

    public /* synthetic */ FcmViewModel$$ExternalSyntheticLambda2(FcmViewModel fcmViewModel, MediatorLiveData mediatorLiveData, LiveData liveData) {
        this.f$0 = fcmViewModel;
        this.f$1 = mediatorLiveData;
        this.f$2 = liveData;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo29457x6ffa7ea0(this.f$1, this.f$2, (Resource) obj);
    }
}
