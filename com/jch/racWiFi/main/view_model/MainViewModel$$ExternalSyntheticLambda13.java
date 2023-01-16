package com.jch.racWiFi.main.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MainViewModel$$ExternalSyntheticLambda13 implements Observer {
    public final /* synthetic */ MediatorLiveData f$0;
    public final /* synthetic */ LiveData f$1;

    public /* synthetic */ MainViewModel$$ExternalSyntheticLambda13(MediatorLiveData mediatorLiveData, LiveData liveData) {
        this.f$0 = mediatorLiveData;
        this.f$1 = liveData;
    }

    public final void onChanged(Object obj) {
        MainViewModel.lambda$registerDevice$5(this.f$0, this.f$1, (Resource) obj);
    }
}
