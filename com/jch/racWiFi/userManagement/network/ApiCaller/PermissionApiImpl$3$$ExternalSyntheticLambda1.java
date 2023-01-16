package com.jch.racWiFi.userManagement.network.ApiCaller;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Response;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PermissionApiImpl$3$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MutableLiveData f$0;
    public final /* synthetic */ Response f$1;

    public /* synthetic */ PermissionApiImpl$3$$ExternalSyntheticLambda1(MutableLiveData mutableLiveData, Response response) {
        this.f$0 = mutableLiveData;
        this.f$1 = response;
    }

    public final void run() {
        this.f$0.setValue(this.f$1);
    }
}
