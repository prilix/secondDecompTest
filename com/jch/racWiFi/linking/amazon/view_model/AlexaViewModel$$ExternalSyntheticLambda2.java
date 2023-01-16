package com.jch.racWiFi.linking.amazon.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AlexaViewModel$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ MediatorLiveData f$0;
    public final /* synthetic */ LiveData f$1;

    public /* synthetic */ AlexaViewModel$$ExternalSyntheticLambda2(MediatorLiveData mediatorLiveData, LiveData liveData) {
        this.f$0 = mediatorLiveData;
        this.f$1 = liveData;
    }

    public final void onChanged(Object obj) {
        AlexaViewModel.lambda$skillEnable$8(this.f$0, this.f$1, (Resource) obj);
    }
}
